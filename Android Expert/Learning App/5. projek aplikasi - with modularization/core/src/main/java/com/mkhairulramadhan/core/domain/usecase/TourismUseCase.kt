package com.mkhairulramadhan.core.domain.usecase

import com.mkhairulramadhan.core.data.Resource
import com.mkhairulramadhan.core.domain.model.Tourism
import kotlinx.coroutines.flow.Flow

interface TourismUseCase {
    fun getAllTourism(): Flow<Resource<List<Tourism>>>
    fun getFavoriteTourism(): Flow<List<Tourism>>
    fun setFavoriteTourism(tourism: Tourism, state: Boolean)
}