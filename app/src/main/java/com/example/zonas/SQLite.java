package com.example.zonas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Editable;
import android.util.Log;

import java.util.ArrayList;

public class SQLite {

    private Sql sql;
    private SQLiteDatabase db;

    public SQLite(Context context) {
        this.sql = new Sql(context);
    }

    public SQLite() {

    }

    public void abrir() {
        Log.i("SQLite", "Se abre conexión a la base de datos: " + sql.getDatabaseName());
        db = sql.getWritableDatabase();
    }

    public void cerrar() {
        Log.i("SQLite", "Se cierra la conexión a la base de datos: " + sql.getDatabaseName());
        sql.close();
    }

    public Cursor getRegistroProd() {
        return db.rawQuery("SELECT * FROM MUNICIPIO", null);
    }

    public Cursor getRegistroProd2(int id) {
        return db.rawQuery("SELECT * FROM MUNICIPIO WHERE ID_PROD = " + id, null);
    }

    public Cursor getZona(int id) {
        return db.rawQuery("SELECT * FROM RIESGOS WHERE ID = " + id, null);
    }

    public Cursor getRegistroUser() {
        return db.rawQuery("SELECT NOMBRE, CLAVE FROM USER WHERE NOMBRE", null);
    }

    public boolean addRegistroMun(int id, String municipio, String significado, String cabecera, String superficie, String altitud, String clima, String latitud, String longitud) {
        ContentValues cv = new ContentValues();
        cv.put("ID_PROD", id);
        cv.put("MUNICIPIO", municipio);
        cv.put("SIGNIFICADO", significado);
        cv.put("CABECERA", cabecera);
        cv.put("SUPERFICIE", superficie);
        cv.put("ALTITUD", altitud);
        cv.put("CLIMA", clima);
        cv.put("LATITUD", latitud);
        cv.put("LONGITUD", longitud);
        return (db.insert("MUNICIPIO", null, cv) != -1) ? true : false;
    }

    public boolean addZonaRies(int id, int idmun, String desastre){
        ContentValues cv = new ContentValues();
        cv.put("ID", id);
        cv.put("IDMUNICIPIO", idmun);
        cv.put("DESASTRE", desastre);
        return (db.insert("RIESGOS", null, cv) != -1) ? true : false;
    }





    public ArrayList<String> getProd(Cursor cursor) {
        ArrayList<String> listaData = new ArrayList<String>();
        if (cursor.moveToFirst()) {
            do {
                String item = "";
                item += "Clave IGECEM: " + cursor.getInt(0) + "\r\n";
                item += "Municipio: " + cursor.getString(1) + "\r\n";
                item += "Significado Nombre: " + cursor.getString(2) + "\r\n";
                item += "Cabecera: " + cursor.getString(3) + "\r\n";
                item += "Superficie: " + cursor.getString(4) +" KM2"+ "\r\n";
                item += "Altitud: " + cursor.getString(5) + " MSNMM"+ "\r\n";
                item += "Clima: " + cursor.getString(6) + "\r\n";
                item += "Latitud: " + cursor.getString(7) + "\r\n";
                item += "Longitud: " + cursor.getString(8) + "\r\n";
                listaData.add(item);
                item = "";
            } while (cursor.moveToNext());

        }
        return listaData;
    }

    public ArrayList<String> getID(Cursor cursor) {
        ArrayList<String> listaData = new ArrayList<>();
        String item = "";
        if (cursor.moveToFirst()) {
            do {
                item += "ID: [" + cursor.getInt(0) + "]\r\n";
                listaData.add(item);
                item = "";
            } while (cursor.moveToNext());
        }
        return listaData;
    }

    public boolean updateRegMun(int id, String municipio, String significado, String cabecera, String superficie, String altitud, String clima, String latitud, String longitud) {
        ContentValues cv = new ContentValues();
        cv.put("ID_PROD", id);
        cv.put("MUNICIPIO", municipio);
        cv.put("SIGNIFICADO", significado);
        cv.put("CABECERA", cabecera);
        cv.put("SUPERFICIE", superficie);
        cv.put("ALTITUD", altitud);
        cv.put("CLIMA", clima);
        cv.put("LATITUD", latitud);
        cv.put("LONGITUD", longitud);
        int cant = db.update("MUNICIPIO", cv, "ID_PROD =" + id, null);
        if (cant >= 1) {
            return true;
        } else {
            return false;
        }

    }

    public Cursor getCantMun(int id) {
        return db.rawQuery("SELECT * FROM MUNICIPIO WHERE ID_PROD = " + id, null);
    }


    public Cursor getCantUser(int id) {
        return db.rawQuery("SELECT * FROM USER WHERE ID_PROD = " + id, null);
    }



    public int Eliminar(Editable id) {
        return db.delete("MUNICIPIO", "ID_PROD=" + id, null);
    }
}
