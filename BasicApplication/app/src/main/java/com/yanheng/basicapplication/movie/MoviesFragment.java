package com.yanheng.basicapplication.movie;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yanheng.basicapplication.L;
import com.yanheng.basicapplication.R;
import com.yanheng.basicapplication.api.DoubanManager;
import com.yanheng.basicapplication.api.IDoubanService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {

    private List<MovieItem> movieItems = new ArrayList<>();


    public MoviesFragment() {
        L.d();
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d();
        loadMovies(new Callback<HotMoviesData>() {
            @Override
            public void onResponse(Call<HotMoviesData> call, Response<HotMoviesData> response) {
                L.d();
                movieItems = response.body().getMovieItems();
                int size = movieItems.size();
                for(int i=0;i<size;i++){
                    L.d("title = " + movieItems.get(i).getTitle());
                }
            }

            @Override
            public void onFailure(Call<HotMoviesData> call, Throwable t) {
                L.d();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        L.d();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    private void loadMovies(Callback<HotMoviesData> callback){
        L.d();
        IDoubanService iDoubanService = DoubanManager.createDoubanService();
        iDoubanService.searchHotMovies().enqueue(callback);
        L.d();
    }

}
