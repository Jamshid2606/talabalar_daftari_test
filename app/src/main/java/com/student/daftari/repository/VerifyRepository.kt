package com.student.daftari.repository

import java.util.concurrent.Flow

interface VerifyRepository {
    fun verifyCode(code:String,id:String):kotlinx.coroutines.flow.Flow<Result<Unit>>
    fun resendCode(phone:String):kotlinx.coroutines.flow.Flow<Result<String>>
}