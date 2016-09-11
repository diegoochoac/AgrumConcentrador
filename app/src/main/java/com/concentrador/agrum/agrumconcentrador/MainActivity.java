package com.concentrador.agrum.agrumconcentrador;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.concentrador.agrum.agrumconcentrador.fragments.GPSFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.NodosFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.OnFragmentInteractionListener;
import com.concentrador.agrum.agrumconcentrador.fragments.PagerAdapter;
import com.concentrador.agrum.agrumconcentrador.fragments.ParametrosFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.ParametrosMaquinaFragment;
import com.concentrador.agrum.agrumconcentrador.fragments.RegistroFragment;
import com.concentrador.agrum.agrumconcentrador.utils.DepthMeasure;
import com.concentrador.agrum.agrumconcentrador.utils.FileOperations;
import com.concentrador.agrum.agrumconcentrador.utils.RegistrationParameters;
import com.concentrador.agrum.agrumconcentrador.utils.ReporteProfundidad;


public class MainActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    //Variables OnCreate
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ViewPager viewPager;

    //Clases de packet Utils
    private FileOperations fop;
    private ReporteProfundidad reporte;
    private RegistrationParameters parameters;
    private DepthMeasure medicionProfundidad;

    private PagerAdapter pagerAdapter;

    private boolean enviarNodosFragment;
    private boolean gpsTabletGpsFragment;
    private boolean registrandoRegistroFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        String menu_receiver = getIntent().getExtras().getString("Activity");
        switch (menu_receiver) {
            case "labor":
                inicializarLabor();
                break;
            case "mantenimiento":
                break;
            case "administrar":
                iniciarMantenimiento();
                break;
        }


        medicionProfundidad = new DepthMeasure();
        fop = new FileOperations(this);
        reporte = new ReporteProfundidad();
        loadPreferences();

    }

    private void inicializarLabor() {
        //<editor-fold desc="Tab y Menu lateral">
        //Layout que carga los tabs fragment
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //hace parte del menu lateral
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav_menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //hace parte del menu lateral
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        //Layout que carga los tabs fragment
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab1));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab2));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab3));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab4));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab5));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),MainActivity.this, tabLayout.getTabCount());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
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

        navView = (NavigationView)findViewById(R.id.navview);
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
                                intent = new Intent(MainActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu0");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;

                            case R.id.menu_seccion_1:
                                intent = new Intent(MainActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu1");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case R.id.menu_seccion_2:
                                intent = new Intent(MainActivity.this, NavigationViewActivity.class);
                                intent.putExtra("Fragment", "menu2");
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                break;
                            case R.id.menu_seccion_3:
                                intent = new Intent(MainActivity.this, NavigationViewActivity.class);
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
        //</editor-fold>
    }

    private void iniciarMantenimiento() {
        //<editor-fold desc="Tab">
        //Layout que carga los tabs fragment
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Layout que carga los tabs fragment
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab6));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab7));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab8));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(),MainActivity.this, tabLayout.getTabCount());

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
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

        //</editor-fold>
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

    //Muestra el Toast cuando se llama desde otra clase
    public void showToast(String msj)
    {
        Toast.makeText(this,msj,Toast.LENGTH_SHORT);
    }

    public void sendSerial(String command){
       /* if(usbService != null && enviarNodosFragment && usbService.isSerialPortConnected()) // if UsbService was correctly binded, Send data{
        {
            char ini = 0x02;
            char fin = 0x03;
            usbService.write(("" + ini + command + fin).getBytes());
        }*/ //TODO: falta colocar el servicion USB
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
