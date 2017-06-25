package com.example.gabriel.tepe4prog;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentManager AdministradorDeFragments;
    FragmentTransaction TransaccionesDeFragments;

    DBHelper accesoDb;
    SQLiteDatabase Db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdministradorDeFragments = getSupportFragmentManager();
        Fragment frgInicio = new FragmentInicio();

        TransaccionesDeFragments = AdministradorDeFragments.beginTransaction();
        TransaccionesDeFragments.replace(R.id.AlojadorDeFragments, frgInicio);
        TransaccionesDeFragments.commit();
    }

    /* Esta funcion muestra un Fragment pasado como parametro en un Holder pasado como parametro*/
    public void irAFragment(Fragment fragmentAPasar, int idDelHolder) {
        FragmentManager frgManager;
        FragmentTransaction frgTransaction;

        frgManager = getSupportFragmentManager();

        frgTransaction = frgManager.beginTransaction();
        frgTransaction.replace(idDelHolder, fragmentAPasar);
        frgTransaction.commit();
    }

    //Devuelve true si pudo abrir la DB y false si no pudo
    public boolean DbAbierta() {
        boolean responder = false;
        //declaro el helper y la base de datos
        accesoDb = new DBHelper(this, "baseTP4", null, 1);
        Db = accesoDb.getWritableDatabase();

        //verifico que exista, comporbando que no sea null
        if (Db != null) {
            responder = true;
        }
        return responder;
    }

    //devuelve true si las credenciales ingresadas coinciden con las de la DB
    public boolean Loguearse(String usuario, String Contraseña) {
        //entro al if si hay una DB
        if (DbAbierta()) {
            //ejecuto una consulta que devuelve los registros
            Cursor Registros = Db.rawQuery("select * from Usuarios", null);
            //si hay registros entro al if y la repetitiva
            if (Registros.moveToFirst()) {
                //leo los registros hasta que encuentre un usuario y contraseña igual al ingresado o que termine de recorer los registros
                do {
                    //si el nombre ingresado es igual al del registro devuelvo true
                    if (usuario.compareTo(Registros.getString(0)) == 0 && Contraseña.compareTo(Registros.getString(1)) == 0) {
                        return true;
                    }
                } while (Registros.moveToNext());
            }
        }
        Db.close();
        //si no encontre un usuario y contraseña iguales a las ingresadas o no pude abrir la Db devuelvo false
        return false;
    }

    //Inserta un registro en la DB con las credenciales pasadas como parametro
    public void RegistrarUsuario(String Usuario, String Contraseña) {
        boolean usuarioValido = true;
        //entro al if si hay una DB
        if (DbAbierta()) {
                //Instancio un registro nuevo
                ContentValues nuevoRegistro = new ContentValues();
                //lleno el registro instanciado con el nombre pasado como parametro
                nuevoRegistro.put("Usuario", Usuario);
                nuevoRegistro.put("Contrasena", Contraseña);
                //ejecuto la consulta insert con el registro nuevo
                Db.insert("Usuarios", null, nuevoRegistro);
        }
        Db.close();
    }

    //devuelve true si el usuario pasado como parametro no coincide con un registro de la DB, es decir que es valido para usar
    public boolean usuarioValido(String usuario){
        boolean valido = true;
        //listo los nombres de usuario de la DB
        ArrayList<String> listaUsuarios = ListarUsuarios();
        //leo todos los usuarios del ArrayList
        for (String usuarioUsado: listaUsuarios) {
            //comparo el nombre de usuario pasado como parametro con el registro que estoy recorriendo en el momento
            if (usuario.compareTo(usuarioUsado) == 0){
                //si el registro y el parametro son iguales devuelvo false
                valido = false;
            }
        }
        //si no encontro un registro igual en la DB al que pase como parametro devuelvo true
        return valido;
    }

    //Devuelve una lista con todos los nombres de usuario que hay en la DB
    public ArrayList<String> ListarUsuarios() {
        //entro al if si hay una DB
        ArrayList<String> listaUsuarios = new ArrayList<>();
        if (DbAbierta()) {
            //ejecuto una consulta que devuelve los registros
            Cursor Registros = Db.rawQuery("select Usuario from Usuarios", null);
            //si hay registros entro al if y la repetitiva
            if (Registros.moveToFirst()) {

                //leo todos los registros y los agrego uno por uno al arraylist
                do {
                    listaUsuarios.add(Registros.getString(0));
                }
                while (Registros.moveToNext());
            }
        }
        return  listaUsuarios;
    }
}