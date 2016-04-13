package com.example.paco.myapp1application;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Paco on 11/04/2016.
 */
public class SocialListFragment extends ListFragment {
    static String KEY_POSITION = "position";
    public static SocialListFragment list=null;
    // database helper
    private SocialItemDataHelper mDatabaseHelper;

    // database items list
    private List<SocialItem> mSocialItems;

    // list adapter
    private SocialListViewAdapter mAdapter;

    public SocialListFragment(){
        super();
    }

    public static SocialListFragment newInstance(int position) {

        //if (list == null) {
            Bundle args = new Bundle();
            args.putInt(KEY_POSITION, position);
            SocialListFragment list = new SocialListFragment();
            list.setArguments(args);
        //}
        return list;
    }

    /*@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // set the title for the action bar
        final ActionBar actionBar = activity.getActionBar();
        actionBar.setTitle(R.string.database_demo_title);
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // initialize the database helper
        mDatabaseHelper = new SocialItemDataHelper(getActivity());

        // initialize the icons map
        Map<String, Drawable> iconsMap = new HashMap<String, Drawable>();
        Resources resources = getResources();
        iconsMap.put(getString(R.string.aim_launcherim), resources.getDrawable(R.drawable.ic_launcher, getActivity().getTheme()));
        iconsMap.put(getString(R.string.beic_launcherbo), resources.getDrawable(R.drawable.ic_launcher, getActivity().getTheme()));
        iconsMap.put(getString(R.string.youtic_launcherube), resources.getDrawable(R.drawable.ic_launcher, getActivity().getTheme()));

        // initialize the items list
        //mSocialItems = mDatabaseHelper.getAllItems();

        // initialize and set the list adapter
        mSocialItems = new ArrayList<SocialItem>();
        mAdapter = new SocialListViewAdapter(this.getActivity(), mSocialItems, iconsMap);
        setListAdapter(mAdapter);

        // start an AsyncTask for loading the items from the database
        AsyncTask<String, Integer, List<SocialItem>> loader = new AsyncTask<String, Integer, List<SocialItem>>() {

            @Override
            protected List<SocialItem> doInBackground(String... params) {
                List<SocialItem> items = mDatabaseHelper.getAllItems();

                if(items.size() == 0) {
                    for(String title : params) {
                        items.add(new SocialItem(title));
                    }

                    // add the items to the database
                    mDatabaseHelper.addItems(items);
                }

                // sort the list
                Collections.sort(items);

                return items;
            }

            @Override
            protected void onPostExecute(List<SocialItem> items) {
                for(SocialItem item : items) {
                    mSocialItems.add(item);
                    mAdapter.notifyDataSetChanged();
                }
            }
        };

        Set<String> set = iconsMap.keySet();
        loader.execute(set.toArray(new String[set.size()]));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState){
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.listview_item, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve the item
        SocialItem item = mSocialItems.get(position);

        // update the clicks counter
        item.increaseNumClicks();

        // notify the adapter
        mAdapter.notifyDataSetChanged();

        // update the database
        mDatabaseHelper.updateItem(item);
    }
}
