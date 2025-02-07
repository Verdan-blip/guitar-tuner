package ru.muztache.feature.tuner.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetTunedUkulelesUseCase

class GetTunedUkulelesUseCaseTest {

    private val tunedInstrumentRepository: TunedInstrumentRepository<Ukulele> = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var getTunedUkulelesUseCase: GetTunedUkulelesUseCase

    @Before
    fun setUp() {
        getTunedUkulelesUseCase = GetTunedUkulelesUseCase(
            tunedInstrumentRepository, testDispatcher
        )
    }

    @Test
    fun `invoke should return all ukuleles from repository`() = runBlocking {
        val expectedUkuleleFlow = flow<Map<String, Ukulele>> {
            mapOf("Ukulele1" to Ukulele(mockk()), "Ukulele2" to Ukulele(mockk()))
        }
        coEvery { tunedInstrumentRepository.instruments } returns expectedUkuleleFlow

        val result = getTunedUkulelesUseCase()

        assertEquals(expectedUkuleleFlow.toList(), result.toList())
        coVerify(exactly = 1) { tunedInstrumentRepository.instruments }
    }
}
