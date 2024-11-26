package ru.muztache.feature.tuner.ui.engine.mapper

import ru.muztache.feature.tuner.ui.engine.tone.Tone

const val ROOT_C_PITCH = 16.35f
const val ROOT_C_DIES_PITCH = 17.32f
const val ROOT_D_PITCH = 18.35f
const val ROOT_D_DIES_PITCH = 19.45f
const val ROOT_E_PITCH = 20.6f
const val ROOT_F_PITCH = 21.83f
const val ROOT_F_DIES_PITCH = 23.12f
const val ROOT_G_PITCH = 24.5f
const val ROOT_G_DIES_PITCH = 25.96f
const val ROOT_A_PITCH = 27.5f
const val ROOT_A_DIES_PITCH = 29.14f
const val ROOT_B_PITCH = 30.87f

fun Float.toMusicalNote(): Tone {
    var octave = 0
    var rootPitch = this
    while (rootPitch > ROOT_B_PITCH) { rootPitch /= 2; octave++ }

    return when (rootPitch) {
        in ROOT_C_PITCH..ROOT_C_DIES_PITCH -> Tone.valueOf("C$octave")
        in ROOT_C_DIES_PITCH..ROOT_D_PITCH -> Tone.valueOf("C${octave}_DIES")
        in ROOT_D_PITCH..ROOT_D_DIES_PITCH -> Tone.valueOf("D$octave")
        in ROOT_D_DIES_PITCH..ROOT_E_PITCH -> Tone.valueOf("D${octave}_DIES")
        in ROOT_F_PITCH..ROOT_F_DIES_PITCH -> Tone.valueOf("F$octave")
        in ROOT_F_DIES_PITCH..ROOT_G_PITCH -> Tone.valueOf("F${octave}_DIES")
        in ROOT_G_PITCH..ROOT_G_DIES_PITCH -> Tone.valueOf("G$octave")
        in ROOT_G_DIES_PITCH..ROOT_A_PITCH -> Tone.valueOf("G${octave}_DIES")
        in ROOT_A_PITCH..ROOT_A_DIES_PITCH -> Tone.valueOf("A$octave")
        in ROOT_A_DIES_PITCH..ROOT_B_PITCH -> Tone.valueOf("A${octave}_DIES")
        else -> Tone.valueOf("B$octave")
    }
}
