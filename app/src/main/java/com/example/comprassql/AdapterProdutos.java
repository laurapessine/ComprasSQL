package com.example.comprassql;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cursoradapter.widget.CursorAdapter;

public class AdapterProdutos extends CursorAdapter {
    public AdapterProdutos(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.item_lista, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvNomeProduto = view.findViewById(R.id.tvNomeProduto);
        TextView tvMarcaProduto = view.findViewById(R.id.tvMarcaProduto);
        TextView tvQuantidadeProduto = view.findViewById(R.id.tvQuantidadeProduto);
        TextView tvComprado = view.findViewById(R.id.tvComprado);

        String nomeProduto = cursor.getString(cursor.getColumnIndexOrThrow("nome"));
        String marcaProduto = cursor.getString(cursor.getColumnIndexOrThrow("marca"));
        String quantidade = cursor.getString(cursor.getColumnIndexOrThrow("quantidade"));
        String comprado = cursor.getString(cursor.getColumnIndexOrThrow("comprado"));

        tvNomeProduto.setText(nomeProduto);
        tvMarcaProduto.setText(marcaProduto);
        tvQuantidadeProduto.setText(quantidade);
        tvComprado.setText(comprado);
    }
}
