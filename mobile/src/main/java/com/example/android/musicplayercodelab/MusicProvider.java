/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.musicplayercodelab;

import android.media.MediaMetadata;
import android.media.browse.MediaBrowser;
import android.util.Log;

import com.example.android.musicplayercodelab.model.Track;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

class MusicProvider {

    enum State {
        NON_INITIALIZED, INITIALIZING, INITIALIZED
    }

    private static final String TAG = "MusicProvider";
    private volatile State mCurrentState = State.NON_INITIALIZED;
    public static final String CUSTOM_METADATA_TRACK_SOURCE = "__SOURCE__";
    private static final TreeMap<String, MediaMetadata> music = new TreeMap<>();

    public static String getRoot() {
        return "";
    }

    public static List<MediaBrowser.MediaItem> getMediaItems() {
        List<MediaBrowser.MediaItem> result = new ArrayList<>();
        for (MediaMetadata metadata: music.values()) {
            result.add(new MediaBrowser.MediaItem(metadata.getDescription(),
                    MediaBrowser.MediaItem.FLAG_PLAYABLE));
        }
        return result;
    }

    public static MediaMetadata getMetadata(String mediaId) {
        MediaMetadata metadata = music.get(mediaId);
        return metadata;
    }


    public static String getPreviousSong(String currentMediaId) {
        String prevMediaId = music.lowerKey(currentMediaId);
        if (prevMediaId == null) {
            prevMediaId = music.firstKey();
        }
        return prevMediaId;
    }

    public static String getNextSong(String currentMediaId) {
        String nextMediaId = music.higherKey(currentMediaId);
        if (nextMediaId == null) {
            nextMediaId = music.firstKey();
        }
        return nextMediaId;
    }

    public boolean isInitialized() {
        return mCurrentState == State.INITIALIZED;
    }

    public interface Callback {
        void onMusicCatalogReady(boolean success);
    }

    public void retrieveMediaAsync(final Callback callback) {
        Log.d(TAG, "retrieveMediaAsync: ");
        if (mCurrentState == State.INITIALIZED) {
            // Nothing to do, execute callback immediately
            callback.onMusicCatalogReady(true);
            return;
        }

        if (mCurrentState == State.NON_INITIALIZED) {
            mCurrentState = State.INITIALIZING;
            getMusicList("acoustic").subscribe(new Subscriber<List<MediaMetadata>>() {
                @Override
                public void onCompleted() {
                    Log.d(TAG, "onCompleted: ");
                }

                @Override
                public void onError(Throwable e) {
                    Log.e(TAG, "onError: ",e.fillInStackTrace() );
                    if (mCurrentState != State.INITIALIZED) {
                        // Something bad happened, so we reset state to NON_INITIALIZED to allow
                        // retries (eg if the network connection is temporary unavailable)
                        mCurrentState = State.NON_INITIALIZED;
                        callback.onMusicCatalogReady(false);
                    }
                }
                @Override
                public void onNext(List<MediaMetadata> mediaMetadatas) {
                    mCurrentState = State.INITIALIZED;
                    callback.onMusicCatalogReady(true);
                }
            });
        }
    }

    public Observable<List<MediaMetadata>> getMusicList(String genreName) {
        ApiService apiService=provideApiService(provideRestAdapter());
        return apiService.getGenreList(genreName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .concatMap(new Func1<List<Track>, Observable<Track>>() {
                    @Override
                    public Observable<Track> call(List<Track> tracks) {
                        return Observable.from(tracks);
                    }
                })
                .concatMap(new Func1<Track, Observable<MediaMetadata>>() {
                    @Override
                    public Observable<MediaMetadata> call(Track track) {
                        MediaMetadata metadata=getTrackMediaMetadata(track);
                        String musicId = metadata.getString(MediaMetadata.METADATA_KEY_MEDIA_ID);
                        music.put(musicId, metadata);
                        return Observable.just(metadata);
                    }
                })
                .toList();
    }
    Retrofit provideRestAdapter() {
        return  new Retrofit.Builder()
                .baseUrl("http://api-v2.hearthis.at/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    ApiService provideApiService(Retrofit restAdapter) {
        return restAdapter.create(ApiService.class);
    }

    private MediaMetadata getTrackMediaMetadata(Track track) {
        return
                new MediaMetadata.Builder()
                        .putString(MediaMetadata.METADATA_KEY_MEDIA_ID, track.getId())
                        .putString(CUSTOM_METADATA_TRACK_SOURCE, track.getStream_url())
                        .putLong(MediaMetadata.METADATA_KEY_DURATION, Long.parseLong(track.getDuration()) * 1000)
                        .putString(MediaMetadata.METADATA_KEY_GENRE, track.getGenre())
                        .putString(MediaMetadata.METADATA_KEY_ALBUM_ART_URI, track.getArtwork_url())
                        .putString(MediaMetadata.METADATA_KEY_DISPLAY_ICON_URI, track.getArtwork_url())
                        .putString(MediaMetadata.METADATA_KEY_TITLE, track.getTitle())
                        .build();
    }
}