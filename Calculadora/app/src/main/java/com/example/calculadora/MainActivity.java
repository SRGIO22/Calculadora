package com.example.calculadora;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvPantalla; //textview

    private double acumulado = 0;
    private String operador = ""; //parac guardar la funcion que se va a usar
    private boolean nuevoNumero = true; //el ver si hay un nuevo numero

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvPantalla = findViewById(R.id.tvPantalla); //textView

        Button boton0 = findViewById(R.id.boton0);  //Creacion de los botones
        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);
        Button boton4 = findViewById(R.id.boton4);
        Button boton5 = findViewById(R.id.boton5);
        Button boton6 = findViewById(R.id.boton6);
        Button boton7 = findViewById(R.id.boton7);
        Button boton8 = findViewById(R.id.boton8);
        Button boton9 = findViewById(R.id.boton9);
        Button botonMas = findViewById(R.id.boton_mas);
        Button botonQuion = findViewById(R.id.boton_quion);
        Button botonAsterisco = findViewById(R.id.boton_asterisco);
        Button botonBarra = findViewById(R.id.boton_barra);
        Button botonComa = findViewById(R.id.boton_coma);
        Button botonIgual = findViewById(R.id.boton_igual);
        Button boton_borrar = findViewById(R.id.boton_borrar);
        Button botonC = findViewById(R.id.boton_c);

        boton0.setOnClickListener(new View.OnClickListener() { //Escribir en cada boton usando con el onclick
            @Override
            public void onClick(View v) {
                anadirDigito("0");
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("1");
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("2");
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("3");
            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("4");
            }
        });

        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("5");
            }
        });

        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("6");
            }
        });

        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("7");
            }
        });

        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("8");
            }
        });

        boton9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirDigito("9");
            }
        });

        botonMas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsarOperador("+");
            }
        });

        botonQuion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsarOperador("-");
            }
        });

        botonAsterisco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsarOperador("*");
            }
        });

        botonBarra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsarOperador("/");
            }
        });

        botonComa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anadirPunto();
            }
        });

        botonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pulsarIgual();
            }
        });

        boton_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciar_todo();
            }
        });
        botonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                borrarUltimo();
            }
        });
    }

    private void anadirDigito(String digito) { //Poner el numero en el text view
        if (nuevoNumero || tvPantalla.getText().toString().equals("0")) {
            tvPantalla.setText(digito);
            nuevoNumero = false;
        } else {
            tvPantalla.append(digito);
        }
    }

    private void anadirPunto() {
        if (nuevoNumero) {
            tvPantalla.setText("0,");
            nuevoNumero = false;
        } else if (!tvPantalla.getText().toString().contains(",")) {
            tvPantalla.append(",");
        }
    }

    private void pulsarOperador(String nuevoOperador) { //poner el calculo para que el operador coja lo escrito en el textview
        double actual = Double.parseDouble(tvPantalla.getText().toString());

        if (!operador.isEmpty() && !nuevoNumero) {
            if (!calcular(actual)) {
                return;
            }
            tvPantalla.setText(formatear(acumulado));
        } else {
            acumulado = actual;
        }

        operador = nuevoOperador;
        nuevoNumero = true;
    }

    private void pulsarIgual() { //ejecutar las opreaciones
        if (operador.isEmpty()) {
            return;
        }

        double actual = Double.parseDouble(tvPantalla.getText().toString());
        if (!calcular(actual)) {
            return;
        }

        tvPantalla.setText(formatear(acumulado));
        operador = "";
        nuevoNumero = true;
    }

    private boolean calcular(double actual) {
        switch (operador) {
            case "+":
                acumulado += actual;
                break;
            case "-":
                acumulado -= actual;
                break;
            case "*":
                acumulado *= actual;
                break;
            case "/":
                if (actual == 0) {
                    tvPantalla.setText("Error");
                    acumulado = 0;
                    operador = "";
                    nuevoNumero = true;
                    return false;
                }
                acumulado /= actual;
                break;
        }
        return true;
    }

    private String formatear(double valor) {
        return String.valueOf(valor);
    }


    private void reiniciar_todo() { //Borrar todo el contenido
        tvPantalla.setText("0");
        acumulado = 0;
        operador = "";
        nuevoNumero = true;
    }
    private void borrarUltimo() { //Borrar el anterior con el length y recortandolo
        if (nuevoNumero) {
            return;
        }

        String texto = tvPantalla.getText().toString();

        if (texto.length() > 1) {
            tvPantalla.setText(texto.substring(0, texto.length() - 1));
        } else {
            tvPantalla.setText("0");
            nuevoNumero = true;
        }
    }


}