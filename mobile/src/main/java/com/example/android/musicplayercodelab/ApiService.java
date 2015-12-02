package com.example.android.musicplayercodelab;

import com.example.android.musicplayercodelab.model.Genre;
import com.example.android.musicplayercodelab.model.Track;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

public interface ApiService {
    @GET("categories/")
    Observable<List<Genre>> getAllGenres();

    @GET("categories/{genre}/")
    Observable<List<Track>> getGenreList(@Path("genre") String genre);
}