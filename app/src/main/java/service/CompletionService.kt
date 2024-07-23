package service

import gtp.entity.CompletionResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface CompletionService {
    @POST("chat/completions")
    suspend fun getCompletion(
        @Body completionData: Any,
        @Header("Authorization") barer: String
    ): CompletionResponse
}