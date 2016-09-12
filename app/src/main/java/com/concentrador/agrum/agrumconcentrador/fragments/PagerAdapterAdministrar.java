package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.concentrador.agrum.agrumconcentrador.MainActivity;

/**
 * Created by diego on 11/09/16.
 */
public class PagerAdapterAdministrar extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    private MainActivity context;
    private Context mContext;

    public PagerAdapterAdministrar(FragmentManager fm) {
        super(fm);
    }

    public PagerAdapterAdministrar(FragmentManager fm, MainActivity context, int NumOfTabs) {
        super(fm);
        this.context = context;
        mContext = context;
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
            MenuCrearContratista contratistatab = new MenuCrearContratista();
            return contratistatab;

            case 1:
            MenuCrearUsuario usuariotab = new MenuCrearUsuario();
            return usuariotab;

            case 2:
            MenuCrearTerreno terrenotab = new MenuCrearTerreno();
            return terrenotab;

            case 3:
                MenuAdministrar administrartab = new MenuAdministrar();
                return administrartab;

            default:
            return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}