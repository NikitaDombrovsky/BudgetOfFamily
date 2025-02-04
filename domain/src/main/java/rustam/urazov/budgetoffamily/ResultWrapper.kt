package rustam.urazov.budgetoffamily

import rustam.urazov.budgetoffamily.models.error.ErrorResponse

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class Error(val code: Int? = null, val error: ErrorResponse? = null): ResultWrapper<Nothing>()

    object NetworkError: ResultWrapper<Nothing>()
}