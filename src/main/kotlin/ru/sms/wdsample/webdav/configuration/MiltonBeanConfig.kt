package ru.sms.wdsample.webdav.configuration

import io.milton.servlet.MiltonFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.firewall.StrictHttpFirewall
import java.util.*
import javax.servlet.Filter


@Configuration
open class MiltonBeanConfig {
    @Bean
    open fun someFilterRegistration(): FilterRegistrationBean<Filter> {
        val registration: FilterRegistrationBean<Filter> = FilterRegistrationBean()
        registration.filter = getMiltonFilter()
        registration.setName("MiltonFilter")
        registration.addUrlPatterns("/webdav/*")
        registration.addInitParameter("resource.factory.class", "io.milton.http.annotated.AnnotationResourceFactory")
        registration.addInitParameter("controllerPackagesToScan", "ru.sms.wdsample.webdav.controller")
        registration.addInitParameter("milton.configurator", "ru.sms.wdsample.webdav.configuration.MiltonConfig")
        registration.order = 1
        return registration
    }

    open fun getMiltonFilter(): Filter {
        return MiltonFilter()
    }

    @Bean
    open fun httpFirewall(): StrictHttpFirewall {
        val firewall = StrictHttpFirewall()
        firewall.setUnsafeAllowAnyHttpMethod(true)
        return firewall
    }
}