package com.hadirahimi.wallpaper.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hadirahimi.wallpaper.data.server.UnsplashApi
import com.hadirahimi.wallpaper.data.model.UnsplashPhoto

class UnsplashPagingSource(private val unsplashApi: UnsplashApi) : PagingSource<Int, UnsplashPhoto>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        return try {
            val page = params.key ?: 1
            val photos = unsplashApi.getPhotos(page = page)
            LoadResult.Page(
                data = photos,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (photos.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition
    }
}