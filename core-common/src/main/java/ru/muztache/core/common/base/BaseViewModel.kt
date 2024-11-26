package ru.muztache.core.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow


abstract class BaseViewModel<S : BaseState, EV : BaseEvent, EF : BaseEffect> : ViewModel() {

    abstract val state: StateFlow<S>

    abstract val effect: SharedFlow<EF>

    abstract fun reducer(event: EV)
}