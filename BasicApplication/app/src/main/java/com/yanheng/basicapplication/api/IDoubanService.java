package com.yanheng.basicapplication.api;

import com.yanheng.basicapplication.movie.HotMoviesData;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IDoubanService {
    String BASE_URL = "https://api.douban.com/";

    @GET("v2/movie/in_theaters")
    Call<HotMoviesData> searchHotMovies();

}
