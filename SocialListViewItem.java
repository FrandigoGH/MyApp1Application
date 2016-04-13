package com.example.paco.myapp1application;

import android.graphics.drawable.Drawable;

/**
 * Created by Paco on 11/04/2016.
 */
public class SocialListViewItem {
    public final Drawable icon;       // the drawable for the ListView item ImageView
    public final String title;        // the text for the ListView item title
    public final String description;  // the text for the ListView item description

    public SocialListViewItem(Drawable icon, String title, String description) {
        this.icon = icon;
        this.title = title;
        this.description = description;
    }
}
