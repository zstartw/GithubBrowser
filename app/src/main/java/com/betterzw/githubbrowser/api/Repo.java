package com.betterzw.githubbrowser.api;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhengwu on 4/11/18.
 */
public class Repo {
    public static final int UNKNOWN_ID = -1;
    public final int id;
    @SerializedName("name")
    @NonNull
    public final String name;
    @SerializedName("full_name")
    public final String fullName;
    @SerializedName("description")
    public final String description;
    @SerializedName("stargazers_count")
    public final int stars;
    @SerializedName("forks_count")
    public final int forks;
    public final String language;
    @SerializedName("owner")
//    @Embedded(prefix = "owner_")
//    @NonNull
    public final Owner owner;

    public Repo(int id, String name, String fullName, String description, Owner owner, int stars, int forks,
                String language) {
        this.id = id;
        this.name = name;
        this.fullName = fullName;
        this.description = description;
        this.owner = owner;
        this.stars = stars;
        this.forks = forks;
        this.language = language;
    }

    public static class Owner {
        @SerializedName("login")
        @NonNull
        public final String login;
        @SerializedName("url")
        public final String url;
        @SerializedName("avatar_url")
        public final String avatorUrl;

        public Owner(@NonNull String login, String url, String avatorUrl) {
            this.login = login;
            this.url = url;
            this.avatorUrl = avatorUrl;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Owner owner = (Owner) o;

            if (login != null ? !login.equals(owner.login) : owner.login != null) {
                return false;
            }
            return url != null ? url.equals(owner.url) : owner.url == null;
        }

        @Override
        public int hashCode() {
            int result = login != null ? login.hashCode() : 0;
            result = 31 * result + (url != null ? url.hashCode() : 0);
            return result;
        }
    }
}
