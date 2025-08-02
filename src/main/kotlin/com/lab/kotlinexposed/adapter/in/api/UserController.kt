package com.lab.kotlinexposed.adapter.`in`.api

import com.lab.kotlinexposed.adapter.`in`.api.dto.CreateUserRequest
import com.lab.kotlinexposed.adapter.`in`.api.dto.UpdateUserRequest
import com.lab.kotlinexposed.adapter.`in`.api.dto.UserResponse
import com.lab.kotlinexposed.application.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {
    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<UserResponse> {
        val user = userService.createUser(createUserRequest.toCommand())

        return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user))
    }

    @GetMapping("/{id}")
    fun findUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
        val user = userService.findById(id)
        return ResponseEntity.ok(UserResponse.from(user))
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<UserResponse>> {
        val users = userService.findAllUsers()
        return ResponseEntity.ok(users.map { UserResponse.from(it) })
    }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updateUserRequest: UpdateUserRequest): ResponseEntity<UserResponse> {
        val user = userService.updateUser(id, updateUserRequest.toCommand())
        return ResponseEntity.ok(UserResponse.from(user))
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)

    }
}