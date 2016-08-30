package com.concentrador.agrum.agrumconcentrador;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.concentrador.agrum.agrumconcentrador.fragments.MenuFragment1;
import com.concentrador.agrum.agrumconcentrador.fragments.MenuFragment2;
import com.concentrador.agrum.agrumconcentrador.fragments.MenuFragment3;

public class NavigationViewActivity extends AppCompatActivity{

    //AppCompatActivity
    private Toolbar appbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_view);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        appbar = (Toolbar)findViewById(R.id.appbar);
        setSupportActionBar(appbar);

        String fragment_receiver = getIntent().getExtras().getString("Fragment");
        boolean fragmentTransaction = false;
        Fragment fragment = null;

        switch (fragment_receiver){
            case "menu1":
                fragment = new MenuFragment1();
                fragmentTransaction = true;
                break;
            case "menu2":
                fragment = new MenuFragment2();
                fragmentTransaction = true;
                break;
            case "menu3":
                fragment = new MenuFragment3();
                fragmentTransaction = true;
                break;
        }

        if(fragmentTransaction) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i("NavigationViewActivity", "OnStop");
        super.onStop();
        this.finish();
    }

}
