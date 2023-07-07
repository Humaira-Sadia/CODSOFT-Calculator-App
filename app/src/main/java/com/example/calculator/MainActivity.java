package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView result, solution;
    MaterialButton buttonC, buttonOpen, buttonClose, buttonAC, buttonDot;
    MaterialButton button0, button1, button2, button3, button4,
            button5, button6, button7, button8, button9;
    MaterialButton buttonPlus, buttonMin, buttonMul, buttonDiv, buttonEqu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result_textView);
        solution = findViewById(R.id.solution_textView);

        assignBtn(buttonC, R.id.button_clear);
        assignBtn(buttonOpen, R.id.button_openBracket);
        assignBtn(buttonClose, R.id.button_closeBracket);
        assignBtn(buttonAC, R.id.button_clearAll);
        assignBtn(buttonDot, R.id.button_dot);

        assignBtn(button0, R.id.button_0);
        assignBtn(button1, R.id.button_1);
        assignBtn(button2, R.id.button_2);
        assignBtn(button3, R.id.button_3);
        assignBtn(button4, R.id.button_4);

        assignBtn(button5, R.id.button_5);
        assignBtn(button6, R.id.button_6);
        assignBtn(button7, R.id.button_7);
        assignBtn(button8, R.id.button_8);
        assignBtn(button9, R.id.button_9);

        assignBtn(buttonPlus, R.id.button_plus);
        assignBtn(buttonMin, R.id.button_minus);
        assignBtn(buttonMul, R.id.button_mul);
        assignBtn(buttonDiv, R.id.button_divide);
        assignBtn(buttonEqu, R.id.button_equalTo);
    }

    void assignBtn(MaterialButton btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String btnText = button.getText().toString();
        String dataToCalculate = solution.getText().toString();

        if(btnText.equals("AC")){
            solution.setText("");
            result.setText("0");
            return;
        }

        if(btnText.equals("=")){
//            solution.setText(result.getText());
            return;
        }

        if(btnText.equals("C")){
            solution.setText(dataToCalculate.substring(0,dataToCalculate.length()-1));
            return;
        }

        else{
            dataToCalculate = dataToCalculate+btnText;
        }

        solution.setText(dataToCalculate);
        String finalRes = calRes(dataToCalculate);

        if(!finalRes.equals("Error Occured")){
            result.setText(finalRes);
        }
    }

    private String calRes(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String result = context.evaluateString(scriptable, data, "javascript", 1, null)
                    .toString();
            return result;
        }
        catch(Exception e){
            return "Error Occured";
        }
    }
}