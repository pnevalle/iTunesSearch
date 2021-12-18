package com.pnevalle.itunessearch.common

import kotlinx.coroutines.withContext
import com.pnevalle.itunessearch.data.Result
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

/**
 * Based from https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 *
 * Executes the api call and handle if it is success / fail.
 *
 * @param apiCall - the function to execute the retrofit API call
 *
 * @return the [Result] wrapper class parsed from Retrofit
 */
suspend fun <T> safeApiCall(
    apiCall: suspend () -> Response<T>,
): Result<T> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall.invoke()
            val body = response.body()
            if (response.isSuccessful && body != null) {
                Result.Success(body)
            } else {
                Result.Error(response.code(), response.message())
            }
        } catch (t: Throwable) {
            Result.Error()
        }
    }
}