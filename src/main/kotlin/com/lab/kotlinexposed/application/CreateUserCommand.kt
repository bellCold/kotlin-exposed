package com.lab.kotlinexposed.application

data class CreateUserCommand(
    val name: String,
    val email: String,
    val age: Int
)