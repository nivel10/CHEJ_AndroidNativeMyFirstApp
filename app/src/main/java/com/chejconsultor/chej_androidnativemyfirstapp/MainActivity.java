package com.chejconsultor.chej_androidnativemyfirstapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //region Attributes

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private EditText editTextResult;
    private TextView textViewTextMessages;

    private String value1;
    private String value2;

    private Double number1;
    private Double number2;

    private String resul;
    private String message;

    //endregion Atributes

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //  Set initial data
        this.InitialData();

        //  Calculate average
        this.GetToast();
    }

    //region Activity States

     /*
     @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "OnStart", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de hacerse visible.
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "OnResume", Toast.LENGTH_SHORT).show();
        // La actividad se ha vuelto visible (ahora se "reanuda").

        this.GetToast();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "OnPause", Toast.LENGTH_SHORT).show();
        // Enfocarse en otra actividad  (esta actividad está a punto de ser "detenida").
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "OnStop", Toast.LENGTH_SHORT).show();
        // La actividad ya no es visible (ahora está "detenida")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "OnDestroy", Toast.LENGTH_SHORT).show();
        // La actividad está a punto de ser destruida.
    }
    */

    //endregion Activity States

    //region Methods

    private void InitialData() {

        this.editTextNumber1 = findViewById(R.id.txtNumber1);
        this.editTextNumber2 = findViewById(R.id.txtNumber2);
        this.editTextResult = findViewById(R.id.txtResult);
        this.textViewTextMessages = findViewById(R.id.txtMessages);
        this.editTextResult.setEnabled(false);
    }

    public void SetOperationAdd(
            View _view)
    {
        this.SetOperation(0);
    }

    public void SetOperationSub(
            View _view)
    {
        this.SetOperation(1);
    }

    public void SetOperationMul(
            View _view)
    {
        this.SetOperation(2);
    }

    public void SetOperationDiv(
            View _view)
    {
        this.SetOperation(3);
    }

    private void GetValue()
    {
        this.value1 = editTextNumber1.getText().toString();
        this.value2 = editTextNumber2.getText().toString();
    }

    private double GetResult(
            double _number1,
            double _number2,
            int _operation)
    {
        //  double result = 0.00;

        switch (_operation)
        {
            //  Add
            case 0:
                //  result = _number1 + _number2;
                //  return result;
                return  _number1 + _number2;

            //  Subtract
            case 1:
                //  result = _number1 - _number2;
                //  return result;
                return _number1 - _number2;

            //  Multiply
            case 2:
                //  result = _number1 * _number2;
                //  return result;
                return _number1 * _number2;

            //  Divide
            case 3:
                if(_number2 == 0)
                {
                    return 0;
                }
                //  result = _number1 / _number2;
                //  return result;
                return  _number1 / _number2;
        }

        return 0;
    }

    private void SetOperation(
            int _operation)
    {
        this.GetValue();

        this.number1 = Double.parseDouble(this.value1.length() == 0 ? "0" : this.value1);
        this.number2 = Double.parseDouble(this.value2.length() == 0 ? "0" : this.value2);

        Response response;
        response = this.GetResult(
                number1,
                number2,
                _operation,
                true);

        this.resul = response.Result.toString().trim();
        this.message = response.Message.trim();

        this.editTextResult.setText(this.resul);

        this.textViewTextMessages.setTextColor(response.IsSucced ? Color.GREEN : Color.RED);
        this.textViewTextMessages.setText(this.message);
    }

    private Response GetResult(
            double _number1,
            double _number2,
            int _operation,
            boolean isValid)
    {
        switch (_operation)
        {
            //  Add
            case 0:
                return  new Response(
                        true,
                        "Method add is ok...!!!",
                        this.GetFormatDecimal ((_number1 + _number2), 2));
            //  Subtract
            case 1:
                return  new Response(
                        true,
                        "Method substract is ok...!!!",
                        this.GetFormatDecimal ((_number1 - _number2), 2));

            //  Multiply
            case 2:
                return  new Response(
                        true,
                        "Method multiply is ok...!!!",
                        this.GetFormatDecimal ((_number1 * _number2), 2));

            //  Divide
            case 3:
                if(_number2 == 0)
                {
                    return  new Response(
                            false,
                            "Error cannot be divided by a value zero",
                            0);
                }
                return  new Response(
                        true,
                        "Method divide is ok...!!!",
                        this.GetFormatDecimal ((_number1 / _number2), 2));
        }

        return  new Response(
            true,
            "No operation was chosen...!!!",
            0);
    }

    private String GetFormatDecimal(
            double _value,
            int _decimal)
    {
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(_decimal);
        return decimalFormat.format(_value);
    }

    private void GetToast()
    {
        try
        {
            Response response;
            Double average = 0.00;

            response = this.AverageCalc(
                    5.35,
                    7.87,
                    9.09);

            if(!response.IsSucced)
            {
                Toast.makeText(this, response.Message, Toast.LENGTH_SHORT).show();
                return;
            }

            average = Double.parseDouble(response.Result.toString());

            response = this.IsApproved(average);

            Toast.makeText(
                    this,
                    response.Message.trim(),
                    Toast.LENGTH_SHORT).show();
        }
        catch (Exception ex)
        {
         Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private Response AverageCalc(
            double _maths,
            double _chemistry,
            double _physical) {
        try {

            double average = 0.00;
            average = (_maths + _chemistry + _physical) / 3.00;
            average = Math.round(average * 100.00 / 100.00);

            return new Response(
                    true,
                    null,
                    average);
        }
        catch (Exception ex)
        {
            return new Response(
                    false,
                    ex.getMessage(),
                    null);
        }
    }

    private Response IsApproved(
            double _average) {
        try{
            if(_average >= 6.00)
            {
                return  new Response(
                        true,
                        "Approved",
                        null);
            }
            return  new Response(
                    true,
                    "Reprobate",
                    null);
        }
        catch (Exception ex)
        {
            return new Response(
                    false,
                    ex.getMessage(),
                    null);
        }
    }

    //endregion Methods
}