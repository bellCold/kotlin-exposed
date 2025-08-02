package com.lab.kotlinexposed.adapter.out.persistence

import com.lab.kotlinexposed.application.CreateUserCommand
import com.lab.kotlinexposed.application.UpdateUserCommand
import com.lab.kotlinexposed.application.port.out.UserPersistencePort
import com.lab.kotlinexposed.domain.user.User
import com.lab.kotlinexposed.domain.user.Users.age
import com.lab.kotlinexposed.domain.user.Users.name
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class UserComposedRepository : UserPersistencePort {
    override fun save(createUserCommand: CreateUserCommand): User {
        return transaction {
            User.new {
                this.name = createUserCommand.name
                this.email = createUserCommand.email
                this.age = createUserCommand.age
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }
        }
    }

    override fun findById(id: Long): User? {
        return transaction {
            User.findById(id)
        }
    }

    override fun findAll(): List<User> {
        return transaction {
            User.all().toList()
        }
    }

    override fun update(id: Long, updateUserCommand: UpdateUserCommand): User {
        return transaction {
            User.findByIdAndUpdate(id) {
                it.name = updateUserCommand.name
                it.email = updateUserCommand.email
                it.age = updateUserCommand.age
                it.updatedAt = LocalDateTime.now()
            } ?: throw NoSuchElementException("User not found")
        }
    }


    override fun delete(id: Long) {
        transaction {
            User.findById(id)?.delete()
        }
    }

    fun findByName(keyword: String): List<User> {
        return transaction {
            User.find {
                (name eq keyword)
            }
                .orderBy(
                    age to SortOrder.ASC,
                    name to SortOrder.DESC
                )
                .limit(0,0)
                .toList()
        }
    }
}