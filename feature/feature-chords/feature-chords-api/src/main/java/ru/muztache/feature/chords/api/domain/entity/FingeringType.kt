package ru.muztache.feature.chords.api.domain.entity

sealed interface FingeringType {

    data object X : FingeringType

    data object O : FingeringType

    data class Use(val fret: Int): FingeringType

    companion object {

        fun fromString(string: String): List<FingeringType> {
            return string.split(" ").map { fret ->
                when (fret) {
                    "X" -> X
                    "O" -> O
                    else -> Use(fret.toInt())
                }
            }
        }
    }
}