package ru.muztache.core.common.entity

sealed interface FetchRequest {

    data object Pending : FetchRequest

    data class Failure(val throwable: Throwable) : FetchRequest

    data class Success<T : Any>(val data: T) : FetchRequest
}
