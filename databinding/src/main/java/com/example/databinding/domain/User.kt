package com.example.databinding.domain

data class User(var name: String, var age: Int, var gender: Gender)

enum class Gender {
    Male, Female
}