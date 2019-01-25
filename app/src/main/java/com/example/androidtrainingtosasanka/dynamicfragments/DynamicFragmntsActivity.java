package com.example.androidtrainingtosasanka.dynamicfragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.adapters.DynamicAdapter;

public class DynamicFragmntsActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    DynamicAdapter dynamicAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);
        findViews();
        setAdapter();
    }

    private void findViews() {
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }

    private void setAdapter() {
        viewPager.setOffscreenPageLimit(2);
        dynamicAdapter = new DynamicAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(dynamicAdapter);
        tabLayout.setupWithViewPager(viewPager);
        setupTabNames();

    }

    private void setupTabNames() {
        tabLayout.getTabAt(0).setText("Fragment1");
        tabLayout.getTabAt(1).setText("Fragment2");
        tabLayout.getTabAt(2).setText("Fragment3");
    }
}
