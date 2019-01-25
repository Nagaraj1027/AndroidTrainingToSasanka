package com.example.androidtrainingtosasanka.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.androidtrainingtosasanka.dynamicfragments.FragmentOne;
import com.example.androidtrainingtosasanka.dynamicfragments.FragmentSecond;
import com.example.androidtrainingtosasanka.dynamicfragments.FragmentThird;

/**
 * Created by npambhala on 8/2/2017.
 */

public class DynamicAdapter extends FragmentStatePagerAdapter {
    int value;

    public DynamicAdapter(FragmentManager fm, int value) {
        super(fm);
        this.value = value;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return FragmentOne.newInstance("param1", "param2");
            case 1:
                return FragmentSecond.newInstance("param1", "param2");
            case 2:
                return FragmentThird.newInstance("param1", "param2");
        }
        return null;
    }


    @Override
    public int getCount() {
        return value;
    }
}
