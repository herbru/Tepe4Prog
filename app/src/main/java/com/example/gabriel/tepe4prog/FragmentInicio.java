package com.example.gabriel.tepe4prog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentInicio extends Fragment implements View.OnClickListener{

    Button boton;

    public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos){
        View vistaADevolver = InfladorDeLayouts.inflate(R.layout.layout_inicio, GrupoDeLaVista, false);

        boton = (Button)vistaADevolver.findViewById(R.id.btnIngresar);
        boton.setOnClickListener(this);
        return vistaADevolver;
    }

    public void onClick(View Vista){

        MainActivity actividadAnfitriona = (MainActivity) getActivity();

        Fragment frgLogin = new FragmentLogin();

        //voy al fragment Login usando my hermoso metodo
        actividadAnfitriona.irAFragment(frgLogin, R.id.AlojadorDeFragments);
    }

}
