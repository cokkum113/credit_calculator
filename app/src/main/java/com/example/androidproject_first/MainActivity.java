package com.example.androidproject_first;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Switch stSwitch;
    LinearLayout view1;

    EditText edName, midTest, finalTest, project, seatCheck;
    RadioGroup radioGp;
    RadioButton grade1, grade2, grade3, grade4;
    Button calBtn, calReturn;
    TextView resultTxt;
    ImageView imageView;
    View dialog1;

    double total;
    String grade = "";
    String name = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("18017073_전혜수");
        stSwitch = findViewById(R.id.stSwitch);
        view1 = findViewById(R.id.view1);
        edName = findViewById(R.id.edName);
        midTest = findViewById(R.id.midTest);
        finalTest = findViewById(R.id.finalTest);
        project = findViewById(R.id.project);
        seatCheck = findViewById(R.id.seatCheck);
        radioGp = findViewById(R.id.radioGp);
        calBtn = findViewById(R.id.calBtn);
        calReturn = findViewById(R.id.calReturn);
        grade1 = findViewById(R.id.grade1);
        grade2 = findViewById(R.id.grade2);
        grade3 = findViewById(R.id.grade3);
        grade4 = findViewById(R.id.grade4);

        stSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (stSwitch.isChecked()) {
                    view1.setVisibility(View.VISIBLE);
                } else {
                    view1.setVisibility(View.INVISIBLE);
                }
            }
        });

        name = edName.getText().toString();

        calReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGp.clearCheck();
                edName.setText("");
                midTest.setText("");
                finalTest.setText("");
                project.setText("");
                seatCheck.setText("");
            }
        });

        calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (radioGp.getCheckedRadioButtonId()) {
                    case R.id.grade1:
                        grade = "1학년";
                        break;
                    case R.id.grade2:
                        grade = "2학년";
                        break;
                    case R.id.grade3:
                        grade = "3학년";
                        break;
                    case R.id.grade4:
                        grade = "4학년";
                        break;
                }

                total = (Integer.parseInt(midTest.getText().toString()) * 0.3) +
                        (Integer.parseInt(finalTest.getText().toString()) * 0.3) +
                        (Integer.parseInt(project.getText().toString()) * 0.2) +
                        (Integer.parseInt(seatCheck.getText().toString()) * 0.2);

                dialog1 = View.inflate(MainActivity.this, R.layout.dialog1, null);
                resultTxt = dialog1.findViewById(R.id.resultTxt);
                imageView = dialog1.findViewById(R.id.imageView);


                resultTxt.setText(grade.toString() + edName.getText().toString() + "학생의 총점은" + total);

                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                dlg.setTitle("학점계산결과");
                dlg.setView(dialog1);

                if (total >= 90) {
                    imageView.setImageResource(R.drawable.alphabet_a);
                } else if (total >= 80) {
                    imageView.setImageResource(R.drawable.alphabet_b);
                } else if (total >= 70) {
                    imageView.setImageResource(R.drawable.alphabet_c);
                } else {
                    imageView.setImageResource(R.drawable.alphabet_f);
                }
                dlg.setPositiveButton("확인", null);
                dlg.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.quit:
                finish();
                return true;
            case R.id.restart:
                stSwitch.setChecked(false);
                radioGp.clearCheck();
                edName.setText("");
                midTest.setText("");
                finalTest.setText("");
                project.setText("");
                seatCheck.setText("");
                return true;
        }
        return false;
    }



}