package ru.sms.wdsample.webdav.controller

import io.milton.annotations.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.sms.wdsample.app.WebDavSampleService
import ru.sms.wdsample.utility.AppBeans
import ru.sms.wdsample.webdav.model.WebDavFile
import ru.sms.wdsample.webdav.model.WebDavFolder
import java.io.ByteArrayInputStream
import java.io.InputStream

@ResourceController
class SamplesController {
    private val logger: Logger = LoggerFactory.getLogger(SamplesController::class.java)

    @Root
    fun getRoot(): SamplesController {
        return this
    }

    @Name
    fun getRootName(root: SamplesController) : String {
        return "webdav" //don't know for that but milton logs warn if it's not presents
    }

    @ChildrenOf
    fun getWebDavFolders(root: SamplesController): List<WebDavFolder> {
        val webDavSampleService = AppBeans.getBean(WebDavSampleService::class.java)
        return webDavSampleService.getSamplesFolders()
    }

    @ChildrenOf
    fun getWebDavFiles(webDavFolder: WebDavFolder): Collection<WebDavFile> {
        val webDavSampleService = AppBeans.getBean(WebDavSampleService::class.java)
        return webDavSampleService.getFiles(webDavFolder)
    }


    @Get
    fun getChild(webDavFile: WebDavFile): InputStream {
        return webDavFile.inputStream
    }

    @Delete
    fun delete(webDavFile: WebDavFile) {
        val webDavSampleService = AppBeans.getBean(WebDavSampleService::class.java)
        webDavSampleService.remove(webDavFile)
    }

    @PutChild
    fun putChild(parent: WebDavFolder, name: String, inputStream: InputStream) {
        val webDavSampleService = AppBeans.getBean(WebDavSampleService::class.java)
        webDavSampleService.createAttachment(parent, name, inputStream)
    }



}