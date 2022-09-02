/* Laura e Evandro */
package com.example.comprassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase bd;
    AdapterProdutos adaptador;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAdiciona = findViewById(R.id.btnAdiciona);
        ListView lista = findViewById(R.id.lista);

        bd = openOrCreateDatabase("compras", MODE_PRIVATE, null);
        bd.execSQL("CREATE TABLE IF NOT EXISTS produtos(nome VARCHAR, marca VARCHAR, quantidade VARCHAR, comprado VARCHAR)");

        cursor = bd.rawQuery("SELECT _rowid_ _id, nome, marca, quantidade, comprado FROM produtos", null);
        adaptador = new AdapterProdutos(this, cursor);
        lista.setAdapter(adaptador);

        btnAdiciona.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), DigitaActivity.class);
            startActivityForResult(intent, 1);
        });

        lista.setOnItemClickListener(((adapterView, view, i, l) -> {
            cursor = (Cursor) adaptador.getItem(i);
            int rowid = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            String comprado = cursor.getString(cursor.getColumnIndexOrThrow("comprado"));
            if (comprado.equals("")) {
                bd.execSQL("UPDATE produtos SET comprado = '* COMPRADO *' WHERE _rowid_ = " + rowid);
            } else {
                bd.execSQL("UPDATE produtos SET comprado = '' WHERE _rowid_ = " + rowid);
            }
            Cursor cursor = bd.rawQuery("SELECT _rowid_ _id, nome, marca, quantidade, comprado FROM produtos", null);
            adaptador.changeCursor(cursor);
        }));

        lista.setOnItemLongClickListener((adapterView, view, i, l) -> {
            cursor = (Cursor) adaptador.getItem(i);
            int rowid = cursor.getInt(cursor.getColumnIndexOrThrow("_id"));
            bd.execSQL("DELETE FROM produtos WHERE _rowid_ = " + rowid);
            Cursor cursor = bd.rawQuery("SELECT _rowid_ _id, nome, marca, quantidade, comprado FROM produtos", null);
            adaptador.changeCursor(cursor);
            return true;
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String nome = data.getStringExtra("nome");
                String marca = data.getStringExtra("marca");
                String quantidade = data.getStringExtra("quantidade");
                String comprado = data.getStringExtra("comprado");
                bd.execSQL("INSERT INTO produtos(nome, marca, quantidade, comprado) VALUES('" + nome + "', '" + marca + "', '" + quantidade + "', '" + comprado + "')");
                Cursor cursor = bd.rawQuery("SELECT _rowid_ _id, nome, marca, quantidade, comprado FROM produtos", null);
                adaptador.changeCursor(cursor);
            }
        }
    }
}