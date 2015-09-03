package info.cafenica.climatologia.activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import com.rey.material.app.Dialog;
import java.util.Calendar;
import java.util.List;

import com.rey.material.app.DatePickerDialog;

import cn.pedant.SweetAlert.SweetAlertDialog;
import info.cafenica.climatologia.R;
import info.cafenica.climatologia.db.models.Clima;
import info.cafenica.climatologia.db.models.ClimaSync;
import info.cafenica.climatologia.db.models.Usuario;
import info.cafenica.climatologia.fragments.FragmentClima;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int REGISTER = 1;
    Usuario usuario = null;
    DrawerLayout drawerLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    ActionBar actionBar;
    TextView textView;
    FragmentManager fragmentManager;
    FragmentClima frgClima = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        usuario = Usuario.getUserCredentials();
        if(usuario==null) {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else{
            drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
            collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collap);
            collapsingToolbarLayout.setTitle(usuario.getUsuario());
            TextView drawerUser = (TextView) findViewById(R.id.drawer_user);
            drawerUser.setText(usuario.getUsuario());
        }
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        try {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){ }

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }
        FloatingActionButton fabBtn = (FloatingActionButton) findViewById(R.id.fabBtn_nuevo);
        fabBtn.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();
        frgClima = new FragmentClima();
        fragmentManager.beginTransaction().replace(R.id.fragmentContainer, frgClima).commit();
        //setupNavigationDrawerContent(navigationView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_exit:
                finish();
                return true;
            case R.id.action_unbind:
                CoordinatorLayout content = (CoordinatorLayout) findViewById(R.id.contentLayout);
                Snackbar.make(content, "Desvincular cuenta?", Snackbar.LENGTH_INDEFINITE)
                        .setAction("SI", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                usuario.delete();
                                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigationDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.item_navigation_drawer_inbox:
                                menuItem.setChecked(true);
                                collapsingToolbarLayout.setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                //fragment = new FragmentInternacionales();
                                break;
                            case R.id.item_navigation_drawer_unbind:
                                menuItem.setChecked(true);
                                collapsingToolbarLayout.setTitle(menuItem.getTitle());
                                drawerLayout.closeDrawer(GravityCompat.START);
                                CoordinatorLayout content = (CoordinatorLayout) findViewById(R.id.contentLayout);
                                Snackbar.make(content, "Desvincular usuario?", Snackbar.LENGTH_INDEFINITE)
                                .setAction("SI", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        usuario.delete();
                                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }).show();
                                //fragment = new FragmentNacionales();
                                break;
                            case R.id.item_navigation_drawer_exit:
                                menuItem.setChecked(true);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.item_navigation_drawer_settings:
                                menuItem.setChecked(true);
                                collapsingToolbarLayout.setTitle(menuItem.getTitle());
                                //Toast.makeText(MainActivity.this, "Launching " + menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);
                                SweetAlertDialog pDialog = new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.WARNING_TYPE);
                                pDialog.setTitleText("Are you sure?");
                                pDialog.setContentText("Won't be able to recover this file!");
                                pDialog.setConfirmText("Yes,delete it!");
                                pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                    @Override
                                    public void onClick(SweetAlertDialog sDialog) {
                                        sDialog
                                                .setTitleText("Deleted!")
                                                .setContentText("Your imaginary file has been deleted!")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                    }
                                });
                                pDialog.show();
                                //Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                                //startActivity(intent);
                                return true;
                            case R.id.item_navigation_drawer_help_and_feedback:
                                menuItem.setChecked(true);
                                //Toast.makeText(MainActivity.this, menuItem.getTitle().toString(), Toast.LENGTH_SHORT).show();
                                drawerLayout.closeDrawer(GravityCompat.START);

                                return true;
                        }


                        // Insert the fragment by replacing any existing fragment
  //                      FragmentManager fragmentManager = getSupportFragmentManager();
//                        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();
                        //setTitle(menuItem.getTitle());
                        return true;
                    }
                });
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent(HomeActivity.this, Registro.class);
        //startActivity(intent);
        startActivityForResult(intent,REGISTER);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK){
            Clima clima =(Clima) data.getParcelableExtra("entry");
            Log.d("REGISTRO", String.valueOf(clima.getTemp_actual()));
            frgClima.addClimaEntry(clima);
        }

    }
}
