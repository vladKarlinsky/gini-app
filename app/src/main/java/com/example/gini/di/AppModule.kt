package com.example.gini.di
import android.content.Context
import androidx.room.Room.databaseBuilder
import com.example.gini.data.dao.GiniDao
import com.example.gini.data.database.GiniDatabase
import com.example.gini.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): GiniDatabase {
        return databaseBuilder(
            appContext,
            GiniDatabase::class.java,
            "gini_database"
        ).build()
    }

    @Provides
    fun provideGiniDao(database: GiniDatabase): GiniDao {
        return database.giniDao()
    }

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl("https://pixabay.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
