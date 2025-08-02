package com.lab.kotlinexposed.config

import com.lab.kotlinexposed.domain.user.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import javax.sql.DataSource

@Configuration
class DatabaseConfig(
    private val dataSource: DataSource
) {
    @EventListener(ApplicationReadyEvent::class)
    fun configureDatabase() {
        Database.connect(dataSource)

        transaction {
            SchemaUtils.createMissingTablesAndColumns(Users)
        }
    }
}
