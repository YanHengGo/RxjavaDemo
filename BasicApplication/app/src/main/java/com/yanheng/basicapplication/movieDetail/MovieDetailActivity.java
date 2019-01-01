package com.yanheng.basicapplication.movieDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.yanheng.basicapplication.ConstContent;
import com.yanheng.basicapplication.L;
import com.yanheng.basicapplication.R;
import com.yanheng.basicapplication.beans.MovieItem;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    private MovieDetailContract.Presenter presenter;
    private String movieInfo;
    private String movieAlt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initView();

    }

    private void initView() {
        new MovieDetailPresenter((MovieItem) getIntent().getSerializableExtra("movie"),this);
        ViewPager viewPager = (ViewPager) findViewById(R.id.moviedetail_viewpager);
        setupViewPager(viewPager);
        TabLayout tablayout = (TabLayout) findViewById(R.id.moviedetail_tablayout);
        if (tablayout != null) {
            tablayout.addTab(tablayout.newTab());
            tablayout.addTab(tablayout.newTab());
            tablayout.setupWithViewPager(viewPager);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        presenter.loadMovieAlt();
        presenter.loadMovieInfo();
        MovieDetailPagerAdapter movieDetailPagerAdapter = new MovieDetailPagerAdapter(getSupportFragmentManager());
        MovieDetailFragment movieInfoFragment = MovieDetailFragment.createInstance(movieInfo,ConstContent.TYPE_MOVIE_INFO);
        MovieDetailFragment movieAltFragment = MovieDetailFragment.createInstance(movieAlt,ConstContent.TYPE_MOVIE_WEBSITE);

        movieDetailPagerAdapter.addFragment(movieInfoFragment,getString(R.string.movie_info));
        movieDetailPagerAdapter.addFragment(movieAltFragment,getString(R.string.movie_description));

        viewPager.setAdapter(movieDetailPagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.start();
    }

    static class MovieDetailPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitles = new ArrayList<>();
        public MovieDetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment ,String title){
            fragments.add(fragment);
            fragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitles.get(position);
        }
    }



    @Override
    public void showCollapsingToolbar(String title) {
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.moviedetail_collaps_toolbar);
        collapsingToolbar.setTitle(title);
    }

    @Override
    public void showPicassoImage(String largeImagePath) {
        ImageView movieImage = (ImageView) findViewById(R.id.moviedetail_image);
        Picasso.with(movieImage.getContext())
                .load(largeImagePath)
                .into(movieImage);
    }

    @Override
    public void setMovieInfoToFragment(String movieInfo) {
        MovieDetailActivity.this.movieInfo = movieInfo;
    }

    @Override
    public void setMoieAltToFragment(String movieAlt) {
        MovieDetailActivity.this.movieAlt = movieAlt;
    }

    @Override
    public void setPresentor(MovieDetailContract.Presenter presentor) {
        presenter = checkNotNull(presentor);
    }

}
