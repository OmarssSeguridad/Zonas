package com.example.zonas;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EliminarMunicipio extends Fragment {

    private OnFragmentInteractionListener mListener;
    View rootView;
    Button eliminar;
    private SQLite sqlite;
    EditText id;

    public EliminarMunicipio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_eliminar_municipio, container, false);

        id = (EditText) rootView.findViewById(R.id.id_eliminar);
        eliminar = (Button) rootView.findViewById(R.id.btn_eliminar_elim);

        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                sqlite = new SQLite(getContext());
                sqlite.abrir();
                System.out.println("Entraste");

                builder.setMessage("¿Está seguro que desea eliminar este registro?");
                builder.setTitle("Confirmación");
                if (id.getText().toString() != null) {
                    if (sqlite.getCantMun(Integer.parseInt(id.getText().toString())).getCount() == 1) {
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                sqlite.Eliminar(id.getText());
                                Log.i("Dialogos", "Confirmacion Aceptada.");
                                dialog.cancel();
                                Toast.makeText(getContext(), "Registro Eliminado", Toast.LENGTH_SHORT).show();
                                id.setText("");
                            }
                        }).setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("Dialogos", "Confirmacion Cancelada.");
                                dialog.cancel();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    } else {
                        Toast.makeText(getContext(), "Error, no se encontraron coincidencias con el id: " + id.getText(), Toast.LENGTH_SHORT).show();
                        id.setText("");
                    }

                } else {

                    Toast.makeText(getContext(), "Llene el campo código", Toast.LENGTH_SHORT).show();
                }
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
