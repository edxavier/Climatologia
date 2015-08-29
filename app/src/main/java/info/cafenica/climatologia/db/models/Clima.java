package info.cafenica.climatologia.db.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

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
    double temp_actual;
    double temp_min;
    double temp_max;
    double temp_rocio;
    double temp_rocio_min;
    double temp_rocio_max;
    double temp_suelo;
    double temp_media;

    double hum_actual;
    double hum_min;
    double hum_max;
    double hum_suelo;
    double hum_media;

    double brillo_solar;
    double precipitacion;

    String observacion;
    Date fecha;
    int usuario;

    public Clima(Context context) {
        this.context = context;
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
        cv.put("user", this.usuario);
        //cv.put("password",this.password);
        return db.insert("usuario", null, cv);
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

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
