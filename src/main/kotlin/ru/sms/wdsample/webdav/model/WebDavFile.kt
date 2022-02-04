package ru.sms.wdsample.webdav.model

import io.milton.annotations.*

import java.util.*


class WebDavFile(private var name: String, private var bytes: ByteArray) {
    private var createdDate: Date = Date()
    private var modifiedDate: Date = Date()
    private var contentLength: Long? = null

    init {
        this.contentLength = bytes.size.toLong()
    }


    @Name
    fun getName(): String {
        return name
    }

    @UniqueId
    fun getUniqueId(): String {
        return name
    }

    @ModifiedDate
    fun getModifiedDate(): Date {
        return modifiedDate
    }

    @CreatedDate
    fun getCreatedDate(): Date {
        return createdDate
    }

    @ContentLength
    fun getContentLength(): Long {
        return bytes.size.toLong()
    }

    fun getBytes(): ByteArray {
        return bytes
    }

    fun setBytes(bytes: ByteArray) {
        this.bytes = bytes
    }
}