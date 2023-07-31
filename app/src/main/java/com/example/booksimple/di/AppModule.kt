package com.example.booksimple.di

import com.example.booksimple.movies.data.remote.MoviesApi
import com.example.booksimple.movies.data.repository.MovieRepository
import com.example.booksimple.movies.data.repository.MovieRepositoryImpl
import com.example.booksimple.movies.domain.GetMoviesUseCase
import com.example.booksimple.movies.domain.GetMoviesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit{
        val baseUrl = "https://api-gate2.movieglu.com/"
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi{
        return retrofit.create(MoviesApi::class.java)
    }


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