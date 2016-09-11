package com.concentrador.agrum.agrumconcentrador.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.concentrador.agrum.agrumconcentrador.MainActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    private MainActivity context;

    private ParametrosFragment parametrosFragment;
    private GPSFragment gpsFragment;
    private NodosFragment nodosFragment;
    private RegistroFragment registroFragment;
    private MapaFragment mapaFragment;

    private boolean enableConsole;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    public PagerAdapter(FragmentManager fm, MainActivity context) {
        super(fm);
        this.context = context;

        parametrosFragment = null;
        nodosFragment = null;
        registroFragment = null;
        enableConsole = true;
    }

    public PagerAdapter(FragmentManager fm, MainActivity context, int NumOfTabs) {
        super(fm);
        this.context = context;
        this.mNumOfTabs = NumOfTabs;

        parametrosFragment = null;
        nodosFragment = null;
        registroFragment = null;
        enableConsole = true;
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

            case 5:
                MenuCrearContratista contratistatab = new MenuCrearContratista();
                return contratistatab;

            case 6:
                MenuCrearUsuario usuariotab = new MenuCrearUsuario();
                return usuariotab;
            case 7:
                MenuCrearTerreno terrenotab = new MenuCrearTerreno();
                return terrenotab;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


    public void updateGPS(String nVelocidad)
    {
        if(gpsFragment != null)
        {
            gpsFragment.update(nVelocidad);
        }
        if(registroFragment != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            registroFragment.updateVelocidad(nVelocidad, format.format(new Date()));
        }
    }

    public void updateGPS(String nLatitud,String nLongitud,String nAltitud,String nPrecision)
    {
        if(gpsFragment != null)
        {
            gpsFragment.update(nLatitud, nLongitud, nAltitud, nPrecision);
        }
    }

    public void updateGPS(String nLatitud,String nLongitud,String nVelocidad,String nAltitud,String nPrecision)
    {
        if(gpsFragment != null)
        {
            gpsFragment.update(nLatitud, nLongitud, nVelocidad, nAltitud, nPrecision);
        }
        if(registroFragment != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            registroFragment.updateVelocidad(nVelocidad, format.format(new Date()));
        }
    }

    public void updateGPSConsole(String msg)
    {
        if(gpsFragment != null && enableConsole)
        {
            gpsFragment.updateMessage(msg);
        }
    }

    public void updateGPSConsoleUSBSerial(String msg)       //TODO: este no creo que funcione
    {
        if(gpsFragment != null && enableConsole)
        {
            gpsFragment.updateSerial(msg);
            //enableConsole = false;
        }
    }

    public void updateProfundidad(double nProfundidad,double nAngleTractor,double nAngleImplemento,double nDifAngle,double angCero,double angMedicion)
    {
        if(nodosFragment != null)
        {
            DecimalFormat df = new DecimalFormat("0.0");
            DecimalFormat df_int = new DecimalFormat("0");
            nodosFragment.update(df_int.format(nProfundidad), df.format(nAngleTractor), df.format(nAngleImplemento), df.format(nDifAngle), df.format(angCero), df.format(angMedicion));
        }
        if(registroFragment != null)
        {
            DecimalFormat df = new DecimalFormat("0.0");
            DecimalFormat df_int = new DecimalFormat("0");
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            registroFragment.updateProfundidad(df_int.format(nProfundidad), format.format(new Date()));
        }
    }

    public void updateRegistro(double nVelocidad,double nProfundidad,String nEstado,String fecha)
    {
        if(registroFragment != null)
        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
            DecimalFormat df = new DecimalFormat("0.0");
            DecimalFormat df_int = new DecimalFormat("0");
            registroFragment.update(df.format(nVelocidad), df_int.format(nProfundidad), nEstado, format.format(new Date()));
        }
    }

    public void updateEstado(String nEstado)
    {
        if(registroFragment != null)
        {
            registroFragment.updateEstado(nEstado);
        }
    }

}