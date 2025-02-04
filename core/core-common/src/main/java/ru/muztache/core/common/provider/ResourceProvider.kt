package ru.muztache.core.common.provider

interface ResourceProvider {

    fun getString(id: Int): String
}