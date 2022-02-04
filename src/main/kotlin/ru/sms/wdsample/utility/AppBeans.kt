package ru.sms.wdsample.utility

import org.springframework.context.ApplicationContext
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component


class AppBeans {
    companion object {
        private lateinit var applicationContext: ApplicationContext

        fun <T> getBean(beanClass: Class<T>): T {
            return applicationContext.getBean(beanClass)
        }
    }

    @Component
    internal class ApplicationContextRetriever {
        @EventListener
        fun storeApplicationContext(event: ContextRefreshedEvent) {
            AppBeans.applicationContext = event.applicationContext
        }
    }
}