package ru.muztache.core.common.base.viewmodel

import ru.muztache.core.common.base.mvi.BaseEvent
import ru.muztache.core.common.base.mvi.BaseState


abstract class BaseNavigableViewModel<S : BaseState, EV : BaseEvent> :
    BaseViewModel<S, EV>()