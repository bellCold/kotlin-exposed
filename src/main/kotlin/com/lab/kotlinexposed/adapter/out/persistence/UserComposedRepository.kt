package com.lab.kotlinexposed.adapter.out.persistence

import com.lab.kotlinexposed.application.CreateUserCommand
import com.lab.kotlinexposed.application.UpdateUserCommand
import com.lab.kotlinexposed.application.port.out.UserPersistencePort
import com.lab.kotlinexposed.domain.User
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
            val user = User.findById(id) ?: throw NoSuchElementException("User not found")

            user.name = updateUserCommand.name
            user.email = updateUserCommand.email
            user.age = updateUserCommand.age
            user.updatedAt = LocalDateTime.now()

            user
        }
    }


    override fun delete(id: Long) {
        transaction {
            User.findById(id)?.delete()
        }
    }
}