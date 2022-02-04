package ru.sms.wdsample.webdav.configuration

import io.milton.http.UrlAdapter
import io.milton.http.HttpManager
import io.milton.http.Request

class UrlAdapterImpl : UrlAdapter {
    override fun getUrl(request: Request): String {
        val s = HttpManager.decodeUrl(request.absolutePath)
        return if (s.contains("/webdav")) s.replace("/webdav", "") else s
    }
}