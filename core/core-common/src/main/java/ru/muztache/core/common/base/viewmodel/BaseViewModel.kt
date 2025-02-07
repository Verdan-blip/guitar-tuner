package ru.muztache.core.common.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import ru.muztache.core.common.R
import ru.muztache.core.common.base.mvi.BaseEffect
import ru.muztache.core.common.base.mvi.BaseEvent
import ru.muztache.core.common.base.mvi.BaseState
import ru.muztache.core.common.provider.ResourceProvider

abstract class BaseViewModel<S : BaseState, EV : BaseEvent>(
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    abstract val state: StateFlow<S>

    private val _effect = MutableSharedFlow<BaseEffect>()
    val effect: SharedFlow<BaseEffect> get() = _effect

    abstract fun reducer(event: EV)

    protected suspend fun doSafeCall(
        onException: suspend (Exception) -> Unit = {
            emitBaseEffect(BaseEffect.ShowSnackBar(getProvidedString(R.string.unknown_error)))
        },
        body: suspend () -> Unit
    ) {
        try {
            body()
        } catch (ex: Exception) {
            onException(ex)
        }
    }

    protected suspend fun emitBaseEffect(effect: BaseEffect) {
        _effect.emit(effect)
    }

    protected fun getProvidedString(id: Int): String = resourceProvider.getString(id)
}
