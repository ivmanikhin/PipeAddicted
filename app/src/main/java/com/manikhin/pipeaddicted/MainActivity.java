package com.manikhin.pipeaddicted;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.print.PrintAttributes;
import android.text.InputType;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;

import androidx.appcompat.widget.ButtonBarLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.text.DecimalFormat;
import android.view.ViewGroup;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editP1, editQ, editNu, editRo;
    int objIndex = 0/*, pipeIndex = 1, editTextWidth*/;
    float /* pipeD[], pipeL[], */ p0, p1, q, nu, ro;
    double pLoss[];


    /*
     objType - тип объекта для назначения viewID : 1 - труба, 2 - отвод ...
     objIndex - порядковый номаер объекта
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
        setContentView(R.layout.activity_main);
        setTitle("Pipe Addicted");
        Button btnAddUnit = findViewById(R.id.add_unit);
        Button btnCalc = findViewById(R.id.calc);
        btnAddUnit.setOnClickListener(this);
        btnCalc.setOnClickListener(this);
        editP1 = findViewById(R.id.editP1);
        editQ = findViewById(R.id.editQ);
        editNu = findViewById(R.id.editNu);
        editRo = findViewById(R.id.editRo);
        editP1.addTextChangedListener(textWatcher);
        editQ.addTextChangedListener(textWatcher);
        editNu.addTextChangedListener(textWatcher);
        editRo.addTextChangedListener(textWatcher);

        //editTextWidth = editP1.getWidth();

        pLoss=new double[100];
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

            CharSequence inpP1 =  "0" + editP1.getText();
            CharSequence inpQ =  "0" + editQ.getText();
            CharSequence inpNu =  "0" + editNu.getText();
            CharSequence inpRo =  "0" + editRo.getText();

            p1 = Float.parseFloat("" + inpP1);
            q = Float.parseFloat("" + inpQ);
            nu = Float.parseFloat("" + inpNu);
            ro = Float.parseFloat("" + inpRo);
/*
            if (d >= 2 * R)
            {
                textStress.setText("Отверстие больше самого изделия");
                btnCalc.setEnabled(false);
            }
            else {
                btnCalc.setEnabled(true);
                textStress.setText("Напряжения в проушине");
            }*/
        }
    };


    private void NewPipe(int objType, int objI) {   // 101 - труба №1
        int objID = 100 * objType + objI;
        int viewID = 100 * objID + 1;                          // 10101 -
        LinearLayout mainLayout = (LinearLayout)findViewById(R.id.main_layout);
        LinearLayout pipeLayout = new LinearLayout (this);
        pipeLayout.setBackgroundResource(R.color.layout_color);
        pipeLayout.setOrientation(LinearLayout.VERTICAL);
        pipeLayout.setId(objID);
        mainLayout.addView(pipeLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(20, 8,20,8);

        pipeLayout.setLayoutParams(params);


        TextView textView1 = new TextView(this);
        textView1.setTextSize(20);
        textView1.setTextColor(0xFF000000);
        textView1.setText(" Прямой участок трубы");
 //       viewID = viewID + 1;
//        textView1.setId(viewID);


        TextView textView2 = new TextView(this);
        textView2.setTextSize(18);
        textView2.setText(" Внутренний диаметр трубы, мм:");
        textView2.setTextColor(0xFF000000);
 //       viewID = viewID + 1;
 //       textView2.setId(viewID);
//        params.topToBottom = textView1.getId();
        textView2.setLayoutParams(params);



        EditText editText1 = new EditText(this);
        editText1.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        /*editText1.setWidth(200);
        editText1.setLeft(20);
        editText1.setTop(8);
        editText1.setBottom(8); */
        editText1.setTextColor(0xFF000000);
//        viewID = viewID + 1;
 //       editText1.setId(viewID);
//       params.topToBottom = textView2.getId();
        editText1.setLayoutParams(params);


        TextView textView3 = new TextView(this);
        textView3.setText(" Длина трубы, м:");
        textView3.setTextSize(18);
        textView3.setTextColor(0xFF000000);
 //       viewID = viewID + 1;
 //       textView3.setId(viewID);
 //       params.topToBottom = editText1.getId();
        textView3.setLayoutParams(params);


        EditText editText2 = new EditText(this);
        editText2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        /*editText2.setWidth(editTextWidth);
        editText2.setLeft(20);
        editText2.setTop(8);
        editText2.setBottom(8);*/
        editText2.setTextColor(0xFF000000);
  //      viewID = viewID + 1;
  //      editText2.setId(viewID);
  //      params.topToBottom = textView3.getId();
        editText2.setLayoutParams(params);

        TextView textPLoss = new TextView(this);
        textPLoss.setText("");
        textPLoss.setTextSize(18);
        textPLoss.setTextColor(0xFF000000);
        //       viewID = viewID + 1;
        //       textView3.setId(viewID);
        //       params.topToBottom = editText1.getId();
        textPLoss.setLayoutParams(params);
        textPLoss.setVisibility(View.GONE);

//        LinearLayout btnLayout = new LinearLayout (this);
 //       btnLayout.setBackgroundResource(R.color.layout_color);
 //       btnLayout.setOrientation(LinearLayout.HORIZONTAL);

        Button delBtn = new Button(this);
        delBtn.setText("Удалить");
        delBtn.setId(viewID);
        viewID++;


        Button lockBtn = new Button(this);
        lockBtn.setText("Сохранить");
        lockBtn.setId(viewID);
        viewID++;


        Button unlockBtn = new Button(this);
        unlockBtn.setText("Редактировать");
        unlockBtn.setId(viewID);
        unlockBtn.setVisibility(View.GONE);
        viewID++;

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainLayout.removeView(pipeLayout);
                pLoss[objI] = 0;
            }
        });

        lockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setVisibility(View.GONE);
                editText2.setVisibility(View.GONE);
                textView2.setVisibility(View.GONE);
                textView3.setVisibility(View.GONE);
                delBtn.setVisibility(View.GONE);
                lockBtn.setVisibility(View.GONE);
                unlockBtn.setVisibility(View.VISIBLE);
                textPLoss.setVisibility(View.VISIBLE);

                //ввод переменных

                CharSequence inpPipeD =  "0" + editText1.getText();
                CharSequence inpPipeL =  "0" + editText2.getText();

                float pipeD = Float.parseFloat("" + inpPipeD);
                float pipeL = Float.parseFloat("" + inpPipeL);

                //расчёт:

                double v = 21.2207 * q / (pipeD * pipeD);
                double Re = v * pipeD * 1000 / nu;
                double lambda = 64 / Re;
                pLoss[objI] = 0.005 * lambda * pipeL * ro * v * v / pipeD;

                //отображение потери давления:

                String strPLoss = new DecimalFormat("0.00").format(pLoss[objI]);
                String strPipeD = new DecimalFormat("0.00").format(pipeD);
                String strPipeL = new DecimalFormat("0.00").format(pipeL);
                String answPLoss = "D = " + strPipeD + " мм;    " + "L = " + strPipeL + " м\n" + "Падение давления " + strPLoss + " бар";

                textPLoss.setText(answPLoss);

            }
        });

        unlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editText1.setVisibility(View.VISIBLE);
                editText2.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                delBtn.setVisibility(View.VISIBLE);
                lockBtn.setVisibility(View.VISIBLE);
                unlockBtn.setVisibility(View.GONE);
                textPLoss.setVisibility(View.GONE);
            }
        });

        pipeLayout.addView(textView1, params);
        pipeLayout.addView(textView2, params);
        pipeLayout.addView(editText1, params);
        pipeLayout.addView(textView3, params);
        pipeLayout.addView(editText2, params);
        pipeLayout.addView(textPLoss, params);
        pipeLayout.addView(delBtn, params);
        pipeLayout.addView(lockBtn, params);
        pipeLayout.addView(unlockBtn, params);


        objIndex++;
//        pipeIndex++;
    }


    @Override
    public void onClick(View view) {
 //       Intent i;
        switch (view.getId()) {
            case R.id.add_unit:
                NewPipe(1, objIndex);
                objIndex++;
                break;
            case R.id.calc:

                break;
        }
    }
}