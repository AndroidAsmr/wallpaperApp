package com.hadirahimi.wallpaper.utils.di

import android.app.WallpaperManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleUtils
{
    @Provides
    @Singleton
    fun provideWallpaperManager(@ApplicationContext  context:Context) : WallpaperManager = WallpaperManager.getInstance(context)
    
}










