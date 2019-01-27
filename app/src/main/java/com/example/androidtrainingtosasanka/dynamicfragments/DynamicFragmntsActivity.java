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

        dynamicAdapter = new DynamicAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(dynamicAdapter);

        tabLayout.setupWithViewPager(viewPager);  //Attach TabLayout with viewpager

        viewPager.setOffscreenPageLimit(2);

        setupTabNames(3);  //Set

    }

    private void setupTabNames(int number) {
        for (int i = 0; i < number; i++) {
            tabLayout.getTabAt(i).setText("Fragment" + i);
        }
    }
}
