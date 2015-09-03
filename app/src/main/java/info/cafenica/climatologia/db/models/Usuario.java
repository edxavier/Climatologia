package info.cafenica.climatologia.db.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.orm.SugarRecord;

import java.util.List;

import info.cafenica.climatologia.db.DbOpenHelper;

/**
 * Created by Eder Xavier Rojas on 25/08/2015.
 */
public class Usuario extends SugarRecord<Usuario>{

        //Campos de tabla
    String usuario;
    String password;

    public Usuario(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }
    public Usuario() {
    }

    public static Usuario getUserCredentials()
    {
        List<Usuario> usuarioList = Usuario.listAll(Usuario.class);
        Log.d("USER", String.valueOf(usuarioList.size()));
        if(usuarioList.size()>0) {
            Log.d("USER", "HAY USUARIO");
            return usuarioList.get(0);
        }
        else {
            Log.d("USER", "NO HAY USUARIO");
            return null;
        }

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
