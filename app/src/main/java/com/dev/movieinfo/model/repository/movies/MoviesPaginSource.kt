package com.dev.movieinfo.model.repository.movies

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev.movieinfo.model.local.MoviesModel
import com.dev.movieinfo.model.service.ApiService

class MoviesPaginSource(val service:ApiService):PagingSource<Int,MoviesModel>() {
    override fun getRefreshKey(state: PagingState<Int, MoviesModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesModel> {
        val page = params.key ?: 0
        return try {
            Log.d("Movie list","Page: $page")
            val response=service.getMovies(page+1)
            var result= listOf<MoviesModel>()
            if(response.isSuccessful)
            {
                result=response.body()!!.results

            }
            LoadResult.Page(
                data = result,
                prevKey = if (page == 0) null else page - 1,
                nextKey =if(result.isEmpty()) null else  page + 1
            )
        }
        catch (ex:Exception)
        {
            LoadResult.Error(ex)
        }
    }
}