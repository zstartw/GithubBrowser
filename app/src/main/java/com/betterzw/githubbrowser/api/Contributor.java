package com.betterzw.githubbrowser.api;

/**
 * Created by betterzw on 12/15/17.
 */

public class Contributor {
    public final String login;
    public final int contributions;
    public final String url;

    public Contributor(String login, int contributions, String url) {
        this.login = login;
        this.contributions = contributions;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Contributor{" +
                "login='" + login + '\'' +
                ", contributions=" + contributions +
                ", url='" + url + '\'' +
                '}';
    }
}
