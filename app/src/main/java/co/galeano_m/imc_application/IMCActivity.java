package co.galeano_m.imc_application;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.slider.RangeSlider;

import java.text.DecimalFormat;

public class IMCActivity extends AppCompatActivity {
    private TextView tvResult;
    private TextView tvIMC;
    private TextView tvDescription;
    private AppCompatButton btnReCalculate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imcactivity);

        double result = getIntent().getDoubleExtra("IMC-Result", -1.0);
        initComponents();
        initListeners();
        initUI(result);
    }
    private void initUI(double result) {
        tvIMC.setText(String.valueOf(result));
        if (result >= 0) {
            if (result >= 0.00 && result <= 18.50) { // Bajo Peso
                tvResult.setText(getString(R.string.title_bajo_peso));
                tvResult.setTextColor(ContextCompat.getColor(this, R.color.lowWeight));
                tvDescription.setText(getString(R.string.description_bajo_peso));
            } else if (result >= 18.51 && result <= 24.99) { // Peso Normal
                tvResult.setText(getString(R.string.title_peso_normal));
                tvDescription.setText(getString(R.string.description_peso_normal));
            } else if (result >= 25.00 && result <= 29.99) { // Sobrepeso
                tvResult.setText(getString(R.string.title_sobrepeso));
                tvDescription.setText(getString(R.string.description_sobrepeso));
            } else if (result >= 30.00 && result <= 99.00) { // Obesidad
                tvResult.setText(getString(R.string.title_obesidad));
                tvDescription.setText(getString(R.string.description_obesidad));
            } else {
                handleResultError();
            }
        } else {
            handleResultError();
        }
    }

    private void handleResultError() {
        tvResult.setText(getString(R.string.error));
        tvIMC.setText(getString(R.string.error));
        tvDescription.setText(getString(R.string.error));
    }

    private void initComponents() {
        tvResult = findViewById(R.id.tvResult);
        tvIMC = findViewById(R.id.tvIMC);
        tvDescription = findViewById(R.id.tvDescription);
        btnReCalculate = findViewById(R.id.btnReCalculate);
    }

    private void initListeners() {
        btnReCalculate.setOnClickListener(v -> onBackPressed());
    }
}