package com.example.zonas;

import android.content.Context;
import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class RegistrarMunicipio extends Fragment {

    private OnFragmentInteractionListener mListener;
    View rootView;
    Button guardar, cancelar;
    EditText id, etMunicipio, etSignificado, etCabecera, etSuperficie, etAltitud;
    Spinner spClima;
    String desastre;
    public SQLite sqlite;
    CheckBox c1, c2, c3, c4, c5, c6;


    String clim, longitud, latitud;
    Intent mapa;

    public RegistrarMunicipio() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_registrar_municipio, container, false);
        if(getArguments() != null){
            latitud = getArguments().getString("latitud_map");
            longitud = getArguments().getString("longitud_map");
        }
        /*Log.d("lat", latitud);
        Log.d("long", longitud);*/
        System.out.println("El primer valor es:" +latitud);
        System.out.println("El primer segundo es:" +longitud);


        id = (EditText) rootView.findViewById(R.id.clave_registrar);
        etMunicipio = (EditText) rootView.findViewById(R.id.municipio_registrar);
        etSignificado = (EditText) rootView.findViewById(R.id.sig_registrar);
        etCabecera = (EditText) rootView.findViewById(R.id.cabecera_registrar);
        etSuperficie = (EditText) rootView.findViewById(R.id.superficie_registrar);
        etAltitud = (EditText) rootView.findViewById(R.id.altitud_registrar);

        c1  = (CheckBox) rootView.findViewById(R.id.ch_inundacion);
        c2  = (CheckBox) rootView.findViewById(R.id.ch_deslave);
        c3  = (CheckBox) rootView.findViewById(R.id.ch_zonasismica);
        c4  = (CheckBox) rootView.findViewById(R.id.ch_incendio);
        c5  = (CheckBox) rootView.findViewById(R.id.ch_zonavolcanica);
        c6  = (CheckBox) rootView.findViewById(R.id.ch_derrumbes);

        spClima = (Spinner) rootView.findViewById(R.id.clima_crear);
        guardar = (Button) rootView.findViewById(R.id.btn_guardar_crear);
        cancelar = (Button) rootView.findViewById(R.id.btn_cancelar_crear);


        sqlite = new SQLite(getContext());
        sqlite.abrir();
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.clima, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spClima.setAdapter(adapter);

        spClima.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String opcion = String.valueOf(spClima.getSelectedItemId());
                int op = Integer.parseInt(opcion);
                System.out.println(opcion);
                clim = adapter.getItem(op).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /*if (c1.isChecked()){
            desastre =

        }*/


        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!id.getText().toString().equals("") &&
                        !etMunicipio.getText().toString().equals("") &&
                        !etSignificado.getText().toString().equals("") &&
                        !etCabecera.getText().toString().equals("") &&
                        !etSuperficie.getText().toString().equals("") &&
                        !etAltitud.getText().toString().equals("")) {
                    Toast.makeText(getContext(), clim + " " + id.getText().toString() +
                            etMunicipio.getText().toString().toUpperCase() + " " + etSignificado.getText().toString() + " " +
                            etCabecera.getText().toString() + " " + etSuperficie.getText() + " " + etAltitud.getText(), Toast.LENGTH_SHORT).show();
                    if (true) {

                        ArrayList<String> lista = new ArrayList<String>();
                        lista.add(id.getText().toString());
                        lista.add(etMunicipio.getText().toString());
                        lista.add(etSignificado.getText().toString());
                        lista.add(etCabecera.getText().toString());
                        lista.add(etSuperficie.getText().toString());
                        lista.add(etAltitud.getText().toString());
                        lista.add(clim);

                        //Lo envío al activity
                        mapa =new Intent(view.getContext(), MapsActivity.class);
                        mapa.putStringArrayListExtra("lista",lista);
                        startActivity(mapa);

                        System.out.println(id);
                        System.out.println(etMunicipio);
                        System.out.println(etSignificado);
                        System.out.println(etCabecera);

                        id.setText("");
                        etMunicipio.setText("");
                        etSignificado.setText("");
                        etCabecera.setText("");
                        etSuperficie.setText("");
                        etAltitud.setText("");
                        spClima.setSelection(0);
                        spClima.setId(0);


                    } else {
                        Toast.makeText(getContext(), "Error, verifique sus datos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error, no puede haber campos vacíos", Toast.LENGTH_SHORT).show();
                }

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id.setText("");
                etMunicipio.setText("");
                etSignificado.setText("");
                etCabecera.setText("");
                etSuperficie.setText("");
                etAltitud.setText("");
                spClima.setSelection(0);
                spClima.setId(0);
            }
        });


        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
