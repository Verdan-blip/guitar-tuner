package ru.muztache.core.common.flow

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.time.Duration

fun <T> Flow<T>.bufferByTime(duration: Duration): Flow<List<T>> = flow {
    val buffer = mutableListOf<T>()
    coroutineScope {
        launch { collect { buffer.add(it) } }
        while (true) {
            delay(duration)
            if (buffer.isNotEmpty()) {
                emit(buffer.toList())
                buffer.clear()
            }
        }
    }
}