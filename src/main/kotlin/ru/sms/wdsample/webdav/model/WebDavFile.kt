package ru.sms.wdsample.webdav.model

import io.milton.annotations.*
import java.io.InputStream
import java.util.*


class WebDavFile(private var name: String, private var id: String, var inputStream: InputStream) {
    var createdDate: Date? = Date()
        @CreatedDate
        get
    var modifiedDate: Date? = Date()
        @ModifiedDate
        get


    @Name
    fun getName(): String {
        return name
    }

    @UniqueId
    fun getUniqueId(): String {
        return id
    }

    @ContentLength
    fun getContentLength(): Long {
        return inputStream.available().toLong()
    }
}