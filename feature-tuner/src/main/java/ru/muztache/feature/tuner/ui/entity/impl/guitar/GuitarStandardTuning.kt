package ru.muztache.feature.tuner.ui.entity.impl.guitar

import ru.muztache.feature.tuner.ui.engine.tone.Tone

object GuitarStandardTuning : GuitarTuning() {

    override val firstString: Tone = Tone.E4

    override val secondString: Tone = Tone.B3

    override val thirdString: Tone = Tone.G3

    override val fourthString: Tone = Tone.D3

    override val fifthString: Tone = Tone.A2

    override val sixthString: Tone = Tone.E2

    override val stringsCount: Int = 6
}