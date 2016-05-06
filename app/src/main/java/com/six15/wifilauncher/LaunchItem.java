package com.six15.wifilauncher;

import android.content.Intent;

/**
 * Created by George on 4/22/2016.
 */
public class LaunchItem {
    public int imageResourceId;
    public String title;
    public String intentName;

    public LaunchItem(int imageResourceId, String title, String intentName){

        this.imageResourceId = imageResourceId;
        this.title = title;
        this.intentName = intentName;

    }
}
