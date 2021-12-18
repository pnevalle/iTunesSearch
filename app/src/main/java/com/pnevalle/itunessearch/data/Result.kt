package com.pnevalle.itunessearch.data

import com.pnevalle.itunessearch.common.GENERIC_ERROR

/**
 * Based from https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
 *
 * Wrapper class for API results
 */
sealed class Result<out T> {

    /**
     * The data class if the API is successful
     *
     * @param value the API response model
     */
    data class Success<out T>(val value: T) : Result<T>()

    /**
     * The data class if the API failed
     * @param code the error code
     * @param errorMessage the error message. Has the default value of [GENERIC_ERROR]
     */
    data class Error(
        val code: Int? = null,
        val errorMessage: String = GENERIC_ERROR,
    ) : Result<Nothing>()
}