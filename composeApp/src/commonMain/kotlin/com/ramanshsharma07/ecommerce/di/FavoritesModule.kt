package com.ramanshsharma07.ecommerce.di

import com.ramanshsharma07.ecommerce.core.data.repository.FakeFavoritesRepositoryImpl
import com.ramanshsharma07.ecommerce.core.domain.repository.FavoritesRepository
import com.ramanshsharma07.ecommerce.core.domain.use_cases.GetFavoritesIdsUseCase
import com.ramanshsharma07.ecommerce.core.domain.use_cases.ToggleFavoriteUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val favoritesModule = module {
    single<FavoritesRepository> { FakeFavoritesRepositoryImpl() }
    factoryOf(::GetFavoritesIdsUseCase)
    factoryOf(::ToggleFavoriteUseCase)
}