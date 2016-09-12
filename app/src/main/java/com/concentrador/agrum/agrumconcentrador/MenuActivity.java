package com.concentrador.agrum.agrumconcentrador;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity implements OnClickListener {

    LinearLayout labor, mantenimiento, administrar;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        toolbar = (Toolbar) findViewById(R.id.toolbarMenu);
        setSupportActionBar(toolbar);
        //hace parte del menu lateral
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //hace parte del menu lateral
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout_Menu);

        navView = (NavigationView)findViewById(R.id.navviewMenu);
        //<editor-fold desc="setNavigationItemSelectedListener">
        navView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        boolean fragmentTransaction = false;
                        Fragment fragment = null;
                        Intent intent;

                        switch (menuItem.getItemId()) {
                            case R.id.menu_seccion_0:
                                intent = new Intent(MenuActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu0");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;

                            case R.id.menu_seccion_1:
                                intent = new Intent(MenuActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu1");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case R.id.menu_seccion_2:
                                intent = new Intent(MenuActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu2");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case R.id.menu_seccion_3:
                                intent = new Intent(MenuActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu3");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case R.id.menu_opcion_1:
                                Log.i("NavigationView", "Pulsada opción 1");
                                break;
                            case R.id.menu_opcion_2:
                                Log.i("NavigationView", "Pulsada opción 2");
                                break;
                        }



                        drawerLayout.closeDrawers();

                        return true;
                    }
                });
        //</editor-fold>

        labor = (LinearLayout)findViewById(R.id.LinearLabor);
        labor.setOnClickListener(this);
        mantenimiento = (LinearLayout)findViewById(R.id.LinearMantenimiento);
        mantenimiento.setOnClickListener(this);
        administrar = (LinearLayout)findViewById(R.id.LinearAdministrar);
        administrar.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.LinearLabor:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("Activity", "labor");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.LinearMantenimiento:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("Activity", "mantenimiento");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.LinearAdministrar:
                intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("Activity", "administrar");
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }

    //<editor-fold desc="Menu Normal">
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch(item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //</editor-fold>
}
