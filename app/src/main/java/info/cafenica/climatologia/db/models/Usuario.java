package info.cafenica.climatologia.db.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import info.cafenica.climatologia.db.DbOpenHelper;

/**
 * Created by Eder Xavier Rojas on 25/08/2015.
 */
public class Usuario {

    DbOpenHelper dbOpenHelper = null;
    Context context = null;
    private SQLiteDatabase db = null;
    //Campos de tabla
    String usuario;
    String password;


    public Usuario(Context context) {
        this.context = context;
        if(dbOpenHelper==null)
            dbOpenHelper = new DbOpenHelper(context);
    }

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    public Usuario() {
    }

    /**
     ********* Operaciones sobre Sqlite *********
     */
    public long insert()
    {
        db = dbOpenHelper .getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("user", this.usuario);
        cv.put("password",this.password);
        return db.insert("usuario", null, cv);
    }
    public int delete()
    {
        db = dbOpenHelper.getWritableDatabase();
        return db.delete("usuario",null, null);
    }

    public Usuario getUserCredentials()
    {
        db = dbOpenHelper.getWritableDatabase();
        String [] cols = new  String [] {"_id", "user", "password"};
        //Cursor cursor = info.cafenica.climatologia.db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
        Cursor cursor = db.query("usuario", cols,null, null, null, null, null);
        Usuario usuario = null;
        if(cursor.moveToFirst())
        {
            usuario = new Usuario();
            usuario.setUsuario(cursor.getString(cursor.getColumnIndex("user")));
            usuario.setPassword(cursor.getString(cursor.getColumnIndex("password")));
        }
        this.usuario = usuario.getUsuario();
        return usuario;
    }

    /**
     ********* Getter y Setter de los campos *********
     */
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
