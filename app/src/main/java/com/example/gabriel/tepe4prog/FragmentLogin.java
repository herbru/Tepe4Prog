package com.example.gabriel.tepe4prog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentLogin extends Fragment implements View.OnClickListener{

    EditText txtUsuario;
    EditText txtContraseña;
    Button btnLogin;
    Button btnRegistracion;

    public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos){
        View vistaADevolver = InfladorDeLayouts.inflate(R.layout.layout_login, GrupoDeLaVista, false);

        txtUsuario = (EditText) vistaADevolver.findViewById(R.id.UsuarioLogin);
        txtContraseña = (EditText) vistaADevolver.findViewById(R.id.ContraseñaLogin);
        btnLogin = (Button) vistaADevolver.findViewById(R.id.btnLogin);
        btnRegistracion = (Button) vistaADevolver.findViewById(R.id.btnIrARegistracion);

        btnLogin.setOnClickListener(this);
        btnRegistracion.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View Vista){
        MainActivity actividadAnfitriona = (MainActivity) getActivity();
        switch (Vista.getId()) {
            //depende del id del boton presionado hago diferentes acciones
            case R.id.btnLogin:
                //si se presiono Loguearse verifico las credenciales
                if (actividadAnfitriona.Loguearse(txtUsuario.getText().toString(), txtContraseña.getText().toString())){
                    Fragment frgIngresoExitoso = new FragmentIngresoExitoso();
                    actividadAnfitriona.irAFragment(frgIngresoExitoso, R.id.AlojadorDeFragments);
                }
                else{
                    //si las credenciales no existen en la DB devuelvo un toast
                    Toast.makeText(actividadAnfitriona, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnIrARegistracion:
                //si se presiono ir a registracion uso mi gran metodo del que estoy orgulloso para ir a la registracion
                Fragment frgRegistracion = new FragmentRegistracion();
                actividadAnfitriona.irAFragment(frgRegistracion, R.id.AlojadorDeFragments);
                break;
        }
    }
}
