package com.yanheng.basicapplication.movie;

import com.yanheng.basicapplication.BasePresenter;
import com.yanheng.basicapplication.BaseView;
import com.yanheng.basicapplication.beans.MovieItem;

import java.util.List;

public interface MoviesContract {
    interface View extends BaseView<Presenter>{
        void showMovies(List<MovieItem> movieItems);

        void showNoMovies();

        void setLoadingIndicator(boolean active);
    }

    interface Presenter extends BasePresenter {
        void loadMovies(boolean forceupdate);
    }
}
