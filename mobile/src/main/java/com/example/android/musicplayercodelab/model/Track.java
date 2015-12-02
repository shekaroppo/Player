package com.example.android.musicplayercodelab.model;

import com.google.gson.annotations.SerializedName;

public class Track {
    @SerializedName("comment_count")
    private String comment_count;

    @SerializedName("genre")
    private String genre;

    @SerializedName("playback_count")
    private String playback_count;

    @SerializedName("stream_url")
    private String stream_url;

    @SerializedName("favoritings_count")
    private String favoritings_count;

    @SerializedName("download_count")
    private String download_count;

    @SerializedName("uri")
    private String uri;

    @SerializedName("artwork_url")
    private String artwork_url;

    @SerializedName("downloadable")
    private String downloadable;

    @SerializedName("background_url")
    private String background_url;

    @SerializedName("id")
    private String id;

    @SerializedName("duration")
    private String duration;

    @SerializedName("title")
    private String title;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("favorited")
    private String favorited;

    @SerializedName("download_url")
    private String download_url;

    @SerializedName("description")
    private String description;

    @SerializedName("genre_slush")
    private String genre_slush;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("permalink_url")
    private String permalink_url;

    @SerializedName("waveform_data")
    private String waveform_data;

    @SerializedName("user")
    private User user;

    private String waveform_url;

    public String getComment_count() {
        return comment_count;
    }


    public String getGenre() {
        return genre;
    }

    public String getPlayback_count() {
        return playback_count;
    }


    public String getStream_url() {
        return stream_url;
    }

    public String getFavoritings_count() {
        return favoritings_count;
    }


    public String getDownload_count() {
        return download_count;
    }


    public String getUri() {
        return uri;
    }


    public String getArtwork_url() {
        return artwork_url;
    }


    public String getDownloadable() {
        return downloadable;
    }


    public String getBackground_url() {
        return background_url;
    }


    public String getId() {
        return id;
    }


    public String getDuration() {
        return duration;
    }


    public String getTitle() {
        return title;
    }


    public String getPermalink() {
        return permalink;
    }


    public String getFavorited() {
        return favorited;
    }


    public String getDownload_url() {
        return download_url;
    }


    public String getDescription() {
        return description;
    }


    public String getGenre_slush() {
        return genre_slush;
    }

    public String getCreated_at() {
        return created_at;
    }


    public String getUser_id() {
        return user_id;
    }


    public String getPermalink_url() {
        return permalink_url;
    }


    public String getWaveform_data() {
        return waveform_data;
    }


    public User getUser() {
        return user;
    }


    public String getWaveform_url() {
        return waveform_url;
    }


    @Override
    public String toString() {
        return "ClassPojo [comment_count = " + comment_count + ", genre = " + genre + ", playback_count = " + playback_count + ", stream_url = " + stream_url + ", favoritings_count = " + favoritings_count + ", download_count = " + download_count + ", uri = " + uri + ", artwork_url = " + artwork_url + ", downloadable = " + downloadable + ", background_url = " + background_url + ", id = " + id + ", duration = " + duration + ", title = " + title + ", permalink = " + permalink + ", favorited = " + favorited + ", download_url = " + download_url + ", description = " + description + ", genre_slush = " + genre_slush + ", created_at = " + created_at + ", user_id = " + user_id + ", permalink_url = " + permalink_url + ", waveform_data = " + waveform_data + ", user = " + user + ", waveform_url = " + waveform_url + "]";
    }
}