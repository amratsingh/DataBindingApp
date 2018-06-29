package com.amrat.databindingapp;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.amrat.databindingapp.databinding.ActivityMovieDetailBinding;
import com.amrat.databindingapp.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private Movie mMovie;
    private ActivityMovieDetailBinding mActivityMovieDetailBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMovieDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (getIntent().hasExtra("movie")) {
            mMovie = getIntent().getParcelableExtra("movie");
            mActivityMovieDetailBinding.setMovie(mMovie);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}