package ru.muztache.feature.tuner.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.domain.usecase.save.SaveGuitarUseCase

class SaveGuitarUseCaseTest {

    private val tunedInstrumentRepository: TunedInstrumentRepository<Guitar> = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var saveGuitarUseCase: SaveGuitarUseCase

    @Before
    fun setUp() {
        saveGuitarUseCase = SaveGuitarUseCase(tunedInstrumentRepository, testDispatcher)
    }

    @Test
    fun `invoke should call repository save with correct parameters`() = runBlocking {
        val guitarName = "Stratocaster"
        val guitar = Guitar(mockk())
        coEvery { tunedInstrumentRepository.save(guitarName, guitar) } returns Unit

        saveGuitarUseCase(guitarName, guitar)

        coVerify(exactly = 1) { tunedInstrumentRepository.save(guitarName, guitar) }
    }
}
