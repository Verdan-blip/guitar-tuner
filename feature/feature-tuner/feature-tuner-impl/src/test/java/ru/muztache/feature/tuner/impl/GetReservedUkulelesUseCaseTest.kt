package ru.muztache.feature.tuner.impl

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import ru.muztache.feature.tuner.api.domain.entity.instrument.Ukulele
import ru.muztache.feature.tuner.api.domain.repository.ReservedInstrumentRepository
import ru.muztache.feature.tuner.impl.domain.usecase.get.GetReservedUkulelesUseCase

class GetReservedUkulelesUseCaseTest {

    private val mockReservedInstrumentRepository: ReservedInstrumentRepository<Ukulele> = mockk()
    private val testDispatcher = Dispatchers.Unconfined
    private lateinit var getReservedUkulelesUseCase: GetReservedUkulelesUseCase

    @Before
    fun setUp() {
        getReservedUkulelesUseCase = GetReservedUkulelesUseCase(
            mockReservedInstrumentRepository, testDispatcher
        )
    }

    @Test
    fun `invoke should return all ukuleles from repository`() = runBlocking {
        val expectedUkuleles = mapOf(
            "Ukulele1" to Ukulele(mockk()),
            "Ukulele2" to Ukulele(mockk())
        )
        coEvery { mockReservedInstrumentRepository.getAll() } returns expectedUkuleles

        val result = getReservedUkulelesUseCase()

        assertEquals(expectedUkuleles, result)
        coVerify(exactly = 1) { mockReservedInstrumentRepository.getAll() }
    }
}
