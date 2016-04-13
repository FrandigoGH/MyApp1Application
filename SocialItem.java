package com.example.paco.myapp1application;

/**
 * Created by Paco on 11/04/2016.
 */
public class SocialItem implements Comparable<SocialItem> {
    public final String title;    // the text for the ListView item title
    private long numClicks;        // the number of user clicks on this item

    public SocialItem(String title) {
        this.title = title;
        numClicks = 0;
    }

    public SocialItem(String title, long numClicks) {
        this.title = title;
        this.numClicks = numClicks;
    }

    public long getNumClicks() {
        return numClicks;
    }

    public long increaseNumClicks() {
        return ++numClicks;
    }

    @Override
    public int compareTo(SocialItem another) {
        long anotherNumClicks = another != null ? another.getNumClicks() : 0;

        return (int) (anotherNumClicks - numClicks);
    }
}
