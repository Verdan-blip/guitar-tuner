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
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.TunedInstrumentRepository
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetTunedGuitarsUseCase

class GetTunedGuitarsUseCaseTest {

    private val tunedInstrumentRepository: TunedInstrumentRepository<Guitar> = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var getTunedGuitarsUseCase: GetTunedGuitarsUseCase

    @Before
    fun setUp() {
        getTunedGuitarsUseCase = GetTunedGuitarsUseCase(
            tunedInstrumentRepository, testDispatcher
        )
    }

    @Test
    fun `invoke should return all guitars from repository`() = runBlocking {
        val expectedGuitarFlow = flow<Map<String, Guitar>> {
            mapOf("Guitar1" to Guitar(mockk()), "Guitar2" to Guitar(mockk()))
        }
        coEvery { tunedInstrumentRepository.instruments } returns expectedGuitarFlow

        val result = getTunedGuitarsUseCase()

        assertEquals(expectedGuitarFlow.toList(), result.toList())
        coVerify(exactly = 1) { tunedInstrumentRepository.instruments }
    }
}
