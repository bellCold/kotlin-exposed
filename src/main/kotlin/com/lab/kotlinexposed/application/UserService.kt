package com.lab.kotlinexposed.application

import com.lab.kotlinexposed.application.port.out.UserPersistencePort
import com.lab.kotlinexposed.domain.user.User
import org.springframework.stereotype.Service

@Service
class UserService(private val userPersistencePort: UserPersistencePort) {
    fun createUser(createUserCommand: CreateUserCommand): User {
        return userPersistencePort.save(createUserCommand)
    }

    fun findById(id: Long): User {
        return userPersistencePort.findById(id) ?: throw RuntimeException("User with id $id not found")
    }

    fun findAllUsers(): List<User> {
        return userPersistencePort.findAll()
    }

    fun updateUser(id: Long, userCommand: UpdateUserCommand): User {
        return userPersistencePort.update(id, userCommand)
    }

    fun deleteUser(id: Long) {
        userPersistencePort.delete(id)
    }
}