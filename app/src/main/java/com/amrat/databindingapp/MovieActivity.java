package com.amrat.databindingapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.amrat.databindingapp.databinding.ActivityMovieBinding;
import com.amrat.databindingapp.model.Movie;
import com.amrat.databindingapp.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MovieActivity extends AppCompatActivity implements OnItemClickListener {

    private ObservableArrayList<Movie> mMoviesObservableArrayList;
    private RecyclerView mMovieRecyclerView;
    private MovieAdapter mMovieAdapter;
    private ActivityMovieBinding mActivityMovieBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMovieBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie);
        mMoviesObservableArrayList = new ObservableArrayList<>();
        mMovieAdapter = new MovieAdapter(mMoviesObservableArrayList, this);
        mMovieRecyclerView = mActivityMovieBinding.movieRecyclerview;
        mMovieRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        mMovieRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mMovieRecyclerView.setAdapter(mMovieAdapter);
        generateMovieList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add) {
            String description = "Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos. On a mission to collect all six Infinity Stones, Thanos plans to use the artifacts to inflict his twisted will on reality. The fate of the planet and existence itself has never been more uncertain as everything the Avengers have fought for has led up to this moment.";
            mMoviesObservableArrayList.add(new Movie("Avengers: Infinity War", description, Calendar.getInstance(), R.mipmap.img_infinity_war));
            mMovieAdapter.notifyItemInserted(mMoviesObservableArrayList.size() - 1);
            return true;
        } else if (id == R.id.action_change) {
            mMoviesObservableArrayList.get(1).setName("Guardians of the Galaxy");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void generateMovieList() {
        String description = "Iron Man, Thor, the Hulk and the rest of the Avengers unite to battle their most powerful enemy yet -- the evil Thanos. On a mission to collect all six Infinity Stones, Thanos plans to use the artifacts to inflict his twisted will on reality. The fate of the planet and existence itself has never been more uncertain as everything the Avengers have fought for has led up to this moment.";
        try {
            mMoviesObservableArrayList.add(new Movie("Ironman", description, DateUtils.parseDate("05-04-2008"), R.mipmap.img_ironman));
            mMoviesObservableArrayList.add(new Movie("The Guardians of the Galaxy", description, DateUtils.parseDate("14-06-2014"), R.mipmap.img_tgotg));
            mMoviesObservableArrayList.add(new Movie("Thor", description, DateUtils.parseDate("16-17-2012"), R.mipmap.img_thor));
            mMoviesObservableArrayList.add(new Movie("Avengers", description, DateUtils.parseDate("12-12-2012"), R.mipmap.img_avengers));
            mMoviesObservableArrayList.add(new Movie("Captain America: Winter Soldier", description, DateUtils.parseDate("12-12-2012"), R.mipmap.img_winter_soldier));
            mMoviesObservableArrayList.add(new Movie("Ant Man", description, DateUtils.parseDate("22-08-2015"), R.mipmap.img_antman));
            mMoviesObservableArrayList.add(new Movie("Deadpool", description, DateUtils.parseDate("19-11-2016"), R.mipmap.img_deadpool));
            mMoviesObservableArrayList.add(new Movie("Captain America: Civil War", description, DateUtils.parseDate("12-10-2014"), R.mipmap.img_civil_war));
            mMoviesObservableArrayList.add(new Movie("Doctor Strange", description, DateUtils.parseDate("03-09-2016"), R.mipmap.img_doctor_strange));
            mMoviesObservableArrayList.add(new Movie("Thor Ragnarok", description, DateUtils.parseDate("07-11-2017"), R.mipmap.img_ragnarok));
            mMoviesObservableArrayList.add(new Movie("Black Panther", description, DateUtils.parseDate("12-02-2018"), R.mipmap.img_black_panther));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("movie", (Parcelable) mMoviesObservableArrayList.get(position));
        startActivity(intent);
    }
}