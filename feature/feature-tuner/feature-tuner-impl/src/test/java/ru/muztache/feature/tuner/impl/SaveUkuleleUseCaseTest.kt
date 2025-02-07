package ru.muztache.feature.tuner.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.domain.usecase.save.SaveUkuleleUseCase

class SaveUkuleleUseCaseTest {

    private val tunedInstrumentRepository: TunedInstrumentRepository<Ukulele> = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var saveUkuleleUseCase: SaveUkuleleUseCase

    @Before
    fun setUp() {
        saveUkuleleUseCase = SaveUkuleleUseCase(tunedInstrumentRepository, testDispatcher)
    }

    @Test
    fun `invoke should call repository save with correct parameters`() = runBlocking {
        val ukuleleName = "Stratocaster"
        val ukulele = Ukulele(mockk())
        coEvery { tunedInstrumentRepository.save(ukuleleName, ukulele) } returns Unit

        saveUkuleleUseCase(ukuleleName, ukulele)

        coVerify(exactly = 1) { tunedInstrumentRepository.save(ukuleleName, ukulele) }
    }
}
