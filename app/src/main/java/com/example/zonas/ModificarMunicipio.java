package com.example.zonas;

import android.content.Context;
import android.database.Cursor;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;


public class ModificarMunicipio extends Fragment {

    private OnFragmentInteractionListener mListener;
    View rootView;
    Button guardar, cancelar, buscar;
    EditText id, etMunicipio, etSignificado, etCabecera, etSuperficie, etAltitud;
    TextView tv1, tv2;
    Spinner spClima;
    public SQLite sqlite;
    String clim, latitud, longitud;


    public ModificarMunicipio() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_modificar_municipio, container, false);

        id = (EditText) rootView.findViewById(R.id.clave_mod);
        etMunicipio = (EditText) rootView.findViewById(R.id.municipio_mod);
        etSignificado = (EditText) rootView.findViewById(R.id.sig_mod);
        etCabecera = (EditText) rootView.findViewById(R.id.cabecera_mod);
        etSuperficie = (EditText) rootView.findViewById(R.id.superficie_mod);
        etAltitud = (EditText) rootView.findViewById(R.id.altitud_mod);
        spClima = (Spinner) rootView.findViewById(R.id.clima_mod);
        guardar = (Button) rootView.findViewById(R.id.btn_guardar_mod);
        cancelar = (Button) rootView.findViewById(R.id.btn_cancelar_mod);
        buscar = (Button) rootView.findViewById(R.id.btn_buscar_mod);
        tv1 = (TextView) rootView.findViewById(R.id.textView6);
        tv2 = (TextView) rootView.findViewById(R.id.textView7);

        etMunicipio.setVisibility(View.INVISIBLE);
        etSignificado.setVisibility(View.INVISIBLE);
        etCabecera.setVisibility(View.INVISIBLE);
        etSuperficie.setVisibility(View.INVISIBLE);
        etAltitud.setVisibility(View.INVISIBLE);
        spClima.setVisibility(View.INVISIBLE);
        guardar.setVisibility(View.INVISIBLE);
        cancelar.setVisibility(View.INVISIBLE);
        tv1.setVisibility(View.INVISIBLE);
        tv2.setVisibility(View.INVISIBLE);


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
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!id.getText().toString().equals("")) {
                    if (sqlite.getCantMun(Integer.parseInt(id.getText().toString())).getCount() == 1) {
                        etMunicipio.setVisibility(View.VISIBLE);
                        etSignificado.setVisibility(View.VISIBLE);
                        etCabecera.setVisibility(View.VISIBLE);
                        etSuperficie.setVisibility(View.VISIBLE);
                        etAltitud.setVisibility(View.VISIBLE);
                        spClima.setVisibility(View.VISIBLE);
                        guardar.setVisibility(View.VISIBLE);
                        cancelar.setVisibility(View.VISIBLE);
                        tv1.setVisibility(View.VISIBLE);
                        tv2.setVisibility(View.VISIBLE);
                        id.setEnabled(false);
                        etMunicipio.setEnabled(false);
                        buscar.setEnabled(false);

                        int f = Integer.parseInt(id.getText().toString());
                        Cursor cursor = sqlite.getCantMun(f);
                        String g1 = null, g2 = null, g3 = null, g4 = null, g5 = null, g6 = null, g7=null, g8=null;

                        if (cursor.moveToFirst()) {
                            do {
                                g1 = cursor.getString(1);
                                g2 = cursor.getString(2);
                                g3 = cursor.getString(3);
                                g4 = cursor.getString(4);
                                g5 = cursor.getString(5);
                                g6 = cursor.getString(6);
                                g7 = cursor.getString(7);
                                g8 = cursor.getString(8);


                            } while (cursor.moveToNext());
                        }

                        etMunicipio.setText(g1.toString());
                        etSignificado.setText(g2.toString());
                        etCabecera.setText(g3.toString());
                        etSuperficie.setText(g4.toString());
                        etAltitud.setText(g5.toString());

                        Log.d("Valor clima", String.valueOf(g6));

                        int pos_clima = adapter.getPosition(String.valueOf(g6));
                        spClima.setSelection(pos_clima);
                        Log.d("Item Clima", String.valueOf(pos_clima));

                    } else {
                        Toast.makeText(getContext(), "Error, no se encontraron coincidencias con el id: " + id.getText(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(getContext(), "Error, no has colocado un id ", Toast.LENGTH_SHORT).show();

                }
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!id.getText().toString().equals("") &&
                        !etMunicipio.getText().toString().equals("") &&
                        !etSignificado.getText().toString().equals("") &&
                        !etCabecera.getText().toString().equals("") &&
                        !etSuperficie.getText().toString().equals("") &&
                        !etAltitud.getText().toString().equals("")) {
                    if (sqlite.updateRegMun(Integer.parseInt(id.getText().toString()),
                            etMunicipio.getText().toString().toUpperCase(),
                            etSignificado.getText().toString().toUpperCase(),
                            etCabecera.getText().toString().toUpperCase(),
                            etSuperficie.getText().toString(),
                            etAltitud.getText().toString(), clim,latitud,longitud)) {
                        Toast.makeText(getContext(), "Registro modificado", Toast.LENGTH_LONG).show();
                        id.setText("");
                        etMunicipio.setText("");
                        etSignificado.setText("");
                        etCabecera.setText("");
                        etSuperficie.setText("");
                        etAltitud.setText("");
                        spClima.setSelection(0);
                        spClima.setId(0);

                        id.setEnabled(true);
                        etMunicipio.setEnabled(true);
                        buscar.setEnabled(true);

                        etMunicipio.setVisibility(View.INVISIBLE);
                        etSignificado.setVisibility(View.INVISIBLE);
                        etCabecera.setVisibility(View.INVISIBLE);
                        etSuperficie.setVisibility(View.INVISIBLE);
                        etAltitud.setVisibility(View.INVISIBLE);
                        spClima.setVisibility(View.INVISIBLE);
                        guardar.setVisibility(View.INVISIBLE);
                        cancelar.setVisibility(View.INVISIBLE);
                        tv1.setVisibility(View.INVISIBLE);
                        tv2.setVisibility(View.INVISIBLE);

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

                id.setEnabled(true);
                etMunicipio.setEnabled(true);
                buscar.setEnabled(true);

                etMunicipio.setVisibility(View.INVISIBLE);
                etSignificado.setVisibility(View.INVISIBLE);
                etCabecera.setVisibility(View.INVISIBLE);
                etSuperficie.setVisibility(View.INVISIBLE);
                etAltitud.setVisibility(View.INVISIBLE);
                spClima.setVisibility(View.INVISIBLE);
                guardar.setVisibility(View.INVISIBLE);
                cancelar.setVisibility(View.INVISIBLE);
                tv1.setVisibility(View.INVISIBLE);
                tv2.setVisibility(View.INVISIBLE);

            }
        });

        return rootView;
    }


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
