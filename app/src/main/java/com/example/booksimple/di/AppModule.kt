package com.example.booksimple.di

import com.example.booksimple.movies.data.repository.MovieRepository
import com.example.booksimple.movies.data.repository.MovieRepositoryImpl
import com.example.booksimple.movies.domain.GetMoviesUseCase
import com.example.booksimple.movies.domain.GetMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

class AppModule {




    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt{

        @Binds
        @Singleton
        fun provideMovieRepository(useCase: MovieRepositoryImpl): MovieRepository

        @Binds
        @Singleton
        fun provideGetMoviesUseCase(useCase: GetMoviesUseCaseImpl): GetMoviesUseCase
    }
}