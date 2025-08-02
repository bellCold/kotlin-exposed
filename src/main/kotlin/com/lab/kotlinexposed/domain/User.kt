package com.lab.kotlinexposed.domain

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object Users : LongIdTable() {
    val name = varchar("name", 100).nullable()
    val email = varchar("email", 255).nullable()
    val age = integer("age").nullable()
    val createdAt = datetime("created_at").default(LocalDateTime.now())
    val updatedAt = datetime("updated_at").default(LocalDateTime.now())
}

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email
    var age by Users.age
    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt
}