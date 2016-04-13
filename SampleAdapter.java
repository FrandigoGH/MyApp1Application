package com.example.paco.myapp1application;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Paco on 11/04/2016.
 */
public class SampleAdapter extends FragmentPagerAdapter {
    Context ctxt = null;

    public SampleAdapter(Context ctxt, android.support.v4.app.FragmentManager mgr) {
        super(mgr);
        this.ctxt = ctxt;
    }
    @Override
    public int getCount() {
        return(10);
    }

    @Override
    public Fragment getItem(int position) {

            if (position == 6) {
                return (SocialListFragment.newInstance(position));
            }
            else if (position == 2) {
                return (OtherFragment.newInstance(position));
            }
            return (EditorFragment.newInstance(position));

    }

    @Override
    public String getPageTitle(int position) {
        return(EditorFragment.getTitle(ctxt, position));
    }
}
