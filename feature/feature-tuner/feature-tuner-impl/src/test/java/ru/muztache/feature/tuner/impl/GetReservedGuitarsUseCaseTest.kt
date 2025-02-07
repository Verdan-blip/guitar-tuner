package ru.muztache.feature.tuner.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.muztache.feature.tuner.api.domain.entity.instrument.Guitar
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetReservedGuitarsUseCase

class GetReservedGuitarsUseCaseTest {

    private val mockReservedInstrumentRepository: ReservedInstrumentRepository<Guitar> = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var getReservedGuitarsUseCase: GetReservedGuitarsUseCase

    @Before
    fun setUp() {
        getReservedGuitarsUseCase = GetReservedGuitarsUseCase(
            mockReservedInstrumentRepository, testDispatcher
        )
    }

    @Test
    fun `invoke should return all guitars from repository`() = runBlocking {
        val expectedGuitars = mapOf(
            "Guitar1" to Guitar(mockk()),
            "Guitar2" to Guitar(mockk())
        )
        coEvery { mockReservedInstrumentRepository.getAll() } returns expectedGuitars

        val result = getReservedGuitarsUseCase()

        assertEquals(expectedGuitars, result)
        coVerify(exactly = 1) { mockReservedInstrumentRepository.getAll() }
    }
}
