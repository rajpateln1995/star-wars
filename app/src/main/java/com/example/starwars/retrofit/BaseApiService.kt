package com.example.starwars.retrofit

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.json.JSONObject
import retrofit2.Response

abstract class BaseApiService(private val context: Context) {
    protected fun <T> handleApiCall(apiCall: suspend () -> Response<T>, endpoint: String):
            Flow<ApiResponse<T>> = flow {
        emit(ApiResponse.Loading)
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val data = response.body()
                if (data != null) {
                    emit(ApiResponse.Success(data))
                } else {
                    emit(ApiResponse.Error("Server Error"))
                }
            } else {
                val errorBody = response.errorBody()?.string()
                val errorMessage = parseErrorMessage(errorBody)
                emit(ApiResponse.Error(errorMessage))

            }
        } catch (e: Exception) {
            emit(ApiResponse.Error(e.message ?: "Unknown Error"))
        }
    }.flowOn(Dispatchers.IO)

    private fun parseErrorMessage(errorBody: String?): String {
        return try {
            val errorJson = JSONObject(errorBody ?: "")
            errorJson.getString("message")
        } catch (e: Exception) {
            "Unknown Error"
        }
    }
}
