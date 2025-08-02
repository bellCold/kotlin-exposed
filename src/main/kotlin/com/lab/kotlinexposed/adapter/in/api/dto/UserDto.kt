package com.lab.kotlinexposed.adapter.`in`.api.dto

import com.lab.kotlinexposed.application.CreateUserCommand
import com.lab.kotlinexposed.application.UpdateUserCommand
import com.lab.kotlinexposed.domain.User
import java.time.LocalDateTime

data class CreateUserRequest(
    val name: String,
    val email: String,
    val age: Int
) {
    fun toCommand(): CreateUserCommand {
        return CreateUserCommand(
            name = name,
            email = email,
            age = age,
        )
    }
}

data class UpdateUserRequest(
    val name: String?,
    val email: String?,
    val age: Int?
) {
    fun toCommand(): UpdateUserCommand {
        return UpdateUserCommand(
            name = name,
            email = email,
            age = age,
        )
    }
}

data class UserResponse(
    val id: Long,
    val name: String?,
    val email: String?,
    val age: Int?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id.value,
                name = user.name,
                email = user.email,
                age = user.age,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
}