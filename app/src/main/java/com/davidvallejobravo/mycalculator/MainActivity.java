package com.davidvallejobravo.mycalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btnDivision;

    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btnMultiplication;

    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btnMinus;

    private Button btn0;
    private Button btnDecimal;
    private Button btnEquals;
    private Button btnPlus;

    private Button btnClear;
    private Button btnClearEntry;

    private EditText display;

    private double firstValue = 0;
    private String operation = "";
    private Boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);
        btnDivision = findViewById(R.id.btnDivision);

        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btnMultiplication = findViewById(R.id.btnMultiplication);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btnMinus = findViewById(R.id.btnMinus);

        btn0 = findViewById(R.id.btn0);
        btnDecimal = findViewById(R.id.btnDecimal);
        btnEquals = findViewById(R.id.btnEquals);
        btnPlus = findViewById(R.id.btnPlus);

        btnClear = findViewById(R.id.btnClear);
        btnClearEntry = findViewById(R.id.btnClearEntry);

        display = findViewById(R.id.display);

        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button clickedButton = (Button) view;
                String key = clickedButton.getText().toString();
                addToDisplay(key);
            }
        };

        btn7.setOnClickListener(buttonClickListener);
        btn8.setOnClickListener(buttonClickListener);
        btn9.setOnClickListener(buttonClickListener);
        btnDivision.setOnClickListener(view -> setOperation("/"));

        btn4.setOnClickListener(buttonClickListener);
        btn5.setOnClickListener(buttonClickListener);
        btn6.setOnClickListener(buttonClickListener);
        btnMultiplication.setOnClickListener(view -> setOperation("*"));

        btn1.setOnClickListener(buttonClickListener);
        btn2.setOnClickListener(buttonClickListener);
        btn3.setOnClickListener(buttonClickListener);
        btnMinus.setOnClickListener(view -> setOperation("-"));

        btn0.setOnClickListener(buttonClickListener);
        btnDecimal.setOnClickListener(buttonClickListener);
        btnEquals.setOnClickListener(view -> calculate());
        btnPlus.setOnClickListener(view -> setOperation("+"));

        btnClear.setOnClickListener(view -> clear());
        btnClearEntry.setOnClickListener(view -> clearEntry());


    }

    public void addToDisplay(String key) {
        String currentValue = display.getText().toString();

        if (this.flag) {
            this.flag = false;
            currentValue = "0";
        }
        if (currentValue.equals("0")) {
            display.setText(key);
            return;
        }
        if (currentValue.contains(".") && key.equals("."))
            return;

        display.setText(currentValue + key);
    }

    private void setOperation(String _operation) {
        if (display.getText().toString().equals("Undefined")) {
            return;
        }

        if (this.operation.equals("")) {
            firstValue = Double.parseDouble(display.getText().toString());
            display.setText("0");
            operation = _operation;
        } else {
            this.calculate();
            this.operation = _operation;
        }
    }

    private void calculate() {
        double secondValue = Double.parseDouble(display.getText().toString());
        double result = 0;
        switch (operation) {
            case "+":
                result = firstValue + secondValue;
                break;
            case "-":
                result = firstValue - secondValue;
                break;
            case "*":
                result = firstValue * secondValue;
                break;
            case "/":
                if (secondValue == 0) {
                    firstValue = 0;
                    display.setText("Undefined");
                    this.flag = true;
                    this.operation = "";
                    return;
                }
                result = firstValue / secondValue;
                break;
        }

        this.flag = true;
        display.setText(String.valueOf(result));
        firstValue = result;
        this.operation = "";
    }

    private void clear() {
        display.setText("0");
        firstValue = 0;
    }

    private void clearEntry() {
        display.setText("0");
    }
}