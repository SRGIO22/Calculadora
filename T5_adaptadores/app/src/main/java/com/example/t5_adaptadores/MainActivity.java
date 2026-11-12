
package com.example.t5_adaptadores;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.t5_adaptadores.R;

public class MainActivity extends Activity {

    ListView lista;
    TextView texto;

    String[] paises = {"España", "Francia", "Alemania", "Italia", "Portugal", "Reino Unido", "Bélgica", "Países Bajos", "Suiza", "Noruega"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listViewPaises);
        texto = findViewById(R.id.textViewPais);

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises);
        lista.setAdapter(adaptador);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
                        texto.setText(paises[position]);
                    }
                }
        );
    }
}
