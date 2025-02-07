package ru.muztache.core.common.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

val applicationScope = CoroutineScope(Dispatchers.Main + SupervisorJob())
