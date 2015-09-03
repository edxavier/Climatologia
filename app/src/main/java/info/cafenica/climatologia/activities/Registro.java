package info.cafenica.climatologia.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;

import info.cafenica.climatologia.db.Utilidades;
import info.cafenica.climatologia.R;
import info.cafenica.climatologia.db.models.Clima;
import info.cafenica.climatologia.db.models.ClimaSync;

public class Registro extends AppCompatActivity {
    Toolbar toolbar;
    EditText txtTempAct;
    EditText txtTempMin;
    EditText txtTempMax;
    EditText txtTempMed;
    EditText txtTempRocio;
    EditText txtTempRocioMin;
    EditText txtTempRocioMax;
    EditText txtTempSuelo;

    EditText txtHumedad;
    EditText txtHumedadMin;
    EditText txtHumedadMax;
    EditText txtHumedadMed;
    EditText txtHumedadSuelo;

    EditText txtBrillo;
    EditText txtPrecipitacion;
    EditText txtObservacion;
    MaterialCalendarView calendarView;

    Typeface roboto;
    ProgressDialog pgdialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TypedValue typedValueColorPrimaryDark = new TypedValue();
        Registro.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValueColorPrimaryDark, true);
        final int colorPrimaryDark = typedValueColorPrimaryDark.data;
        roboto = Typeface.createFromAsset(this.getAssets(),"fonts/Roboto-Regular.ttf");
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setStatusBarColor(colorPrimaryDark);
        }
        setUpTextBoxes();

    }

    public void setUpTextBoxes(){
        calendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
        calendarView.setSelectedDate(new Date());
        txtTempAct = (EditText) findViewById(R.id.text_temp_act);
        txtTempAct.setTypeface(roboto);
        txtTempMin = (EditText) findViewById(R.id.text_temp_min);
        txtTempMin.setTypeface(roboto);
        txtTempMax = (EditText) findViewById(R.id.text_temp_max);
        txtTempMax.setTypeface(roboto);
        txtTempMed = (EditText) findViewById(R.id.text_temp_media);
        txtTempMed.setTypeface(roboto);
        txtTempSuelo = (EditText) findViewById(R.id.text_temp_suelo);
        txtTempSuelo.setTypeface(roboto);
        txtTempRocio =  (EditText) findViewById(R.id.text_rocio);
        txtTempRocio.setTypeface(roboto);
        txtTempRocioMin = (EditText) findViewById(R.id.text_rocio_min);
        txtTempRocioMin.setTypeface(roboto);
        txtTempRocioMax =(EditText) findViewById(R.id.text_rocio_max);
        txtTempRocioMax.setTypeface(roboto);

        txtHumedad =(EditText) findViewById(R.id.text_humedad);txtHumedad.setTypeface(roboto);
        txtHumedadMin =(EditText) findViewById(R.id.text_hum_min);txtHumedadMin.setTypeface(roboto);
        txtHumedadMax =(EditText) findViewById(R.id.text_hum_max);txtHumedadMax.setTypeface(roboto);
        txtHumedadMed =(EditText) findViewById(R.id.text_hum_med);txtHumedadMed.setTypeface(roboto);
        txtHumedadSuelo =(EditText) findViewById(R.id.text_hum_suelo);txtHumedadSuelo.setTypeface(roboto);
        txtBrillo =(EditText) findViewById(R.id.text_brillo_sol);txtBrillo.setTypeface(roboto);
        txtPrecipitacion = (EditText) findViewById(R.id.text_precipitacion);txtPrecipitacion.setTypeface(roboto);
        txtObservacion = (EditText) findViewById(R.id.text_observacion);txtObservacion.setTypeface(roboto);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registro, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.ac_save:
                pgdialog = new ProgressDialog(this);
                pgdialog.setIndeterminate(true);
                pgdialog.setMessage("Enviado datos al servidor.");
                pgdialog.setTitle("Espere por favor...");
                pgdialog.setCancelable(false);
                pgdialog.show();
                saveData();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void saveData(){
        Clima climaEntry = new Clima();
        ClimaSync climaSync = new ClimaSync();
        climaSync.save();

        try {
            climaEntry.setTemp_actual(Double.valueOf(txtTempAct.getText().toString()));
            climaEntry.setTemp_min(Double.valueOf(txtTempMin.getText().toString()));
            climaEntry.setTemp_max(Double.valueOf(txtTempMax.getText().toString()));
            climaEntry.setTemp_media(Double.valueOf(txtTempMed.getText().toString()));
            climaEntry.setTemp_rocio(Double.valueOf(txtTempRocio.getText().toString()));
            climaEntry.setTemp_rocio_min(Double.valueOf(txtTempRocioMin.getText().toString()));
            climaEntry.setTemp_rocio_max(Double.valueOf(txtTempRocioMax.getText().toString()));
            climaEntry.setTemp_suelo(Double.valueOf(txtTempSuelo.getText().toString()));

            climaEntry.setHum_actual(Double.valueOf(txtHumedad.getText().toString()));
            climaEntry.setHum_min(Double.valueOf(txtHumedadMin.getText().toString()));
            climaEntry.setHum_max(Double.valueOf(txtHumedadMax.getText().toString()));
            climaEntry.setHum_media(Double.valueOf(txtHumedadMed.getText().toString()));
            climaEntry.setHum_suelo(Double.valueOf(txtHumedadSuelo.getText().toString()));

            climaEntry.setPrecipitacion(Double.valueOf(txtPrecipitacion.getText().toString()));
            climaEntry.setBrillo_solar(Double.valueOf(txtBrillo.getText().toString()));
            climaEntry.setObservacion(txtObservacion.getText().toString());
            CalendarDay calendarDay = calendarView.getSelectedDate();

            climaEntry.setFecha_productor(Utilidades.dateToInt(calendarDay.getDate()));
            climaEntry.setFecha_sistema(Utilidades.dateToInt(new Date()));
            climaEntry.setUsuario(1);
            climaEntry.save();

            pgdialog.dismiss();

            Intent returnIntent = new Intent();
            returnIntent.putExtra("entry", climaEntry);
            setResult(RESULT_OK, returnIntent);
            finish();

        }catch (Exception e) {
            pgdialog.dismiss();
            //Toast.makeText(this, "Ah ocurrido un error... verifique todos los valores", Toast.LENGTH_SHORT).show();
            Log.d("REGISTRO", e.getMessage());
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

}
