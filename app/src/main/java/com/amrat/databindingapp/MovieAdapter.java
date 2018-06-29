package com.amrat.databindingapp;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amrat.databindingapp.databinding.ItemMovieBinding;
import com.amrat.databindingapp.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private ObservableArrayList<Movie> mMoviesObservableArrayList;
    private OnItemClickListener mOnItemClickListener;

    public MovieAdapter(ObservableArrayList<Movie> movieObservableArrayList, OnItemClickListener onItemClickListener) {
        this.mMoviesObservableArrayList = movieObservableArrayList;
        this.mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_movie, parent, false);
        return new MovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.binding.setMovie(mMoviesObservableArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return mMoviesObservableArrayList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {

        private ItemMovieBinding binding;

        public MovieViewHolder(ItemMovieBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
            this.binding.setVariable(BR.onClickListener, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}