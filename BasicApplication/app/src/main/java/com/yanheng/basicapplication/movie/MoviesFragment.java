package com.yanheng.basicapplication.movie;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
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
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;


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
                moviesAdapter.setData(movieItems);
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
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        recyclerView = ((RecyclerView) view.findViewById(R.id.hotmovies_recyclerview));
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(recyclerView == null)return;
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext() , 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        moviesAdapter = new MoviesAdapter(movieItems, getContext(), R.layout.recycleview_movie_view);
        recyclerView.setAdapter(moviesAdapter);
    }

    private void loadMovies(Callback<HotMoviesData> callback){
        L.d();
        IDoubanService iDoubanService = DoubanManager.createDoubanService();
        iDoubanService.searchHotMovies().enqueue(callback);
        L.d();
    }
    class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
        private List<MovieItem> movieItems ;
        private final Context context;

        @LayoutRes
        private final int layoutResId ;

        public MoviesAdapter(List<MovieItem> movieItems,@NonNull Context context,@LayoutRes int layoutResId) {
            this.movieItems = movieItems;
            this.context = context;
            this.layoutResId = layoutResId;
        }

        @NonNull
        @Override
        public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(context)
                    .inflate(layoutResId,parent,false);
            return new MoviesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int position) {
            if(moviesViewHolder == null)return;
            moviesViewHolder.updateMovie(movieItems.get(position));
        }

        @Override
        public int getItemCount() {
            return movieItems.size();
        }
        public void setData(List<MovieItem> movieItems){
            if(movieItems==null)return;
            this.movieItems = movieItems;
            notifyDataSetChanged();
        }
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        private final ImageView movieImageView;
        private final TextView movieTitle;
        private final RatingBar movieStar;
        private final TextView movieScore;
        private MovieItem movieItem;

        public MoviesViewHolder(@NonNull View itemView) {
            super(itemView);
            movieImageView = ((ImageView) itemView.findViewById(R.id.movie_imageview));
            movieTitle = ((TextView) itemView.findViewById(R.id.movie_title));
            movieStar = ((RatingBar) itemView.findViewById(R.id.movie_star));
            movieScore = (TextView)itemView.findViewById(R.id.movie_score);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }

        public void updateMovie(MovieItem movieItem){
            if(movieItem == null) return;
            Context context = itemView.getContext();
            this.movieItem = movieItem;

            //load picture
            Picasso.with(context)
                    .load(movieItem.getImages().getLarge())
                    .placeholder(R.mipmap.ic_launcher)
                    .into(movieImageView);
            movieTitle.setText(movieItem.getTitle());
            double average = movieItem.getRating().getAverage()/2;
            if(average == 0.0){
                movieStar.setVisibility(View.GONE);
                movieScore.setText(R.string.has_no_data);
            }else{
                movieStar.setVisibility(View.VISIBLE);
                movieStar.setRating((float)average);
                movieScore.setText(String.valueOf(average));
            }


        }

    }
}
