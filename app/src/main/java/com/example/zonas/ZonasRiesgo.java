package com.example.zonas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ZonasRiesgo extends AppCompatActivity {
    Button guardar, cancelar;
    EditText id, id_municipio;
    CheckBox c1, c2, c3, c4, c5, c6;
    public SQLite sqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zonas_riesgo);

        id = (EditText) findViewById(R.id.id_zona);
        id_municipio =(EditText) findViewById(R.id.id_municipio);

        c1  = (CheckBox) findViewById(R.id.ch_inundacion);
        c2  = (CheckBox) findViewById(R.id.ch_deslave);
        c3  = (CheckBox) findViewById(R.id.ch_zonasismica);
        c4  = (CheckBox) findViewById(R.id.ch_incendio);
        c5  = (CheckBox) findViewById(R.id.ch_zonavolcanica);
        c6  = (CheckBox) findViewById(R.id.ch_derrumbes);

        guardar = (Button) findViewById(R.id.guardar_zona);
        cancelar = (Button) findViewById(R.id.cancelar_zona);

        sqlite = new SQLite();
        sqlite.abrir();

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
