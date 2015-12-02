package com.example.android.musicplayercodelab.model;

public class User
{
    private String id;

    private String avatar_url;

    private String username;

    private String permalink;

    private String permalink_url;

    private String uri;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getAvatar_url ()
    {
        return avatar_url;
    }

    public void setAvatar_url (String avatar_url)
    {
        this.avatar_url = avatar_url;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getPermalink ()
    {
        return permalink;
    }

    public void setPermalink (String permalink)
    {
        this.permalink = permalink;
    }

    public String getPermalink_url ()
    {
        return permalink_url;
    }

    public void setPermalink_url (String permalink_url)
    {
        this.permalink_url = permalink_url;
    }

    public String getUri ()
    {
        return uri;
    }

    public void setUri (String uri)
    {
        this.uri = uri;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", avatar_url = "+avatar_url+", username = "+username+", permalink = "+permalink+", permalink_url = "+permalink_url+", uri = "+uri+"]";
    }
}