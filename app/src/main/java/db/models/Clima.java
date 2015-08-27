package db.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

import db.DbOpenHelper;

/**
 * Created by Eder Xavier Rojas on 26/08/2015.
 */
public class Clima {
    DbOpenHelper dbOpenHelper = null;
    Context context = null;
    private SQLiteDatabase db = null;

    //Campos de tabla
    float temp_actual;
    float temp_min;
    float temp_max;
    float temp_rocio;
    float temp_rocio_min;
    float temp_rocio_max;
    float temp_suelo;
    float hum_actual;
    float hum_min;
    float hum_max;
    float hum_suelo;
    float brillo_solar;
    float precipitacion;
    String observacion;
    Date fecha;
    int usuario;

    public Clima(Context context) {
        this.context = context;
    }

    public Clima() {
    }
//**************************

//***************************
    public float getTemp_actual() {
        return temp_actual;
    }

    public void setTemp_actual(float temp_actual) {
        this.temp_actual = temp_actual;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }

    public float getTemp_rocio() {
        return temp_rocio;
    }

    public void setTemp_rocio(float temp_rocio) {
        this.temp_rocio = temp_rocio;
    }

    public float getTemp_rocio_min() {
        return temp_rocio_min;
    }

    public void setTemp_rocio_min(float temp_rocio_min) {
        this.temp_rocio_min = temp_rocio_min;
    }

    public float getTemp_rocio_max() {
        return temp_rocio_max;
    }

    public void setTemp_rocio_max(float temp_rocio_max) {
        this.temp_rocio_max = temp_rocio_max;
    }

    public float getTemp_suelo() {
        return temp_suelo;
    }

    public void setTemp_suelo(float temp_suelo) {
        this.temp_suelo = temp_suelo;
    }

    public float getHum_actual() {
        return hum_actual;
    }

    public void setHum_actual(float hum_actual) {
        this.hum_actual = hum_actual;
    }

    public float getHum_min() {
        return hum_min;
    }

    public void setHum_min(float hum_min) {
        this.hum_min = hum_min;
    }

    public float getHum_max() {
        return hum_max;
    }

    public void setHum_max(float hum_max) {
        this.hum_max = hum_max;
    }

    public float getHum_suelo() {
        return hum_suelo;
    }

    public void setHum_suelo(float hum_suelo) {
        this.hum_suelo = hum_suelo;
    }

    public float getBrillo_solar() {
        return brillo_solar;
    }

    public void setBrillo_solar(float brillo_solar) {
        this.brillo_solar = brillo_solar;
    }

    public float getPrecipitacion() {
        return precipitacion;
    }

    public void setPrecipitacion(float precipitacion) {
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
