package ru.sms.wdsample.webdav.controller

import io.milton.annotations.*
import liquibase.pro.packaged.lo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.sms.wdsample.webdav.model.WebDavFile
import ru.sms.wdsample.webdav.model.WebDavFolder
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.nio.charset.Charset
import java.util.*


@ResourceController
class HelloWorldController {
    private val logger: Logger = LoggerFactory.getLogger(HelloWorldController::class.java)


    private val webDavFolders: MutableList<WebDavFolder> = mutableListOf()


    init {
        try {
            val bytes = "Hello World".toByteArray(Charset.forName("UTF-8"))
            webDavFolders.add(WebDavFolder("hello").apply {
                this.files.add(WebDavFile("file1.txt", bytes))
                this.files.add(WebDavFile("file2.txt", bytes))
            })
            webDavFolders.add(WebDavFolder("word").apply {
                this.files.add(WebDavFile("file3.txt", bytes))
                this.files.add(WebDavFile("file4.txt", bytes))
            })
        } catch (e: UnsupportedEncodingException) {
            logger.error(e.message, e)
        }
    }

    @Root
    fun getRoot(): HelloWorldController {
        return this
    }

    @ChildrenOf
    fun getWebDavFolders(root: HelloWorldController): List<WebDavFolder> {

        return webDavFolders
    }

    @ChildrenOf
    fun getWebDavFiles(webDavFolder: WebDavFolder): Collection<WebDavFile> {
        return webDavFolder.files
    }


    @Get
    fun getChild(webDavFile: WebDavFile): InputStream {
        return ByteArrayInputStream(webDavFile.getBytes())
    }

    @PutChild
    fun putChild(parent: WebDavFolder, name: String, bytes: ByteArray) {
//        if (name != null) {
        parent.files.add(WebDavFile(name, bytes))
//        } else {
//            parent.setBytes(bytes)
//            webDavFiles[parent.getName()] = parent
//        }
    }

//    @Delete
//    fun delete(webDavFile: WebDavFile) {
//        webDavFiles.remove(webDavFile.getName())
//    }

//    @Name
//    fun getWebDavFile(webDavFile: WebDavFile): String {
//        return webDavFile.getName()
//    }

//    @DisplayName
//    fun getDisplayName(webDavFile: WebDavFile): String {
//        return webDavFile.getName()
//    }

//    @UniqueId
//    fun getUniqueId(webDavFile: WebDavFile): String {
//        return webDavFile.getName()
//    }
//
//    @ModifiedDate
//    fun getModifiedDate(webDavFile: WebDavFile): Date {
//        return webDavFile.getModifiedDate()
//    }
//
//    @CreatedDate
//    fun getCreatedDate(webDavFile: WebDavFile): Date {
//        return webDavFile.getCreatedDate()
//    }
}