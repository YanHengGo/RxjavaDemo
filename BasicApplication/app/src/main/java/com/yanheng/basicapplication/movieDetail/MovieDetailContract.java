package com.yanheng.basicapplication.movieDetail;

import com.yanheng.basicapplication.BasePresenter;
import com.yanheng.basicapplication.BaseView;

public interface MovieDetailContract {
    interface View extends BaseView<Presenter> {
        void showCollapsingToolbar(String title);
        void showPicassoImage(String largeImagePath);
        void setMovieInfoToFragment(String movieInfo);
        void setMoieAltToFragment(String movieAlt);
    }


    interface Presenter extends BasePresenter {
        void loadMovieInfo();
        void loadMovieAlt();
    }
}
