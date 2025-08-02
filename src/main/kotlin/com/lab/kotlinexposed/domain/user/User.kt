package com.lab.kotlinexposed.domain.user

import com.lab.kotlinexposed.domain.AbstractEntity
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

object Users : AbstractEntity("users") {
    val name = varchar("name", 100).nullable()
    val email = varchar("email", 255).nullable()
    val age = integer("age").nullable()
}

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email
    var age by Users.age
    var createdAt by Users.createdAt
    var updatedAt by Users.updatedAt
}