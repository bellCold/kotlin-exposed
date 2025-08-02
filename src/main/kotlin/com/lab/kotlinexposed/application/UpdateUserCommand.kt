package com.lab.kotlinexposed.application

data class UpdateUserCommand(
    val name: String?,
    val email: String?,
    val age: Int?
)
