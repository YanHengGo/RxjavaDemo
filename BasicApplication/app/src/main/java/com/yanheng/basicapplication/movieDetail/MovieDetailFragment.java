package com.yanheng.basicapplication.movieDetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yanheng.basicapplication.ConstContent;
import com.yanheng.basicapplication.L;
import com.yanheng.basicapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragment extends Fragment {

    private String url;
    private String linkUrl;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment createInstance (String info , int type){
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ConstContent.INTENT_EXTRA_FRAGMENT_INFO,info);
        bundle.putInt(ConstContent.INTENT_EXTRA_FRAGMENT_TYPE,type);
        movieDetailFragment.setArguments(bundle);
        return movieDetailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        TextView tvinfo = (TextView) view.findViewById(R.id.tvinfo);
        tvinfo.setText(getArguments().getString(ConstContent.INTENT_EXTRA_FRAGMENT_INFO));

        if(ConstContent.INTENT_EXTRA_WEBSITE_URL == getArguments().getString(ConstContent.INTENT_EXTRA_FRAGMENT_TYPE)){
            linkUrl = tvinfo.getText().toString();
            tvinfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    L.d();
                }
            });
        }
        return view;
    }

}
