package com.example.jalihara;

import android.app.Application;

public class UsernameGlobal extends Application {

    public String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String newusername) {
        this.username = newusername;
    }
}
