package com.example.zonas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Intent i_admin, i_consultor;
    EditText etUser, etPass;
    String sUser, sPass;
    Button btn, btn2;
    private Cursor fila;
    SQLite sqlite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn = (Button) findViewById(R.id.btn_enviar);
        btn.setOnClickListener(this);
        btn2 = (Button) findViewById(R.id.btn_cancelar);
        btn2.setOnClickListener(this);

        etUser = (EditText) findViewById(R.id.txt_user);
        etPass = (EditText) findViewById(R.id.txt_password);

    }

    @Override
    public void onClick(View view) {
        i_admin = new Intent(view.getContext(), MainActivity.class);
        i_consultor = new Intent(view.getContext(), Consultor.class);

        switch (view.getId()) {
            case R.id.btn_enviar:
                sUser = etUser.getText().toString();
                sPass = etPass.getText().toString();
                if (!sUser.equals("") && !sPass.equals("")) {
                    if (sUser.equals("admin") && sPass.equals("admin")) {
                        startActivity(i_admin);
                        etUser.setText("");
                        etPass.setText("");
                        Toast.makeText(this, "Ingresando como administrador", Toast.LENGTH_SHORT).show();

                    } else {
                        if (sUser.equals("consultor") && sPass.equals("consultor")) {
                            startActivity(i_consultor);
                            etUser.setText("");
                            etPass.setText("");
                            Toast.makeText(this, "Ingresando como consultor", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this, "No se encontraron coincidencias", Toast.LENGTH_SHORT).show();
                            etUser.setText("");
                            etPass.setText("");
                        }
                    }
                } else {
                    Toast.makeText(this, "Error, no puede haber campos vacíos", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_cancelar:
                System.exit(0);
                break;

        }
    }
}
