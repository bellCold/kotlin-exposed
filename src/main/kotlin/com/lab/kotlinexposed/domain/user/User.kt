package com.lab.kotlinexposed.domain.user

import com.lab.kotlinexposed.domain.AuditableEntity
import com.lab.kotlinexposed.domain.AuditableTable
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

object Users : AuditableTable("users") {
    val name = varchar("name", 100).nullable()
    val email = varchar("email", 255).nullable()
    val age = integer("age").nullable()
}

class User(id: EntityID<Long>) : AuditableEntity<AuditableTable>(id, Users) {
    companion object : LongEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email
    var age by Users.age
}