package com.yanheng.basicapplication.movie;

import android.support.annotation.NonNull;

import com.yanheng.basicapplication.L;
import com.yanheng.basicapplication.api.IDoubanService;
import com.yanheng.basicapplication.beans.HotMoviesData;
import com.yanheng.basicapplication.beans.MovieItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesPresenter implements MoviesContract.Presenter {

    private final MoviesContract.View movieView;
    private final IDoubanService iDoubanService;

    private boolean isFirstLoad = true;

    public MoviesPresenter(@NonNull MoviesContract.View movieView, @NonNull IDoubanService iDoubanService) {
        L.d();
        this.movieView = movieView;
        this.iDoubanService = iDoubanService;

        this.movieView.setPresentor(this);
        L.d();
    }

    @Override
    public void loadMovies(boolean forceupdate) {
        L.d();
        loadMovies(forceupdate || isFirstLoad , true);
        isFirstLoad = false;
        L.d();
    }

    private void loadMovies(boolean forceupdate,final boolean showLoading) {
        L.d();
        if(showLoading){
            movieView.setLoadingIndicator(showLoading);
        }
        if(forceupdate){
            iDoubanService.searchHotMovies().enqueue(new Callback<HotMoviesData>() {
                @Override
                public void onResponse(Call<HotMoviesData> call, Response<HotMoviesData> response) {
                    L.d();
                    List<MovieItem> movieItems = response.body().getMovieItems();
                    if(showLoading){
                        movieView.setLoadingIndicator(false);
                    }
                    processMovies(movieItems);
                    L.d();
                }

                @Override
                public void onFailure(Call<HotMoviesData> call, Throwable t) {
                    L.d();

                }
            });
        }
        L.d();
    }

    private void processMovies(List<MovieItem> movieItems) {
        L.d();
        if(movieItems.isEmpty()){
            movieView.showNoMovies();
        }else{
            movieView.showMovies(movieItems);
        }
        L.d();
    }

    @Override
    public void start() {
        L.d();
        loadMovies(false);
        L.d();
    }
}
