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

public class MainActivity extends AppCompatActivity {

    //Indicates whether male or female sex was selected
    private boolean maleSelect = true;
    private boolean femaleSelect = false;
    private int weight = 70;
    private int height = 150;
    private int age = 20;

    //Represent views of the user interface
    private CardView viewMale;
    private CardView viewFemale;
    private TextView tvHeight;
    private RangeSlider rsHeight;
    private FloatingActionButton btnSubtWeight;
    private FloatingActionButton btnSumWeight;
    private TextView tvWeight;
    private FloatingActionButton btnSubtAge;
    private FloatingActionButton btnSumAge;
    private TextView tvAge;
    private AppCompatButton btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);
        initComponent();
        initListener();
        initSetGenderColor();
    }

    //Initialise the components
    private void initComponent() {
        viewMale = findViewById(R.id.viewMale);
        viewFemale = findViewById(R.id.viewFemale);
        tvHeight = findViewById(R.id.tvHeight);
        rsHeight = findViewById(R.id.rsHeight);
        btnSubtWeight = findViewById(R.id.btnSubtWeight);
        btnSumWeight = findViewById(R.id.btnSumWeight);
        tvWeight = findViewById(R.id.tvWeight);
        btnSubtAge = findViewById(R.id.btnSubtAge);
        btnSumAge = findViewById(R.id.btnSumAge);
        tvAge = findViewById(R.id.tvAge);
        btnCalculate = findViewById(R.id.btnCalculate);
    }

    //Place a click listener
    private void initListener() {
        viewMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGender();
                setGenderColor();
            }
        });

        viewFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGender();
                setGenderColor();
            }
        });

        rsHeight.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(RangeSlider slider, float value, boolean fromUser) {
                DecimalFormat decimalFormat = new DecimalFormat("#.##"); // Formatting for no decinmals
                height = Integer.parseInt(decimalFormat.format(value));
                tvHeight.setText(height + " cm");
            }
        });

        btnSumWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = weight + 1;
                setWeight();
            }
        });

        btnSubtWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weight = weight - 1;
                setWeight();
            }
        });

        btnSumAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = age + 1;
                setAge();
            }
        });

        btnSubtAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = age - 1;
                setAge();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double result = calculateIMC();
                navigate(result);
            }
        });
    }

    //Change background colour
    private void changeGender() {
        maleSelect = !maleSelect;
        femaleSelect = !femaleSelect;
    }

    //Get the corresponding color and then set the color of the views
    private void setGenderColor() {
        viewMale.setCardBackgroundColor(getBackgroundColor(maleSelect));
        viewFemale.setCardBackgroundColor(getBackgroundColor(femaleSelect));
    }

    //Returns the corresponding background color
    private int getBackgroundColor(boolean isSelect) {
        @ColorRes int actualColor = isSelect ? R.color.backgroundComponentSelect : R.color.backgroundComponent;
        return ContextCompat.getColor(this, actualColor);
    }

    //Plus or Sustrain the Value
    private void setWeight() {
        tvWeight.setText(String.valueOf(weight));
    }

    private void setAge() {
        tvAge.setText(String.valueOf(age));
    }

    private double calculateIMC() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double imc = weight / (Math.pow(height / 100.0, 2));
        return Double.parseDouble(decimalFormat.format(imc));
    }

    private void navigate(double result) {
        Intent intent = new Intent(this, IMCActivity.class);
        intent.putExtra("IMC-Result", result);
        startActivity(intent);
    }

    private void initSetGenderColor() {
        setGenderColor();
        setWeight();
        setAge();
    }
}