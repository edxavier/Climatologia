package info.cafenica.climatologia.db.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import info.cafenica.climatologia.db.DbOpenHelper;

/**
 * Created by Eder Xavier Rojas on 26/08/2015.
 */
public class Clima extends SugarRecord<Clima> implements Parcelable {

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

    public Clima() {
    }

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


    public Clima(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(temp_actual);
        dest.writeDouble(temp_min);
        dest.writeDouble(temp_max);
        dest.writeDouble(temp_media);
        dest.writeDouble(temp_rocio);
        dest.writeDouble(temp_rocio_min);
        dest.writeDouble(temp_rocio_max);
        dest.writeDouble(temp_suelo);
        dest.writeDouble(hum_actual);
        dest.writeDouble(hum_min);
        dest.writeDouble(hum_max);
        dest.writeDouble(hum_media);
        dest.writeDouble(hum_suelo);
        dest.writeDouble(brillo_solar);
        dest.writeDouble(precipitacion);
        dest.writeString(observacion);
        dest.writeInt(fecha_productor);
        dest.writeInt(fecha_sistema);
        dest.writeInt(usuario);

    }
    private void readFromParcel(Parcel in) {

        // We just need to read back each
        // field in the order that it was
        // written to the parcel
        //Campos de tabla
        temp_actual = in.readDouble();
        temp_min = in.readDouble();
        temp_max = in.readDouble();
        temp_rocio = in.readDouble();
        temp_rocio_min = in.readDouble();
        temp_rocio_max = in.readDouble();
        temp_suelo = in.readDouble();
        temp_media = in.readDouble();

        hum_actual = in.readDouble();
        hum_min = in.readDouble();
        hum_max = in.readDouble();
        hum_suelo = in.readDouble();
        hum_media = in.readDouble();

        brillo_solar = in.readDouble();
        precipitacion = in.readDouble();

        observacion = in.readString();
        fecha_productor = in.readInt();
        fecha_sistema = in.readInt();
        usuario = in.readInt();

    }
    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Clima createFromParcel(Parcel in) {
                    return new Clima(in);
                }

                public Clima[] newArray(int size) {
                    return new Clima[size];
                }
            };
}
