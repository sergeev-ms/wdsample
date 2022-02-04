package ru.sms.wdsample.app

import io.jmix.core.DataManager
import io.jmix.core.FetchPlan
import io.jmix.core.FileStorageLocator
import io.jmix.core.Id
import io.jmix.core.security.Authenticated
import io.jmix.localfs.LocalFileStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import ru.sms.wdsample.entity.Attachment
import ru.sms.wdsample.entity.Sample
import ru.sms.wdsample.webdav.model.WebDavFile
import ru.sms.wdsample.webdav.model.WebDavFolder
import java.io.InputStream
import java.util.*


@Component("wds_WebDavSampleService")
open class WebDavSampleService {

    @Autowired
    private lateinit var dataManager: DataManager

    @Autowired
    private lateinit var fileStorageLocator: FileStorageLocator

    @Authenticated
    open fun getSamplesFolders() : List<WebDavFolder> {
        val allSamples = dataManager.load(Sample::class.java)
            .all()
            .list()
        return allSamples.map {
            WebDavFolder(it.id.toString(), it.name ?: it.id.toString(), it.createdDate)
        }.toList()
    }

    @Authenticated
    open fun getFiles(webDavFolder: WebDavFolder): Collection<WebDavFile> {
        val attachments = dataManager.load(Attachment::class.java)
            .query("where e.sample.id = ?1", UUID.fromString(webDavFolder.getUniqueId()))
            .fetchPlan {
                it.addFetchPlan(FetchPlan.LOCAL).add("file")
            }
            .list()
        val localFileStorage = fileStorageLocator.getDefault<LocalFileStorage>()
        return attachments.mapNotNull { attachment ->
            attachment.file?.let {
                WebDavFile(it.fileName, attachment.id.toString(), localFileStorage.openStream(it)).apply {
                    this.createdDate = attachment.createdDate
                    this.modifiedDate = attachment.lastModifiedDate
                }
            }
        }.toList()
    }

    @Authenticated
    open fun remove(webDavFile: WebDavFile) {
        dataManager.remove(Id.of(UUID.fromString(webDavFile.getUniqueId()), Attachment::class.java))
    }

    @Authenticated
    open fun createAttachment(parent: WebDavFolder, name: String, inputStream: InputStream) {
        val sampleId = UUID.fromString(parent.getUniqueId())
        val localFileStorage = fileStorageLocator.getDefault<LocalFileStorage>()
        val fileRef = localFileStorage.saveStream(name, inputStream)
        dataManager.create(Attachment::class.java).apply {
            this.sample = dataManager.getReference(Id.of(sampleId, Sample::class.java))
            this.file = fileRef
        }.run {
            dataManager.save(this)
        }

    }
}