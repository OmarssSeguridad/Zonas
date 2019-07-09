package com.example.zonas;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListarMunicipio extends Fragment {

    private OnFragmentInteractionListener mListener;
    View rootView;
    Button buscar;
    private SQLite sqlite;
    EditText id;
    ListView l;

    public ListarMunicipio() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_listar_municipio, container, false);

        id = (EditText) rootView.findViewById(R.id.clave_listar);
        buscar = (Button) rootView.findViewById(R.id.btn_listar);
        l = (ListView) rootView.findViewById(R.id.lista);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlite = new SQLite(getContext());
                sqlite.abrir();
                System.out.println("Entraste");
                if (id.getText().toString() != null) {
                    if (sqlite.getCantMun(Integer.parseInt(id.getText().toString())).getCount() == 1) {
                        Cursor cursor = sqlite.getRegistroProd2(Integer.parseInt(id.getText().toString()));
                        ArrayList<String> reg = sqlite.getProd(cursor);
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, reg);
                        l.setAdapter(adaptador);

                    } else {
                        Toast.makeText(getContext(), "Error, no se encontraron coincidencias con el id: " + id.getText(), Toast.LENGTH_SHORT).show();
                        id.setText("");
                        l.setAdapter(null);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
