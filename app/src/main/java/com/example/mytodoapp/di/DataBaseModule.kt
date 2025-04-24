package com.example.mytodoapp.di

import android.content.Context
import androidx.room.Room
import com.example.mytodoapp.data.local.TodoDatabase
import com.example.mytodoapp.data.local.dao.TodoDao
import com.example.mytodoapp.data.local.repository.TodoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            name = "todo_local_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideTodoDao(db: TodoDatabase): TodoDao = db.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository = TodoRepository(todoDao = todoDao)
}