package com.concentrador.agrum.agrumconcentrador;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity implements OnClickListener {

    LinearLayout labor, mantenimiento, administrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

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
}
