package com.example.t4_interfaz_grafica;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// Diapositiva 9: implementamos la interfaz OnClickListener
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

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