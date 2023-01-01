package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class sqlHelper extends SQLiteOpenHelper {

    private static final String db_name = "wcdb.db";
    private static final int db_version = 1;

    private static sqlHelper instance;

    static sqlHelper getInstance(Context context){
        if (instance==null)
            instance=new sqlHelper(context);
        return instance;
    }

    //Construtor do banco
    public sqlHelper(@Nullable Context context) {super(context, db_name, null, db_version);}
    //------
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "CREATE TABLE times (id INTEGER primary key, nome TEXT)"
        );
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //------

    @SuppressLint("Range")
    List<Registro> getRegistro(String valor){

        List<Registro> registros = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from times", null);

        try{
            if(cursor.moveToFirst()){
                do{
                    Registro registro = new Registro();
                    registro.nome = cursor.getString(cursor.getColumnIndex("nome"));

                    registros.add(registro);
                } while(cursor.moveToNext());
        }
    } catch (Exception e) {
    } finally {
        if (cursor != null && !cursor.isClosed())
        cursor.close();
    }
    return registros;
}

    //----
//--------
    long addAgendamento(String valor1){
    SQLiteDatabase sqLiteDatabase = getWritableDatabase();

    long id_table = 0;
    try{
        sqLiteDatabase.beginTransaction();
        ContentValues valores = new ContentValues();
        valores.put("nome", valor1);
        id_table = sqLiteDatabase.insertOrThrow("times",null, valores);
        sqLiteDatabase.setTransactionSuccessful();
    } catch (Exception e){
        Log.e("sqllite", e.getMessage(),e);

    } finally {
        sqLiteDatabase.endTransaction();
    }
    return id_table;

    }
}