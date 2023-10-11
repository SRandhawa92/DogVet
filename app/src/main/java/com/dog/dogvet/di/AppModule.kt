package com.dog.dogvet.di

import android.content.Context
import androidx.room.Room
import com.dog.dogvet.api.ApiService
import com.dog.dogvet.data.MessagesDatabase
import com.dog.dogvet.data.MessagesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiService() = ApiService()

    @Provides
    @Singleton
    fun provideMessagesDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            MessagesDatabase::class.java,
            "messages_database",
        ).build()

    @Provides
    @Singleton
    fun provideMessagesRepository(database: MessagesDatabase, apiService: ApiService) =
        MessagesRepository(
            database = database,
            apiService = apiService,
            ioDispatcher = Dispatchers.IO,
        )
}
