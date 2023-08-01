package com.jimmy.currency.domain.usecase

import com.jimmy.core_arch.domain.ISuspendableUseCase
import com.jimmy.core_data.domain.repository.HistoryRepository
import com.jimmy.core_database.entity.HistorySelection
import javax.inject.Inject

class SaveHistorySelectionUseCase @Inject constructor(
    private val repository: HistoryRepository
): ISuspendableUseCase.WithParams<HistorySelection, Long> {
    override suspend fun invoke(input: HistorySelection): Long {
        return repository.saveHistory(input)
    }
}