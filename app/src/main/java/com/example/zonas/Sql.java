package com.example.zonas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Sql extends SQLiteOpenHelper {
    private static final String database = "municipios";
    private static final int version = 1;

    private final String tprod = "CREATE TABLE MUNICIPIO(ID_PROD INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "MUNICIPIO TEXT NOT NULL, SIGNIFICADO TEXT NOT NULL, CABECERA TEXT NOT NULL, " +
            "SUPERFICIE TETX NOT NULL, ALTITUD TEXT NOT NULL, CLIMA TEXT NOT NULL, LATITUD TEXT NOT NULL, LONGITUD TEXT NOT NULL)";

    private final String user = "CREATE TABLE USER(ID_USER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "NOMBRE TEXT NOT NULL, CLAVE TEXT NOT NULL)";

    private final String peligro = "CREATE TABLE RIESGOS(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            "IDMUNICIPIO INTEGER NOT NULL, DESASTRE TEXT NOT NULL)";



    public Sql(Context context) {
        super(context, database, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tprod);
        db.execSQL(peligro);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            db.execSQL("DROP TABLE IF EXISTS MUNICIPIO");
            db.execSQL(tprod);
            db.execSQL("DROP TABLE IF EXISTS RIESGOS");
            db.execSQL(peligro);
        }

    }
}
