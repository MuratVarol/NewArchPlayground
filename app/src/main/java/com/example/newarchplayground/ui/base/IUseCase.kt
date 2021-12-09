package com.example.newarchplayground.ui.base

interface IUseCase<out T> {
    suspend operator fun invoke(): List<T>
}