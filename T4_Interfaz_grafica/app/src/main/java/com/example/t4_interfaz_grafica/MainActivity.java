package com.example.t4_interfaz_grafica;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        String[] opciones = {"Opcion1", "Opcion2", "Opcion3", "Opcion4", "Opcion5"};
        AutoCompleteTextView textoLeido = findViewById(R.id.miTexto);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, opciones);
        textoLeido.setAdapter(adaptador);

        Spinner miSpinner = (Spinner) findViewById(R.id.miSpinner);
        String[] valores = {"Star Wars", "Reservoir dogs", "El Club de la lucha", "Snatch: Cerdos y Diamantes"};
        miSpinner.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, valores));

        miSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                System.out.println("Has seleccionado el valor:" + adapterView.getItemAtPosition(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                System.out.println("No has seleccionado nada");
            }
        });

        texto = findViewById(R.id.texto);
        Button boton1 = findViewById(R.id.button1);
        Button boton2 = findViewById(R.id.button2);
        Button boton3 = findViewById(R.id.button3);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto.setText("Has pulsado el botón 1");
                lanzarAnimacion(R.anim.rotate);
            }
        });

        boton2.setOnClickListener(this);

        boton3.setOnClickListener(v -> {
            texto.setText("Has pulsado el botón 3");
            texto.clearAnimation();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        texto.setText("Nuevo texto a mostrar");
        texto.setTextColor(Color.RED);
        texto.setTypeface(null, Typeface.ITALIC);
        texto.setTextSize(24);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button2) {
            texto.setText("Has pulsado el botón 2");
            lanzarAnimacion(R.anim.animacion);
        }
    }

    private void lanzarAnimacion(int recurso) {
        texto.clearAnimation();
        Animation miAnimacion = AnimationUtils.loadAnimation(this, recurso);
        miAnimacion.setRepeatMode(Animation.RESTART);
        miAnimacion.setRepeatCount(20);
        texto.startAnimation(miAnimacion);
    }

}