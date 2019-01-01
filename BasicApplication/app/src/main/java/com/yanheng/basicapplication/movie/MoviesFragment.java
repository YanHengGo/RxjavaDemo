package com.yanheng.basicapplication.movie;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yanheng.basicapplication.L;
import com.yanheng.basicapplication.R;
import com.yanheng.basicapplication.api.DoubanManager;
import com.yanheng.basicapplication.api.IDoubanService;
import com.yanheng.basicapplication.beans.HotMoviesData;
import com.yanheng.basicapplication.beans.MovieItem;
import com.yanheng.basicapplication.movieDetail.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment implements MoviesContract.View{

    private List<MovieItem> movieItems = new ArrayList<>();
    private RecyclerView recyclerView;
    private MoviesAdapter moviesAdapter;
    private MoviesContract.Presenter presentor;


    public MoviesFragment() {
        L.d();
        // Required empty public constructor
    }
    public static MoviesFragment newInstance(){
        L.d();
        return new MoviesFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d();
        if(presentor!=null){
            presentor.start();
        }
        L.d();
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
        L.d();
        if(recyclerView == null)return;
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity().getApplicationContext() , 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        moviesAdapter = new MoviesAdapter(movieItems, getContext(), R.layout.recycleview_movie_view);
        recyclerView.setAdapter(moviesAdapter);
        L.d();
    }

    private void loadMovies(Callback<HotMoviesData> callback){
        L.d();
        IDoubanService iDoubanService = DoubanManager.createDoubanService();
        iDoubanService.searchHotMovies().enqueue(callback);
        L.d();
    }

    @Override
    public void showMovies(List<MovieItem> movieItems) {
        L.d();
        moviesAdapter.setData(movieItems);
        L.d();
    }

    @Override
    public void showNoMovies() {
        L.d();
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        L.d();
        if(getView()==null)return;
        L.d();
        ProgressBar progressBar = (ProgressBar) getView().findViewById(R.id.hotmovies_loading);
        if(active){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void setPresentor(MoviesContract.Presenter presentor) {
        L.d();
        this.presentor = presentor;
        L.d();
    }

    class MoviesAdapter extends RecyclerView.Adapter<MoviesViewHolder> {
        private List<MovieItem> movieItems ;
        private final Context context;

        @LayoutRes
        private final int layoutResId ;

        public MoviesAdapter(List<MovieItem> movieItems,@NonNull Context context,@LayoutRes int layoutResId) {
            L.d();
            this.movieItems = movieItems;
            this.context = context;
            this.layoutResId = layoutResId;
        }

        @NonNull
        @Override
        public MoviesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            L.d();
            View view = LayoutInflater.from(context)
                    .inflate(layoutResId,parent,false);
            return new MoviesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MoviesViewHolder moviesViewHolder, int position) {
            L.d();
            if(moviesViewHolder == null)return;
            moviesViewHolder.updateMovie(movieItems.get(position));
        }

        @Override
        public int getItemCount() {
            return movieItems.size();
        }
        public void setData(List<MovieItem> movieItems){
            L.d();
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

        public MoviesViewHolder(@NonNull final View itemView) {
            super(itemView);
            L.d();
            movieImageView = ((ImageView) itemView.findViewById(R.id.movie_imageview));
            movieTitle = ((TextView) itemView.findViewById(R.id.movie_title));
            movieStar = ((RatingBar) itemView.findViewById(R.id.movie_star));
            movieScore = (TextView)itemView.findViewById(R.id.movie_score);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(movieItem==null || itemView == null)return;
                    Context context = itemView.getContext();
                    if(context==null)return;
                    Intent intent = new Intent(context , MovieDetailActivity.class);
                    intent.putExtra("movie",movieItem);

                    if(context instanceof Activity){
                        Activity activity = (Activity)context;

                        Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity,movieImageView,"cover").toBundle();
                        ActivityCompat.startActivity(activity,intent,bundle);
                    }

                }
            });
            L.d();
        }

        public void updateMovie(MovieItem movieItem){
            L.d();
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


            L.d();
        }

    }
}
