package com.example.paco.myapp1application;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;
public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewPager pager=(ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new SampleAdapter(this, getSupportFragmentManager()));
    }
}
