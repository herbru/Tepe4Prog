package com.example.gabriel.tepe4prog;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class FragmentIngresoExitoso extends Fragment{

    public View onCreateView(LayoutInflater InfladorDeLayouts, ViewGroup GrupoDeLaVista, Bundle DatosRecibidos){
        View vistaADevolver = InfladorDeLayouts.inflate(R.layout.layout_ingreso_exitoso, GrupoDeLaVista, false);

        MainActivity actividadAnfitriona = (MainActivity) getActivity();

        Spinner spinner = (Spinner) vistaADevolver.findViewById(R.id.spinner);

        //declaro e inicializo un ArrayList con la lista de usuarios usando el metodo ListarUsuarios
        ArrayList<String> listaUsuarios = actividadAnfitriona.ListarUsuarios();
        //lleno el arrayadapter con la lista de usuarios
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(actividadAnfitriona, android.R.layout.simple_spinner_item, listaUsuarios);
        //aca no se bien que hace pero segui el tutorial
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //le digo al spinner que use el adapter "adaptador"
        spinner.setAdapter(adaptador);

        return vistaADevolver;
    }

}
