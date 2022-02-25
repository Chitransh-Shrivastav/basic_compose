package com.live.cs007s.jetpack_compose.network

import com.live.cs007s.jetpack_compose.model.Poster
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET

interface RetrofitService {

  @GET("response.json")
  suspend fun fetchPosterList(): ApiResponse<List<Poster>>
}
