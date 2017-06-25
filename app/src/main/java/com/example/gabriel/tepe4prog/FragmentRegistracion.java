package com.example.gabriel.tepe4prog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentRegistracion extends Fragment implements View.OnClickListener{

    EditText txtUsuario;
    EditText txtContraseña;
    EditText txtRepetirContraseña;
    Button btnCrearNuevoUsuario;

    public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos){
        View vistaADevolver = InfladorDeLayouts.inflate(R.layout.layout_registracion, GrupoDeLaVista, false);

        txtUsuario = (EditText) vistaADevolver.findViewById(R.id.UsuarioRegistracion);
        txtContraseña = (EditText) vistaADevolver.findViewById(R.id.ContraseñaRegistracion);
        txtRepetirContraseña = (EditText) vistaADevolver.findViewById(R.id.ReoetirContraseñaRegistracion);
        btnCrearNuevoUsuario = (Button) vistaADevolver.findViewById(R.id.btnCrearUsuario);

        btnCrearNuevoUsuario.setOnClickListener(this);

        return vistaADevolver;
    }

    public void onClick(View Vista){
        MainActivity actividadAnfitriona = (MainActivity) getActivity();
        //verifico que el usuario no este usado
        if (actividadAnfitriona.usuarioValido(txtUsuario.getText().toString())) {
            //si el usuario no esta usado verifico que contraseña y repetir contraseña sean iguales
            if (txtContraseña.getText().toString().compareTo(txtRepetirContraseña.getText().toString()) == 0) {
                // si contraseña y repetir contarseña son llamo a registrar usuario y voy al fragment Login
                Fragment frgLogin = new FragmentLogin();

                actividadAnfitriona.RegistrarUsuario(txtUsuario.getText().toString(), txtContraseña.getText().toString());

                actividadAnfitriona.irAFragment(frgLogin, R.id.AlojadorDeFragments);
            }
            else{
                //si contraseña y reptir contraseña no son iguales muestro un toast
                Toast.makeText(getActivity(), "La contraseña no se repitio correctamente", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            //si el nombre de usuario ya esta usado muestro un toast
            Toast.makeText(getActivity(), "El nombre de usuario no esta disponible", Toast.LENGTH_SHORT).show();
        }
    }
}
