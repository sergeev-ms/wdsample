package ru.sms.wdsample.webdav.model

import io.milton.annotations.CreatedDate
import io.milton.annotations.Name
import io.milton.annotations.UniqueId
import java.util.*

class WebDavFolder(private var name: String) {
    private val createdDate: Date = Date()
    val files : MutableList<WebDavFile> = mutableListOf()

    @Name
    fun getName(): String {
        return name
    }

    @UniqueId
    fun getUniqueId(): String {
        return name
    }

    @CreatedDate
    fun getCreatedDate(): Date {
        return createdDate
    }
}