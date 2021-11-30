package com.example.newarchplayground.data.common


sealed class ApiResult<out E, out S> {
    /** * Represents the left side of [ApiResult] class which by convention is a "Failure". */
    data class Error<out E>(val a: E) : ApiResult<E, Nothing>()

    /** * Represents the right side of [ApiResult] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : ApiResult<Nothing, R>()

    object Loading : ApiResult<Nothing, Nothing>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>
    val isLoading get() = this is Loading

    fun <L> error(a: L) = Error(a)
    fun <R> success(b: R) = Success(b)

    fun either(fnError: (E) -> Any, fnSuccess: (S) -> Any, fnLoading: () -> Unit): Any =
        when (this) {
            is Error -> fnError(a)
            is Success -> fnSuccess(b)
            is Loading -> fnLoading
        }

    fun neither(fnL: (E) -> Any, fnR: () -> Unit, fnLoading: () -> Unit): Any =
        when (this) {
            is Error -> fnL(a)
            is Success -> fnR()
            is Loading -> fnLoading
        }

    fun <T> transform(fnR: (S) -> T): ApiResult<E, T> {
        return when (this) {
            is Error -> this
            is Success -> Success(fnR(b))
            is Loading -> this
        }
    }

    fun ifSuccess(block: (S) -> Unit) {
        when (this) {
            is Success -> block(b)
            else -> return
        }
    }

    fun ifError(block: (E) -> Unit) {
        when (this) {
            is Error -> block(a)
            else -> return
        }
    }
    fun ifLoading(block: () -> Unit) {
        when (this) {
            is Error -> block()
            else -> return
        }
    }
}

// Credits to Alex Hart -> https://proandroiddev.com/kotlins-nothing-type-946de7d464fb
// Composes 2 functions
fun <A, B, C> ((A) -> B).c(f: (B) -> C): (A) -> C = {
    f(this(it))
}

fun <T, L, R> ApiResult<L, R>.flatMap(fn: (R) -> ApiResult<L, T>): ApiResult<L, T> =
    when (this) {
        is ApiResult.Error -> ApiResult.Error(a)
        is ApiResult.Success -> fn(b)
        is ApiResult.Loading -> ApiResult.Loading
    }

fun <T, L, R> ApiResult<L, R>.map(fn: (R) -> (T)): ApiResult<L, T> = this.flatMap(fn.c(::success))
