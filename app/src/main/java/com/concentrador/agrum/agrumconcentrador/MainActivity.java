package com.concentrador.agrum.agrumconcentrador;


import android.app.ActionBar;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.view.View.OnClickListener;

import com.concentrador.agrum.agrumconcentrador.fragments.EventosFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.GPSFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.MenuCrearContratista;
import com.concentrador.agrum.agrumconcentrador.fragments.NodosFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.OnFragmentInteractionListener;
import com.concentrador.agrum.agrumconcentrador.fragments.PagerAdapterAdministrar;
import com.concentrador.agrum.agrumconcentrador.fragments.PagerAdapterLabor;
import com.concentrador.agrum.agrumconcentrador.fragments.ParametrosFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.ParametrosMaquinaFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.RegistroFragment;
import com.concentrador.agrum.agrumconcentrador.utils.DepthMeasure;
import com.concentrador.agrum.agrumconcentrador.utils.FileOperations;
import com.concentrador.agrum.agrumconcentrador.utils.RegistrationParameters;
import com.concentrador.agrum.agrumconcentrador.utils.ReporteProfundidad;
import com.concentrador.agrum.agrumconcentrador.utils.UsbService;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener, OnClickListener {

    //Variables OnCreate
    private ViewPager viewPager;

    //Clases de packet Utils
    private MyHandler mHandler;
    private FileOperations fop;
    private ReporteProfundidad reporte;
    private RegistrationParameters parameters;
    private DepthMeasure medicionProfundidad;

    private PagerAdapterLabor pagerAdapterLabor;
    private PagerAdapterAdministrar pagerAdapterAdministrar;
    private TabLayout tabLayout;

    private UsbService usbService;

    private boolean enviarNodosFragment;
    private boolean gpsTabletGpsFragment;
    private boolean registrandoRegistroFragment;

    private int stateRegistro;
    private int stateComSerial;
    private int stateNodos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;

        decorView.setSystemUiVisibility(uiOptions | 8);

        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        String menu_receiver = getIntent().getExtras().getString("Activity");
        switch (menu_receiver) {
            case "labor":
                inicializarLabor();
                break;
            case "evento":
                iniciarEvento();
                break;
            case "administrar":
                iniciarAdministrar();
                break;
        }

        medicionProfundidad = new DepthMeasure();
        fop = new FileOperations(this);
        reporte = new ReporteProfundidad();
        loadPreferences();

    }

    private void inicializarLabor() {
        //Layout que carga los tabs fragment
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab3));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab4));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab5));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapterLabor = new PagerAdapterLabor(getSupportFragmentManager(),MainActivity.this, tabLayout.getTabCount());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapterLabor);
        viewPager.setCurrentItem(5);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void iniciarEvento() {

        Fragment fragment = null;
        fragment = new EventosFragment();
        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.replace(R.id.main_layout, fragment);
        trans.addToBackStack(null);
        trans.commit();

    }

    private void iniciarAdministrar() {
        //Layout que carga los tabs fragment
        tabLayout.setVisibility(View.VISIBLE);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab6));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab7));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab8));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab9));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapterAdministrar = new PagerAdapterAdministrar(getSupportFragmentManager(),MainActivity.this, tabLayout.getTabCount());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapterAdministrar);
        viewPager.setCurrentItem(3);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }




    public void loadPreferences()
    {
        parameters = new RegistrationParameters();
        parameters.loadRegistrationParameters(this);

        medicionProfundidad.setAngleCero(Double.parseDouble(parameters.getParametrosNodos()[0]));
        medicionProfundidad.setOffsetProfundidad(Double.parseDouble(parameters.getParametrosNodos()[1]));

        medicionProfundidad.setDistanceHerramienta(Double.parseDouble(parameters.getParametrosMachine()[0]));
        medicionProfundidad.setDistanceGrownd(Double.parseDouble(parameters.getParametrosMachine()[1]));

        medicionProfundidad.setDistanciaHerramientaTandem(Double.parseDouble(parameters.getParametrosMachine()[4]));
        medicionProfundidad.setDistanciaHerramientaDoble(Double.parseDouble(parameters.getParametrosMachine()[5]));
        medicionProfundidad.setDistanciaHerramientaTriple(Double.parseDouble(parameters.getParametrosMachine()[6]));

        reporte.setParametrosRegistro(parameters.getParametrosRegister());
        reporte.setParametrosMachine(parameters.getParametrosMachine());
    }



    public RegistrationParameters getRegistrationParameters()
    {
        return parameters;
    }



    public void showToast(String msj)
    {
        Toast.makeText(this,msj,Toast.LENGTH_SHORT);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btnRegistro:
                switch (stateRegistro)
                {
                    case 0:
                        //btnRegistro.setText("Terminar Registro");
                        stateRegistro = 1;
                        registrandoRegistroFragment = true;
                        fop.empezarRegistro(gpsTabletGpsFragment);
                        fop.writeHeaders(reporte.darEncabezado());
                        fop.writeAngles("Distance herramienta = " + medicionProfundidad.getDistanceHerramienta());
                        fop.writeAngles("Distance suelo = "+medicionProfundidad.getDistanceGrownd());
                        break;
                    case 1:
                        //btnRegistro.setText("Iniciar Registro");
                        stateRegistro = 0;
                        registrandoRegistroFragment = false;
                        break;
                }
                break;
            /*case R.id.btnPrueba1:
                System.out.println("Btn Prueba 1");
                if(gpsTabletGpsFragment) {
                    gpsTabletGpsFragment = false;
                }
                else {
                    gpsTabletGpsFragment = true;
                }
                //display.setText("GPS: "+gpsTablet+"\n"+display.getText());
                break;

            case R.id.btnComSerial:
                switch (stateComSerial)
                {
                    case 0:
                        //btnComSerial.setText("TerminarCom");
                        enviarNodosFragment = true;
                        stateComSerial = 1;
                        System.out.println("Iniciando com serial");
                        break;
                    case 1:
                        //btnComSerial.setText("IniciarCom");
                        enviarNodosFragment = false;
                        stateComSerial = 0;
                        System.out.println("Terminando com serial");
                        break;
                }
                System.out.println(reporte.darMedicion());
                break;*/    //TODO revizar que es esto???
        }
    }


    private void startService(Class<?> service, ServiceConnection serviceConnection, Bundle extras)
    {
        if(UsbService.SERVICE_CONNECTED == false)
        {
            Intent startService = new Intent(this, service);
            if(extras != null && !extras.isEmpty())
            {
                Set<String> keys = extras.keySet();
                for(String key: keys)
                {
                    String extra = extras.getString(key);
                    startService.putExtra(key, extra);
                }
            }
            startService(startService);
        }
        Intent bindingIntent = new Intent(this, service);
        bindService(bindingIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void setFilters()
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(UsbService.ACTION_USB_PERMISSION_GRANTED);
        filter.addAction(UsbService.ACTION_NO_USB);
        filter.addAction(UsbService.ACTION_USB_DISCONNECTED);
        filter.addAction(UsbService.ACTION_USB_NOT_SUPPORTED);
        filter.addAction(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED);
        filter.addAction(UsbService.ACTION_USB_READY);

        registerReceiver(mUsbReceiver, filter);
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    private static class MyHandler extends Handler
    {
        private final WeakReference<MainActivity> mActivity;

        public MyHandler(MainActivity activity)
        {
            mActivity = new WeakReference<MainActivity>(activity);
        }
    @Override
        public void handleMessage(Message msg)
        {
            switch(msg.what)
            {
                case UsbService.MESSAGE_FROM_SERIAL_PORT:

                    String data = (String) msg.obj;
                    //String text = mActivity.get().display.getText().toString();
                    if(data.contains("Encontro"))
                    {
                        mActivity.get().pagerAdapterLabor.updateGPSConsoleUSBSerial(data);
                    }

                    String[] medicion = data.split("Angulo:");

                    char start = 0x02;
                    char end = 0x03;
                    String msg_debug = "No contiene: "+data+"\n";
                    if(data.contains("" + start) && data.contains("" + end))
                    {
                        msg_debug = "Contiene ";
                        String command = data.split(""+start)[1];
                        command = command.split(""+end)[0];
                        command = command.replace(""+start,"");
                        command = command.replace(""+end,"");

                        if(command.startsWith("N0"))
                        {
                            msg_debug+="N0 "+command+"\n";
                            String angle = command.split(":")[1].split(",")[0];
                            if (isNumeric(angle))
                            {
                                msg_debug+="Is numeric\n";
                                double angleTruck = Double.parseDouble(angle) / 100;
                                mActivity.get().medicionProfundidad.addAnguloTractor(angleTruck);
                                mActivity.get().medicionProfundidad.setAngleTruck(mActivity.get().medicionProfundidad.getAnguloTractor());
                                mActivity.get().calcularProfundidad();
                                //mActivity.get().txtAngulo1.setText(""+angleTruck);
                            }
                            else {
                                msg_debug+="Is not numeric\n";
                            }
                        }
                        else if(command.startsWith("N1"))
                        {
                            msg_debug+="N1 "+command+"\n";
                            String angle = command.split(":")[1].split(",")[0];
                            if (isNumeric(angle))
                            {
                                msg_debug+="Is numeric\n";
                                double angleImplement = Double.parseDouble(angle) / 100;
                                mActivity.get().medicionProfundidad.addAnguloImplemento(angleImplement);
                                mActivity.get().medicionProfundidad.setAngleImplement(mActivity.get().medicionProfundidad.getAnguloImplemeneto());
                                mActivity.get().calcularProfundidad();
                                //mActivity.get().txtAngulo2.setText(""+angleImplement);
                            }
                            else {
                                msg_debug+="Is not numeric\n";
                            }
                        }
                        else if(command.startsWith("G0"))
                        {
                            msg_debug+="GPS ";
                            command = command.split(":")[1];
                            String[] spl = command.split("\r\n");
                            msg_debug+="spl "+spl.length+"\r\n";
                            for (int i = 0 ; i < spl.length;i++)
                            {
                                String lat = spl[i];
                                if(lat.startsWith("$GPGGA"))
                                {
                                    msg_debug+=lat+"\r\n";
                                    String[] nmea = lat.split(",");
                                    double latitude =
                                            (isNumeric(nmea[2]))?Double.parseDouble(nmea[2]):0;
                                    latitude = (latitude%100)/60+(int)(latitude/100);
                                    double longitude = (isNumeric(nmea[2]))?Double.parseDouble(nmea[4]):0;
                                    longitude = -((longitude%100)/60+(int)(longitude/100));
                                    double accuracy = (isNumeric(nmea[2]))?Double.parseDouble(nmea[8]):0;
                                    double altitude = (isNumeric(nmea[2]))?Double.parseDouble(nmea[9]):0;
                                    mActivity.get().updateGPS(latitude,longitude,accuracy,altitude);
                                }
                                if(lat.startsWith("$GPRMC"))
                                {
                                    String[] nmea = lat.split(",");
                                    double speed = (isNumeric(nmea[2]))?Double.parseDouble(nmea[7])* 1.85200:0;
                                    mActivity.get().updateGPS(speed);
                                }
                            }
                        }
                    }

                    mActivity.get().pagerAdapterLabor.updateGPSConsole(msg_debug);
                    break;
            }
        }
    }


    private final BroadcastReceiver mUsbReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context arg0, Intent arg1)
        {
            if(arg1.getAction().equals(UsbService.ACTION_USB_PERMISSION_GRANTED)) // USB PERMISSION GRANTED
            {
                Toast.makeText(arg0, "USB Ready", Toast.LENGTH_SHORT).show();
            }else if(arg1.getAction().equals(UsbService.ACTION_USB_READY)) // USB PERMISSION NOT GRANTED
            {
                Toast.makeText(arg0, "USB reeeady", Toast.LENGTH_SHORT).show();
                enviarNodosFragment = true;
            }

            else if(arg1.getAction().equals(UsbService.ACTION_USB_PERMISSION_NOT_GRANTED)) // USB PERMISSION NOT GRANTED
            {
                Toast.makeText(arg0, "USB Permission not granted", Toast.LENGTH_SHORT).show();
            }else if(arg1.getAction().equals(UsbService.ACTION_NO_USB)) // NO USB CONNECTED
            {
                //TODO Revisar para el cambio de file
                //Toast.makeText(arg0, "No USB connected", Toast.LENGTH_SHORT).show();
            }else if(arg1.getAction().equals(UsbService.ACTION_USB_DISCONNECTED)) // USB DISCONNECTED
            {
                Toast.makeText(arg0, "USB disconnected", Toast.LENGTH_SHORT).show();
            }else if(arg1.getAction().equals(UsbService.ACTION_USB_NOT_SUPPORTED)) // USB NOT SUPPORTED
            {
                Toast.makeText(arg0, "USB device not supported", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private final ServiceConnection usbConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1)
        {
            usbService = ((UsbService.UsbBinder) arg1).getService();
            usbService.setHandler(mHandler);
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0)
        {
            usbService = null;
        }
    };

    Handler handler = new Handler();
    Handler handlerGuardar = new Handler();
    Handler handlerUsb = new Handler();
    public int contador = 0;

    //<editor-fold desc="Runnables Hilos">
    private Runnable updateData = new Runnable(){
        public void run(){
            //System.out.println("Enviando dato: "+enviar+"  - "+gpsTablet);
            int delay = 500;
            pagerAdapterLabor.updateGPSConsole("usbService: " + (usbService != null) + "\n");

            if(usbService != null)
            {
                pagerAdapterLabor.updateGPSConsole("usbServiceDevice: "+(usbService.isDeviceConnected())+"\n");
                pagerAdapterLabor.updateGPSConsole("usbSerialPort: "+(usbService.isSerialPortConnected())+"\n");
                if(enviarNodosFragment == false)
                {
                    enviarNodosFragment = true;
                }
            }
            if(usbService != null && enviarNodosFragment && !usbService.isSerialPortConnected())
            {
                //usbService.findSerialPortDevice();    //TODO descomentar
            }
            else if(usbService != null && enviarNodosFragment && usbService.isSerialPortConnected()) // if UsbService was correctly binded, Send data{
            {
                pagerAdapterLabor.updateGPSConsole("Enviando \n");
                usbService.printCharacteresReceived();
                String command = "";
                //usbService.write(data.getBytes());
                switch (stateNodos)
                {
                    case 0:
                        stateNodos = 1;
                        command = "N0";
                        delay = 100;
                        break;
                    case 1:
                        if(gpsTabletGpsFragment)
                            stateNodos = 0;
                        else
                        {
                            stateNodos = 2;
                        }
                        command = "N1";
                        break;
                    case 2:
                        stateNodos = 0;
                        command = "G0";
                        delay = 500;
                        break;
                }
                char ini = 0x02;
                char fin = 0x03;
                //usbService.write(("" + ini + command + fin).getBytes());
            }
            handler.postDelayed(updateData, delay);
        }
    };



    private Runnable guardarReporte = new Runnable(){
        public void run(){

            /*if(registrandoRegistroFragment)
            {
                System.out.println("Guardando Reporte");
                boolean guardo = fop.write(reporte.darMedicion());
                Toast.makeText(MainActivity.this,"Guardo: "+guardo,Toast.LENGTH_SHORT);

                //DecimalFormat df1 = new DecimalFormat("0.00");
                //String angles = df1.format(angleCero) +","+df1.format(angleImplement)+","+df1.format(angleTruck);
                //fop.writeAngles(angles);
                if(false) {
                   RegistroRestClient.post("trabajo/create", reporte.getRequestParamsRegister(), new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            System.out.println("***************************Success");
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            System.out.println("Error");

                    });
                }
            }*///Todo: revisar esto
            if(reporte != null)
                //pagerAdapterLabor.addPointToMap(reporte.getLatitud(),reporte.getLongitud(),10);   //TODO:creo que no funciona
            handlerGuardar.postDelayed(guardarReporte, 3000);
        }
    };

    private Runnable revisarUSB = new Runnable(){
        public void run(){
            System.out.println("Revisando USB");
            pagerAdapterLabor.updateGPSConsole("Revisando USB"+(usbService != null)+"\n");
            handlerUsb.postDelayed(revisarUSB, 3000);
        }
    };
    //</editor-fold>

    public void sendSerial(String command){
        if(usbService != null && enviarNodosFragment && usbService.isSerialPortConnected()) // if UsbService was correctly binded, Send data{
        {
            char ini = 0x02;
            char fin = 0x03;
            usbService.write(("" + ini + command + fin).getBytes());
        }
    }

    public void calcularProfundidad()
    {
        medicionProfundidad.imprimierAngulos(fop);
        medicionProfundidad.guardarProfundidad(pagerAdapterLabor);
        reporte.setProfundidad(medicionProfundidad.getProfundidad());
        pagerAdapterLabor.updateEstado(reporte.getEstado());
    }

    public void updateGPS(double latitude,double longitude,double speed,double accuracy,double altitude)
    {
        DecimalFormat df = new DecimalFormat("0.00000");
        DecimalFormat df1 = new DecimalFormat("0.0");

        reporte.setLatitud(latitude);
        reporte.setLongitud(longitude);
        reporte.setVelocidad(speed);
        reporte.setPrecision(accuracy);
        reporte.setAltitud(altitude);

        pagerAdapterLabor.updateGPS(df.format(latitude), df.format(longitude), df1.format(speed), df1.format(altitude), df1.format(accuracy));
    }

    public void updateGPS(double speed)
    {
        DecimalFormat df1 = new DecimalFormat("0.0");

        reporte.setVelocidad(speed);
    }

    public void updateGPS(double latitude,double longitude,double accuracy,double altitude)
    {
        DecimalFormat df = new DecimalFormat("0.00000");
        DecimalFormat df1 = new DecimalFormat("0.0");

        reporte.setLatitud(latitude);
        reporte.setLongitud(longitude);
        reporte.setPrecision(accuracy);
        reporte.setAltitud(altitude);

        pagerAdapterLabor.updateGPS(df.format(latitude), df.format(longitude), df1.format(altitude), df1.format(accuracy));
    }

    //<editor-fold desc="Ciclo vida Activity">
    @Override
    public void onResume(){
        Log.i("MainActivity", "onResume");
        super.onResume();
        setFilters();  // Start listening notifications from UsbService
        startService(UsbService.class, usbConnection, null); // Start UsbService(if it was not started before) and Bind it
    }

    @Override
    public void onPause() {
        Log.i("MainActivity", "onPause");
        super.onPause();
        unregisterReceiver(mUsbReceiver);
        unbindService(usbConnection);
    }

    @Override
    protected void onDestroy() {
        Log.i("MainActivity", "onDestroy");
        super.onDestroy();
        //unregisterReceiver(mUsbReceiver); //TODO mirar
        //unbindService(usbConnection);
    }
    //</editor-fold>

    @Override
    public void onFragmentInteraction(Uri uri) {
        String[] spl = uri.toString().split(":");

        switch (spl[0]){

            //<editor-fold desc="ParametrosFragment TextView Variables">
            case ParametrosFragment.SET_HACIENDA:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this,RegistrationParameters.PARAM_HACIENDA,spl[1]);
                    reporte.setHacienda(spl[1]);
                }
                break;
            case ParametrosFragment.SET_LOTE:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_LOTE, spl[1]);
                    reporte.setLote(spl[1]);
                }
                break;
            case ParametrosFragment.SET_CONTRATISTA:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_CONTRATISTA, spl[1]);
                    reporte.setContratista(spl[1]);
                }
                break;
            case ParametrosFragment.SET_OPERADOR:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_OPERADOR, spl[1]);
                    reporte.setOperador(spl[1]);
                }
                break;
            case ParametrosFragment.SET_EQ_AGRICOLA:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    //parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_OPERADOR, spl[1]);
                    //reporte.setOperador(spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_MAQUINA, spl[1]);
                    reporte.setMaquina(spl[1]);

                    if(spl[1].equals("Tandem")){ }
                    else if(spl[1].equals("Doble")){ }
                    else if(spl[1].equals("Triple")){ }
                }
                break;
            //</editor-fold>

            //<editor-fold desc="ParametrosMaquinaFragment TextView Variables">
            case ParametrosMaquinaFragment.SET_DISTANCIA_HERRAMIENTA:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_DISTANCIA_HERRAMIENTA, spl[1]);
                    medicionProfundidad.setDistanceHerramienta(Double.parseDouble(spl[1]));

                    String eqAgricola = reporte.getMaquina();
                    if(eqAgricola.equals("Tandem"))
                    {
                        System.out.println("Modificando L tandem: "+spl[1]);
                    }
                    else if(eqAgricola.equals("Doble"))
                    {
                        System.out.println("Modificando L doble: "+spl[1]);
                    }
                    else if(eqAgricola.equals("Triple"))
                    {
                        System.out.println("Modificando L triple: "+spl[1]);
                    }
                }
                break;
            case ParametrosMaquinaFragment.SET_DISTANCIA_EJE_SUELO:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_DISTANCIA_SUELO, spl[1]);
                    medicionProfundidad.setDistanceGrownd(Double.parseDouble(spl[1]));
                }
                break;
            case ParametrosMaquinaFragment.SET_MAQUINARIA:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_MAQUINA, spl[1]);
                    //reporte.setMaquina(spl[1]);
                }
                break;
            case ParametrosMaquinaFragment.SET_ANCHO_LABOR:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_ANCHO_LABOR, spl[1]);
                    double anchoLabor = Double.parseDouble(spl[1]);
                    reporte.setAnchoLabor(anchoLabor);
                }
                break;
            case ParametrosMaquinaFragment.SET_PROFUNDIDAD_DESEADA:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_PROFUNDIDAD_DESEADA, spl[1]);
                    reporte.setProfundidadDeseada(spl[1]);
                }
                break;
            //</editor-fold>

            //<editor-fold desc="NodosFragment TextView Variables">
            case NodosFragment.SET_OFFSET_PROFUNDIDAD:
                if(spl.length > 1) {
                    System.out.println(spl[0] + spl[1]);
                    parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_OFFSET_PROFUNDIDAD, spl[1]);
                    medicionProfundidad.setOffsetProfundidad(Double.parseDouble(spl[1]));
                }
                break;
            //</editor-fold>

            //<editor-fold desc="Botones de Navegacion Fragments">
            case ParametrosFragment.BTN_REGISTRO:
                viewPager.setCurrentItem(5);
                break;

            case ParametrosFragment.BTN_PARAMETROS_MAQUINA:
                viewPager.setCurrentItem(1);
                break;

            case ParametrosMaquinaFragment.BTN_REGISTRO_MAQUINA:
                viewPager.setCurrentItem(5);
                break;

            case ParametrosMaquinaFragment.BTN_PARAMETROS_REGISTRO:
                viewPager.setCurrentItem(0);
                break;

            case NodosFragment.BTN_INIT_COM:
                enviarNodosFragment = true;
                break;
            case NodosFragment.BTN_END_COM:
                enviarNodosFragment = false;
                break;
            case NodosFragment.BTN_SET_CERO:
                medicionProfundidad.setAngleCero();
                parameters.saveRegistrationParameter(this, RegistrationParameters.PARAM_ANG_CERO,""+medicionProfundidad.getAngleCero());
                break;

            case GPSFragment.BTN_GPS_EXTERNO:
                System.out.println(spl[0]);
                gpsTabletGpsFragment = false;
                //String device = usbService.getPID_VID_device();   //TODO: Falta colocar el servicio USB
                //pagerAdapter.updateGPSConsoleUSBSerial(device);   //TODO: decomentar  colocar el servicio USB
                break;

            case GPSFragment.BTN_GPS_INTERNO:
                System.out.println(spl[0]);
                gpsTabletGpsFragment = true;
                break;
            case RegistroFragment.BTN_INIT_REGISTRO:
                System.out.println(spl[0]);
                registrandoRegistroFragment = true;
                fop.empezarRegistro(reporte.getHacienda(), reporte.getLote());
                fop.writeHeaders(reporte.darEncabezado());
                parameters.getParametrosRegister();
                //TODO
                this.sendSerial("T0:START,"+parameters.getParametrosRegisterJSON());
                break;

            case RegistroFragment.BTN_END_REGISTRO:
                registrandoRegistroFragment = false;
                this.sendSerial("T0:END");
                break;


            case RegistroFragment.BTN_PARAMETROS:
                viewPager.setCurrentItem(0);
                break;
            //</editor-fold>

        }
    }

}
