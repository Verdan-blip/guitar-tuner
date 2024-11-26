package ru.muztache.feature.tuner.ui.engine.tone

enum class Tone(val frequency: Float) {
    
    C0(16.35f),
    C0_DIES(17.32f),
    D0(18.35f),
    D0_DIES(19.45f),
    E0(20.60f),
    F0(21.83f),
    F0_DIES(23.12f),
    G0(24.50f),
    G0_DIES(25.96f),
    A0(27.50f),
    A0_DIES(29.14f),
    B0(30.87f),

    C1(32.70f),
    C1_DIES(34.65f),
    D1(36.71f),
    D1_DIES(38.89f),
    E1(41.20f),
    F1(43.65f),
    F1_DIES(46.25f),
    G1(49.00f),
    G1_DIES(51.91f),
    A1(55.00f),
    A1_DIES(58.27f),
    B1(61.74f),

    C2(65.41f),
    C2DIES(69.30f),
    D2(73.42f),
    D2_DIES(77.78f),
    E2(82.41f),
    F2(87.31f),
    F2_DIES(92.50f),
    G2(98.00f),
    G2_DIES(103.83f),
    A2(110.00f),
    A2_DIES(116.54f),
    B2(123.47f),

    C3(130.81f),
    C3_DIES(138.59f),
    D3(146.83f),
    D3_DIES(155.56f),
    E3(164.81f),
    F3(174.61f),
    F3_DIES(185.00f),
    G3(196.00f),
    G3_DIES(207.65f),
    A3(220.00f),
    A3_DIES(233.08f),
    B3(246.94f),

    C4(261.63f),
    C4_DIES(277.18f),
    D4(293.66f),
    D4_DIES(311.13f),
    E4(329.63f),
    F4(349.23f),
    F4_DIES(369.99f),
    G4(392.00f),
    G4_DIES(415.30f),
    A4(440.00f),
    A4_DIES(466.16f),
    B4(493.88f),

    C5(523.25f),
    C5_DIES(554.37f),
    D5(587.33f),
    D5_DIES(622.25f),
    E5(659.25f),
    F5(698.46f),
    F5_DIES(739.99f),
    G5(783.99f),
    G5_DIES(830.61f),
    A5(880.00f),
    A5_DIES(932.33f),
    B5(987.77f),

    C6(1046.50f),
    C6_DIES(1108.73f),
    D6(1174.66f),
    D6_DIES(1244.51f),
    E6(1318.51f),
    F6(1396.91f),
    F6_DIES(1479.98f),
    G6(1567.98f),
    G6_DIES(1661.22f),
    A6(1760.00f),
    A6_DIES(1864.66f),
    B6(1975.53f),

    C7(2093.00f),
    C7_DIES(2217.46f),
    D7(2349.32f),
    D7_DIES(2489.02f),
    E7(2637.02f),
    F7(2793.83f),
    F7_DIES(2959.96f),
    G7(3135.96f),
    G7_DIES(3322.44f),
    A7(3520.00f),
    A7_DIES(3729.31f),
    B7(3951.07f),

    C8(4186.01f),
    C_DIES(4434.92f),
    D8(4698.63f),
    D_DIES(4978.03f),
    E8(5274.04f),
    F8(5587.65f),
    F_DIES(5919.91f),
    G8(6271.93f),
    G_DIES(6644.88f),
    A8(7040.00f),
    A_DIES(7458.62f),
    B8(7902.13f);
}