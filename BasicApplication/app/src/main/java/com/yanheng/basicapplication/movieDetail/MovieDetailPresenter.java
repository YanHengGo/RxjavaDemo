package com.yanheng.basicapplication.movieDetail;

import android.app.Activity;
import android.content.res.Resources;

import com.yanheng.basicapplication.R;
import com.yanheng.basicapplication.beans.MovieItem;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    private MovieItem movieItem;

    private MovieDetailContract.View movieDetailView;

    public MovieDetailPresenter(MovieItem movieItem, MovieDetailContract.View movieDetailView) {
        this.movieItem = movieItem;
        this.movieDetailView = movieDetailView;
        this.movieDetailView.setPresentor(this);
    }

    @Override
    public void loadMovieInfo() {
        StringBuilder movieBuilder = new StringBuilder();
        Resources resources = ((MovieDetailActivity) movieDetailView).getResources();
        movieBuilder.append(resources.getString(R.string.movie_directors));
        for(MovieItem.DirectorsBean directorsBean : movieItem.getDirectors()){
            movieBuilder.append(directorsBean.getName());
            movieBuilder.append(" ");
        }
        movieBuilder.append("\n");
        movieBuilder.append(resources.getString(R.string.movie_casts));
        for(MovieItem.CastsBean directorsBean : movieItem.getCasts()){
            movieBuilder.append(directorsBean.getName());
            movieBuilder.append(" ");
        }
        movieBuilder.append("\n");

        movieDetailView.setMovieInfoToFragment(movieBuilder.toString());
    }

    @Override
    public void loadMovieAlt() {
        movieDetailView.setMoieAltToFragment(movieItem.getAlt());
    }


    @Override
    public void start() {
        showMovieDetail();
    }

    private void showMovieDetail() {
        movieDetailView.showCollapsingToolbar(movieItem.getTitle());
        movieDetailView.showPicassoImage(movieItem.getImages().getLarge());
    }
}
