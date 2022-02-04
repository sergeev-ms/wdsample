package ru.sms.wdsample.webdav.model

import io.milton.annotations.CreatedDate
import io.milton.annotations.ModifiedDate
import io.milton.annotations.Name
import io.milton.annotations.UniqueId
import java.util.*

class WebDavFolder(private var uniqueId: String, private var name: String, private var createdDate: Date?) {
    @Name
    fun getName(): String {
        return name
    }

    @UniqueId
    fun getUniqueId(): String {
        return uniqueId
    }

    @CreatedDate
    fun getCreatedDate(): Date {
        return createdDate ?: Date()
    }

    @ModifiedDate
    fun getModifiedDate(): Date {
        return createdDate ?: Date()
    }

}