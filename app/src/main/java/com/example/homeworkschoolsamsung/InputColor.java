package com.example.homeworkschoolsamsung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class InputColor extends AppCompatActivity {

    private EditText r;
    private EditText g;
    private EditText b;

    private String red2;
    private String green2;
    private String blue2;

    private String leftText = "left";
    private String rightText = "right";

    private CheckBox left;
    private CheckBox right;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button cancel = findViewById(R.id.cancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InputColor.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button load = findViewById(R.id.load);
        Button ok = findViewById(R.id.ok);
        r = (EditText) findViewById(R.id.red);
        b = (EditText) findViewById(R.id.blue);
        g = (EditText) findViewById(R.id.green);

        left = (CheckBox) findViewById(R.id.left);
        right = (CheckBox) findViewById(R.id.right);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadtext();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                red2 = r.getText().toString();
                green2 = g.getText().toString();
                blue2 = b.getText().toString();

                Intent intent2 = new Intent(InputColor.this, MainActivity.class);
                intent2.putExtra("red",r.getText().toString());
                intent2.putExtra("green", g.getText().toString());
                intent2.putExtra("blue", b.getText().toString());
                if (left.isChecked()){
                    intent2.putExtra("checkBox",  "1");
                }else if (right.isChecked()){
                    intent2.putExtra("checkBox", "2");
                }
                saveText();
                startActivity(intent2);
            }
        });
    }

    public void saveText(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(InputColor.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString("RED", red2);
        myEditor.putString("GREEN", green2);
        myEditor.putString("BLUE", blue2);
        myEditor.commit();

    }

    private void loadtext(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(InputColor.this);
        String red = myPreferences.getString("RED", red2);
        String green = myPreferences.getString("GREEN", green2);
        String blue = myPreferences.getString("BLUE", blue2);

        red2 = red;
        green2 = green;
        blue2 = blue;

        r.setText(red2);
        g.setText(green2);
        b.setText(blue2);
    }
}
