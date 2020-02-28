/**
 * MIT License
 * Copyright (c) 2017 Kittinun Vantasin
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE
 * AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

@file:Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate", "unused", "RemoveExplicitTypeArguments")

package tk.skeptick.vk.apiclient

infix fun <V : Any?, E : Exception> Result<V, E>.or(fallback: V) = when (this) {
    is Result.Success -> this
    else -> Result.Success(fallback)
}

inline infix fun <V: Any?, E: Exception> Result<V, E>.getOrElse(fallback: (E) -> V): V {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> fallback(error)
    }
}

fun <V: Any?, E: Exception> Result<V, E>.getOrNull(): V? {
    return when (this) {
        is Result.Success -> value
        is Result.Failure -> null
    }
}

suspend fun <V : Any?, U : Any?, E : Exception> Result<V, E>.map(transform: suspend (V) -> U): Result<U, E> = try {
    when (this) {
        is Result.Success -> Result.Success(transform(value))
        is Result.Failure -> Result.Failure(error)
    }
} catch (ex: Exception) {
    Result.error(ex as E)
}

suspend fun <V : Any?, U : Any?, E : Exception> Result<V, E>.flatMap(transform: suspend (V) -> Result<U, E>): Result<U, E> = try {
    when (this) {
        is Result.Success -> transform(value)
        is Result.Failure -> Result.Failure(error)
    }
} catch (ex: Exception) {
    Result.error(ex as E)
}

suspend fun <V : Any?, E : Exception, E2 : Exception> Result<V, E>.mapError(transform: suspend (E) -> E2) = when (this) {
    is Result.Success -> Result.Success<V, E2>(value)
    is Result.Failure -> Result.Failure<V, E2>(transform(error))
}

suspend fun <V : Any?, E : Exception, E2 : Exception> Result<V, E>.flatMapError(transform: suspend (E) -> Result<V, E2>) = when (this) {
    is Result.Success -> Result.Success(value)
    is Result.Failure -> transform(error)
}

suspend fun <V : Any?, E : Exception> Result<V, E>.any(predicate: suspend (V) -> Boolean): Boolean = try {
    when (this) {
        is Result.Success -> predicate(value)
        is Result.Failure -> false
    }
} catch (ex: Exception) {
    false
}

suspend fun <V : Any?, E : Exception> List<Result<V, E>>.lift(): Result<List<V>, E> = Result.of {
    filterIsInstance<Result.Success<V, *>>().map(Result.Success<V, *>::value)
}


sealed class Result<out V : Any?, out E : Exception> {

    abstract operator fun component1(): V?
    abstract operator fun component2(): E?

    suspend fun <X> fold(success: suspend (V) -> X, failure: suspend (E) -> X): X {
        return when (this) {
            is Success -> success(this.value)
            is Failure -> failure(this.error)
        }
    }

    abstract fun get(): V

    class Success<out V : Any?, out E : Exception>(val value: V) : Result<V, E>() {
        override fun component1(): V? = value
        override fun component2(): E? = null
        override fun get(): V = value
        override fun toString() = "[Success: $value]"
        override fun hashCode() = value.hashCode()
        override fun equals(other: Any?) = if (this === other) true else other is Success<*, *> && value == other.value
    }

    class Failure<out V : Any?, out E : Exception>(val error: E) : Result<V, E>() {
        override fun component1(): V? = null
        override fun component2(): E? = error
        override fun get(): V = throw error
        fun getException(): E = error
        override fun toString() = "[Failure: $error]"
        override fun hashCode(): Int = error.hashCode()
        override fun equals(other: Any?) = if (this === other) true else other is Failure<*, *> && error == other.error
    }

    companion object {

        fun <E : Exception> error(ex: E): Failure<Nothing, E> {
            return Failure(ex)
        }

        fun <V : Any?> of(value: V?, fail: (() -> Exception) = { Exception() }): Result<V, Exception> {
            return value?.let(::Success) ?: error(fail)
        }

        suspend fun <V : Any?, E: Exception> of(f: suspend () -> V): Result<V, E> = try {
            Success(f())
        } catch(ex: Exception) {
            Failure(ex as E)
        }

    }

}