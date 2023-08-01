package com.jimmy.currency.domain.usecase

import com.jimmy.core_arch.domain.ISuspendableUseCase
import com.jimmy.core_data.domain.repository.HistoryRepository
import com.jimmy.core_database.entity.HistorySelection
import javax.inject.Inject

class GetHistorySelectionUseCase @Inject constructor(
    private val repository: HistoryRepository
): ISuspendableUseCase.WithoutParams<List<HistorySelection>?> {
    override suspend fun invoke(): List<HistorySelection>? {
        return repository.getHistory()
    }
}