package com.example.cuentabar;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private AutoCompleteTextView acCamarero;
    private EditText etTotal;
    private CheckBox cbPropina;
    private TextView tvPorcentaje;
    private SeekBar sbPropina;
    private RadioGroup rgPago;
    private RatingBar ratingServicio;
    private TextView tvResultado;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        acCamarero = findViewById(R.id.acCamarero);
        etTotal = findViewById(R.id.etTotal);
        cbPropina = findViewById(R.id.cbPropina);
        tvPorcentaje = findViewById(R.id.tvPorcentaje);
        sbPropina = findViewById(R.id.sbPropina);
        rgPago = findViewById(R.id.rgPago);
        ratingServicio = findViewById(R.id.ratingServicio);
        tvResultado = findViewById(R.id.tvResultado);

        String[] camareros = getResources().getStringArray(R.array.camareros);
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(
                this, android.R.layout.simple_dropdown_item_1line, camareros);
        acCamarero.setAdapter(adaptador);
        acCamarero.setThreshold(3);

        actualizarPorcentaje(sbPropina.getProgress());

        sbPropina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                actualizarPorcentaje(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @SuppressLint("StringFormatInvalid")
    private void actualizarPorcentaje(int porcentaje) {
        tvPorcentaje.setText(getString(R.string.propina_porcentaje, porcentaje));
    }

    @SuppressLint("StringFormatInvalid")
    public void calcular(View vista) {
        String textoTotal = etTotal.getText().toString().trim();

        if (textoTotal.isEmpty()) {
            mostrarError(getString(R.string.error_vacio));
            return;
        }

        textoTotal = textoTotal.replace(",", ".");
        double cuenta;
        try {
            cuenta = Double.parseDouble(textoTotal);
        } catch (NumberFormatException e) {
            mostrarError(getString(R.string.error_formato));
            return;
        }

        if (cuenta <= 0) {
            mostrarError(getString(R.string.error_minimo));
            return;
        }

        int porcentaje = 0;
        if (cbPropina.isChecked()) {
            porcentaje = sbPropina.getProgress();
        }
        double propina = cuenta * porcentaje / 100;
        double totalFinal = cuenta + propina;

        String metodo;
        if (rgPago.getCheckedRadioButtonId() == R.id.rbEfectivo) {
            metodo = getString(R.string.efectivo);
        } else {
            metodo = getString(R.string.tarjeta);
        }

        float estrellas = ratingServicio.getRating();

        String resultado = getString(R.string.resultado,
                cuenta, porcentaje, propina, totalFinal, metodo, estrellas);

        String camarero = acCamarero.getText().toString().trim();
        if (!camarero.isEmpty()) {
            resultado = resultado + "\n" + getString(R.string.camarero_resultado, camarero);
        }

        tvResultado.setTextColor(Color.BLACK);
        tvResultado.setText(resultado);
    }

    private void mostrarError(String mensaje) {
        tvResultado.setTextColor(Color.RED);
        tvResultado.setText(mensaje);
    }
}