package ru.sms.wdsample.webdav.configuration

import io.milton.http.fs.NullSecurityManager
import io.milton.servlet.DefaultMiltonConfigurator


class MiltonConfig() : DefaultMiltonConfigurator() {
    private var securityManager: NullSecurityManager? = null

    init {
        securityManager = NullSecurityManager()
    }

    override fun build() {
        builder.securityManager = securityManager
        builder.contextPath = "/webdav/"
        builder.urlAdapter = UrlAdapterImpl()
        super.build()
    }
}