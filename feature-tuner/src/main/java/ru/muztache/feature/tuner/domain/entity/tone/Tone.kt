package ru.muztache.feature.tuner.domain.entity.tone

enum class Tone(val rootFrequency: Float) {

    C(16.35f),
    C_DIES(17.32f),
    D(18.35f),
    D_DIES(19.45f),
    E(20.60f),
    F(21.83f),
    F_DIES(23.12f),
    G(24.50f),
    G_DIES(25.96f),
    A(27.50f),
    A_DIES(29.14f),
    B(30.87f),

    UNRECOGNIZED(0f)
}
