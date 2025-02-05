package ru.muztache.core.common.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.mvi.BaseEvent
import ru.muztache.core.common.base.mvi.BaseState

abstract class BaseViewModel<S : BaseState, EV : BaseEvent> : ViewModel() {

    abstract val state: StateFlow<S>

    private val _effect = MutableSharedFlow<BaseEffect>()
    val effect: SharedFlow<BaseEffect> get() = _effect

    abstract fun reducer(event: EV)

    protected suspend fun <T> doSafeCall(
        onException: (Exception) -> T = { throw it },
        body: suspend () -> T
    ): T {
        return try {
            body()
        } catch (ex: Exception) {
            onException(ex)
        }
    }

    protected suspend fun emitBaseEffect(effect: BaseEffect) {
        _effect.emit(effect)
    }
}