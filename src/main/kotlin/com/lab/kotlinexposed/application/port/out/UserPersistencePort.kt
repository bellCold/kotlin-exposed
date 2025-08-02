package com.lab.kotlinexposed.application.port.out

import com.lab.kotlinexposed.application.CreateUserCommand
import com.lab.kotlinexposed.application.UpdateUserCommand
import com.lab.kotlinexposed.domain.User

interface UserPersistencePort {
    fun save(createUserCommand: CreateUserCommand): User
    fun findById(id: Long): User?
    fun findAll(): List<User>
    fun update(id: Long, updateUserCommand: UpdateUserCommand): User
    fun delete(id: Long)
}