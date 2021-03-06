package info.cafenica.climatologia.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eder Xavier Rojas on 25/08/2015.
 */
public class DbOpenHelper extends SQLiteOpenHelper {
    static  String DB_NAME = "cafenica";
    static int DB_VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table usuario ( _id int primary key, user text, password text)";
        db.execSQL(sql);
        sql = "create table clima ( _id int primary key, temp_act double, temp_min double, temp_max double," +
                "temp_media double, temp_rocio double, temp_rocio_min double, temp_rocio_max double, temp_suelo double," +
                "hum_act double, hum_min double, hum_max double, hum_suelo double, hum_media double," +
                "brillo double, precipitacion double, observacion text, fecha_prod int, fecha_sist int, usuario int)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario");
        onCreate(db);
    }
}
