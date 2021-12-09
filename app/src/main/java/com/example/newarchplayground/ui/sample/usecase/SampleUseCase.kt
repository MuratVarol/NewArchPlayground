package com.example.newarchplayground.ui.sample.usecase

import com.example.newarchplayground.ui.common.IUseCase
import javax.inject.Inject
import kotlin.random.Random

class SampleUseCase @Inject constructor(): IUseCase<String> {
    override suspend fun invoke(): List<String> {
        return mutableListOf<String>().apply{
            repeat(4){
                add(Random.nextInt(0,5).toString())
            }
        }
    }
}