package com.example.newarchplayground.data.common


sealed class DataResult<out E, out S> {
    /** * Represents the left side of [DataResult] class which by convention is a "Failure". */
    data class Error<out E>(val a: E) : DataResult<E, Nothing>()

    /** * Represents the right side of [DataResult] class which by convention is a "Success". */
    data class Success<out R>(val b: R) : DataResult<Nothing, R>()

    object Loading : DataResult<Nothing, Nothing>()

    val isSuccess get() = this is Success<S>
    val isError get() = this is Error<E>
    val isLoading get() = this is Loading

    fun <L> error(a: L) = Error(a)
    fun <R> success(b: R) = Success(b)

    fun <T> either(fnError: (E) -> T, fnSuccess: (S) -> T, fnLoading: () -> T): T =
        when (this) {
            is Error -> fnError(a)
            is Success -> fnSuccess(b)
            is Loading -> fnLoading()
        }

    fun neither(fnError: (E) -> Any, fnSuccess: () -> Unit, fnLoading: () -> Unit): Any =
        when (this) {
            is Error -> fnError(a)
            is Success -> fnSuccess()
            is Loading -> fnLoading
        }

    fun <T> transform(fnR: (S) -> T): DataResult<E, T> {
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

fun <T, L, R> DataResult<L, R>.flatMap(fn: (R) -> DataResult<L, T>): DataResult<L, T> =
    when (this) {
        is DataResult.Error -> DataResult.Error(a)
        is DataResult.Success -> fn(b)
        is DataResult.Loading -> DataResult.Loading
    }

fun <T, L, R> DataResult<L, R>.map(fn: (R) -> (T)): DataResult<L, T> = this.flatMap(fn.c(::success))
