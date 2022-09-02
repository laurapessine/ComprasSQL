package com.example.comprassql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class DigitaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digita);

        Button btnInserir = findViewById(R.id.btnInserir);
        Button btnCancelar = findViewById(R.id.btnCancelar);
        EditText txtNome = findViewById(R.id.txtNome);
        EditText txtMarca = findViewById(R.id.txtMarca);
        EditText txtQuantidade = findViewById(R.id.txtQuantidade);

        btnInserir.setOnClickListener(view -> {
            Intent intent = new Intent();

            String nome = txtNome.getText().toString();
            String marca = txtMarca.getText().toString();
            String quantidade = txtQuantidade.getText().toString();

            intent.putExtra("nome", nome);
            intent.putExtra("marca", marca);
            intent.putExtra("quantidade", quantidade);
            intent.putExtra("comprado", "");

            setResult(RESULT_OK, intent);
            finish();
        });

        btnCancelar.setOnClickListener(view -> finish());
    }
}