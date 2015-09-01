package info.cafenica.climatologia.activities;

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

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;

import info.cafenica.climatologia.R;

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
    MaterialCalendarView calendarView;
    Typeface roboto;


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
        Log.d("REG", String.valueOf(android.R.id.home));

        switch (id){
            case R.id.ac_save:
                Toast.makeText(this,"Guardar datos", Toast.LENGTH_SHORT).show();
                return true;
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
