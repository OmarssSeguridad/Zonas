package com.example.zonas;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Consultor extends AppCompatActivity {

    Button buscar, salir;
    private SQLite sqlite;
    EditText id;
    ListView l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultor);

        id = (EditText) findViewById(R.id.clave_listar_consul);
        buscar = (Button) findViewById(R.id.btn_buscar_consul);
        salir = (Button) findViewById(R.id.salir_consul);
        l = (ListView) findViewById(R.id.lista_consul);

        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlite = new SQLite(getApplicationContext());
                sqlite.abrir();
                System.out.println("Entraste");
                if (id.getText().length() > 0) {
                    if (sqlite.getCantMun(Integer.parseInt(id.getText().toString())).getCount() == 1) {
                        Cursor cursor = sqlite.getRegistroProd2(Integer.parseInt(id.getText().toString()));
                        ArrayList<String> reg = sqlite.getProd(cursor);
                        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, reg);
                        l.setAdapter(adaptador);

                    } else {
                        Toast.makeText(getApplicationContext(), "Error, no se encontraron coincidencias con el id: " + id.getText(), Toast.LENGTH_SHORT).show();
                        id.setText("");
                        l.setAdapter(null);
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Llene el campo Clave IGECEM", Toast.LENGTH_SHORT).show();
                }
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
