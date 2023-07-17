package com.hadirahimi.wallpaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.hadirahimi.wallpaper.data.server.UnsplashApi
import com.hadirahimi.wallpaper.ui.UnsplashPagingSource
import com.hadirahimi.wallpaper.data.model.UnsplashPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class UnsplashViewModel @Inject constructor(private val unsplashApi: UnsplashApi) : ViewModel() {
    val photos: Flow<PagingData<UnsplashPhoto>> = Pager(
        config = PagingConfig(
            pageSize = 40,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { UnsplashPagingSource(unsplashApi) }
    ).flow
}