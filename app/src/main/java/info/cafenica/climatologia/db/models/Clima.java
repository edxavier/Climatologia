package info.cafenica.climatologia.db.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Date;

import info.cafenica.climatologia.db.DbOpenHelper;

/**
 * Created by Eder Xavier Rojas on 26/08/2015.
 */
public class Clima {
    DbOpenHelper dbOpenHelper = null;
    Context context = null;
    private SQLiteDatabase db = null;

    //Campos de tabla
    double temp_actual = 0;
    double temp_min = 0;
    double temp_max = 0;
    double temp_rocio = 0;
    double temp_rocio_min = 0;
    double temp_rocio_max = 0;
    double temp_suelo = 0;
    double temp_media = 0;

    double hum_actual = 0;
    double hum_min = 0;
    double hum_max = 0;
    double hum_suelo = 0;
    double hum_media = 0;

    double brillo_solar = 0;
    double precipitacion = 0;

    String observacion = "";
    int fecha_productor = 0;
    int fecha_sistema = 0;
    int usuario = 0;

    public Clima(Context context) {
        this.context = context;
        if(dbOpenHelper==null)
            dbOpenHelper = new DbOpenHelper(context);
    }

    public Clima() {
    }
//**************************
    /**
     ********* Operaciones sobre Sqlite *********
     */
    public long insert()
    {
        db = dbOpenHelper .getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("temp_act", this.temp_actual);
        cv.put("temp_min", this.temp_min);
        cv.put("temp_max", this.temp_max);
        cv.put("temp_media", this.temp_media);
        cv.put("temp_rocio", this.temp_rocio);
        cv.put("temp_rocio_min", this.temp_rocio_min);
        cv.put("temp_rocio_max", this.temp_rocio_max);
        cv.put("temp_suelo", this.temp_suelo);
        cv.put("hum_act", this.hum_actual);
        cv.put("hum_min", this.hum_min);
        cv.put("hum_max", this.hum_max);
        cv.put("hum_suelo", this.hum_suelo);
        cv.put("hum_media", this.hum_media);
        cv.put("brillo", this.brillo_solar);
        cv.put("precipitacion", this.precipitacion);
        cv.put("observacion", this.observacion);
        cv.put("fecha_prod", this.fecha_productor);
        cv.put("fecha_sist", this.fecha_sistema);
        cv.put("usuario", this.usuario);
        //cv.put("password",this.password);
        return db.insert("clima", null, cv);
    }

    public ArrayList<Clima> getClimaEntries()
    {
        db = dbOpenHelper.getWritableDatabase();
        String [] cols = new  String [] {"_id", "temp_act", "temp_min", "temp_max", "temp_media", "temp_rocio", "temp_rocio_min",
                "temp_rocio_max", "temp_suelo", "hum_act", "hum_min", "hum_max", "hum_suelo", "hum_media", "brillo", "precipitacion",
                "observacion", "fecha_prod", "fecha_sist", "usuario"};
        //Cursor cursor = info.cafenica.climatologia.db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
        Cursor cursor = db.query("clima", cols,null, null, null, null, null);
        Clima clima = null;
        ArrayList<Clima> entradas = new ArrayList<>();
        while(cursor.moveToNext())
        {
            clima = new Clima();
            clima.setTemp_actual(cursor.getDouble(cursor.getColumnIndex("temp_act")));
            clima.setTemp_min(cursor.getDouble(cursor.getColumnIndex("temp_min")));
            clima.setTemp_max(cursor.getDouble(cursor.getColumnIndex("temp_max")));
            clima.setTemp_media(cursor.getDouble(cursor.getColumnIndex("temp_media")));
            clima.setTemp_rocio(cursor.getDouble(cursor.getColumnIndex("temp_rocio")));
            clima.setTemp_rocio_min(cursor.getDouble(cursor.getColumnIndex("temp_rocio_min")));
            clima.setTemp_rocio_max(cursor.getDouble(cursor.getColumnIndex("temp_rocio_max")));
            clima.setTemp_suelo(cursor.getDouble(cursor.getColumnIndex("temp_suelo")));
            clima.setHum_actual(cursor.getDouble(cursor.getColumnIndex("hum_act")));
            clima.setHum_min(cursor.getDouble(cursor.getColumnIndex("hum_min")));
            clima.setHum_max(cursor.getDouble(cursor.getColumnIndex("hum_max")));
            clima.setHum_suelo(cursor.getDouble(cursor.getColumnIndex("hum_suelo")));
            clima.setHum_media(cursor.getDouble(cursor.getColumnIndex("hum_media")));
            clima.setBrillo_solar(cursor.getDouble(cursor.getColumnIndex("brillo")));
            clima.setPrecipitacion(cursor.getDouble(cursor.getColumnIndex("precipitacion")));
            clima.setObservacion(cursor.getString(cursor.getColumnIndex("observacion")));
            clima.setFecha_productor(cursor.getInt(cursor.getColumnIndex("fecha_prod")));
            clima.setFecha_sistema(cursor.getInt(cursor.getColumnIndex("fecha_sist")));
            clima.setUsuario(cursor.getInt(cursor.getColumnIndex("usuario")));
            entradas.add(clima);
        }
        cursor.close();
        db.close();
        return entradas;
    }
//***************************


    public double getTemp_actual() {
        return temp_actual;
    }

    public void setTemp_actual(double temp_actual) {
        this.temp_actual = temp_actual;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }

    public double getTemp_rocio() {
        return temp_rocio;
    }

    public void setTemp_rocio(double temp_rocio) {
        this.temp_rocio = temp_rocio;
    }

    public double getTemp_rocio_min() {
        return temp_rocio_min;
    }

    public void setTemp_rocio_min(double temp_rocio_min) {
        this.temp_rocio_min = temp_rocio_min;
    }

    public double getTemp_rocio_max() {
        return temp_rocio_max;
    }

    public void setTemp_rocio_max(double temp_rocio_max) {
        this.temp_rocio_max = temp_rocio_max;
    }

    public double getTemp_suelo() {
        return temp_suelo;
    }

    public void setTemp_suelo(double temp_suelo) {
        this.temp_suelo = temp_suelo;
    }

    public double getTemp_media() {
        return temp_media;
    }

    public void setTemp_media(double temp_media) {
        this.temp_media = temp_media;
    }

    public double getHum_actual() {
        return hum_actual;
    }

    public void setHum_actual(double hum_actual) {
        this.hum_actual = hum_actual;
    }

    public double getHum_min() {
        return hum_min;
    }

    public void setHum_min(double hum_min) {
        this.hum_min = hum_min;
    }

    public double getHum_max() {
        return hum_max;
    }

    public void setHum_max(double hum_max) {
        this.hum_max = hum_max;
    }

    public double getHum_suelo() {
        return hum_suelo;
    }

    public void setHum_suelo(double hum_suelo) {
        this.hum_suelo = hum_suelo;
    }

    public double getHum_media() {
        return hum_media;
    }

    public void setHum_media(double hum_media) {
        this.hum_media = hum_media;
    }

    public double getBrillo_solar() {
        return brillo_solar;
    }

    public void setBrillo_solar(double brillo_solar) {
        this.brillo_solar = brillo_solar;
    }

    public double getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(double precipitacion) {
        this.precipitacion = precipitacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
    public int getFecha_productor() {
        return fecha_productor;
    }

    public void setFecha_productor(int fecha_productor) {
        this.fecha_productor = fecha_productor;
    }

    public int getFecha_sistema() {
        return fecha_sistema;
    }

    public void setFecha_sistema(int fecha_sistema) {
        this.fecha_sistema = fecha_sistema;
    }

}
