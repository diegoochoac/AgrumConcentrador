package com.concentrador.agrum.agrumconcentrador.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                ParametrosFragment parametrostab = new ParametrosFragment();
                return parametrostab;
            case 1:
                ParametrosMaquinaFragment parametrosmaquinatab = new ParametrosMaquinaFragment();
                return parametrosmaquinatab;
            case 2:
                NodosFragment nodostab = new NodosFragment();
                return nodostab;
            case 3:
                GPSFragment gpstab= new GPSFragment();
                return gpstab;

            case 4:
                RegistroFragment registrotab = new RegistroFragment();
                return registrotab;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}