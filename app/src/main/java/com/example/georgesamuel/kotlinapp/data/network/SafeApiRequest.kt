package com.example.georgesamuel.kotlinapp.data.network

import com.example.georgesamuel.kotlinapp.util.ApiException
import com.google.gson.JsonObject
import org.json.JSONException
import org.json.JSONObject
import retrofit2.Response
import java.io.InterruptedIOException
import java.lang.Exception
import java.util.concurrent.TimeoutException

abstract class SafeApiRequest {
    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>): T {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                val error = response.errorBody()?.string()

                val message = StringBuilder()
                error?.let {
                    try {
                        message.append(JSONObject(it).getString("message"))
                    } catch (e: JSONException) {
                    }
                    message.append("\n")
                }
                message.append("Error code: ${response.code()}")
                throw ApiException(message.toString())
            }
        } catch (e: InterruptedIOException) {
            throw ApiException("Poor connection")
        }
    }
}