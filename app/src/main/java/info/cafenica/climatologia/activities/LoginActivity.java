package info.cafenica.climatologia.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.TextInputLayout;




import info.cafenica.climatologia.R;
import db.models.Usuario;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    ProgressDialog pgdialog;
    TextInputLayout user_til;
    TextInputLayout passwd_til;
    Usuario usuario = null;
    String user = null;
    String password = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button) findViewById(R.id.btn_login);
        EditText user = (EditText) findViewById(R.id.text_user);
        btnLogin.setOnClickListener(this);
        user_til = (TextInputLayout) findViewById(R.id.username_text_input_layout);
        passwd_til = (TextInputLayout) findViewById(R.id.password_text_input_layout);
        user_til.setErrorEnabled(true);
        passwd_til.setErrorEnabled(true);
        usuario = new Usuario(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "Por favor espere un momento!", Toast.LENGTH_LONG).show();

        if(!validate()){
            //Toast.makeText(this, "No valido", Toast.LENGTH_LONG).show();
            return;
        }
        pgdialog = new ProgressDialog(LoginActivity.this);
        pgdialog.setIndeterminate(true);
        pgdialog.setMessage("Se estan verificando tus credenciales en el servidor.\nEsto podria tomar unos segundos.");
        pgdialog.setTitle("Autenticando...");
        pgdialog.setCancelable(false);
        pgdialog.show();

        //Falta agregar la logica de conexion con el API REST
        Log.d(TAG, String.valueOf(user.equals("edx@yahoo.com")));
        Log.d(TAG, String.valueOf(password.equals("1234")));

        if(user.equals("edx@yahoo.com")  && password.equals("1234")) {
            usuario.setUsuario(user);
            usuario.setPassword(password);
            usuario.insert();
            pgdialog.dismiss();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }else{
            pgdialog.dismiss();
            Toast.makeText(this, "Tus credenciales parecen ser invalidas, intenta nuevamente", Toast.LENGTH_LONG).show();
        }


    }

    public boolean validate(){
        boolean valid = true;
        user = ((EditText) findViewById(R.id.text_user)).getText().toString();
        password =  ((EditText) findViewById(R.id.text_password)).getText().toString();

        if (user.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(user).matches()) {
            user_til.setError("Ingrese un correo valido");
            valid = false;
        } else {
            user_til.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 ) {
            passwd_til.setError("Ingrese un minimo de 4 caracteres");
            valid = false;
        } else {
            passwd_til.setError(null);
        }
        return valid;
    }

}
