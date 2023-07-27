package com.jimmy.core_arch.di

import com.jimmy.core_arch.CoroutineDispatchers
import com.jimmy.core_arch.MyCoroutineDispatchers
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {
    @Singleton
    @Provides
    fun providesCoroutineDispatcher(): CoroutineDispatchers {
        return MyCoroutineDispatchers()
    }
}