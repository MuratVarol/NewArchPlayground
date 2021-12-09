package com.example.newarchplayground.ui.common

interface IUseCase<out T> {
    suspend operator fun invoke(): List<T>
}