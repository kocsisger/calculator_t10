package hu.unideb.inf.calculator_t10;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView resultTextView;

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

        resultTextView = findViewById(R.id.resultTextView);
    }

    public void handleSimpleButtonPressed(View view) {
        Button button = (Button)view;
        if (resultTextView.getText().toString().equals("0"))
            resultTextView.setText("");

        resultTextView.append(button.getText().toString());
    }


    public void handleCEButtonPressed(View view) {
        resultTextView.setText("0");
    }

    public void handleEQButtonPressed(View view) {
        String expression = resultTextView.getText().toString();
        String[] splitExpression = expression.split("[+\\-*/]");
        int op1 = Integer.parseInt(splitExpression[0]);
        int op2 = Integer.parseInt(splitExpression[1]);

        char operator = expression.charAt( splitExpression[0].length() );

        String result = "ERROR";
        switch (operator){
            case '+': result = "" + (op1+op2); break;
            case '-': result = "" + (op1-op2); break;
            case '*': result = "" + (op1*op2); break;
            case '/': result = "" + (op1/op2);
        }
        //Log.d("TEST_OP" , "operands: " + op1 + " " + operator + " " + op2);
        resultTextView.setText(result);
    }
}