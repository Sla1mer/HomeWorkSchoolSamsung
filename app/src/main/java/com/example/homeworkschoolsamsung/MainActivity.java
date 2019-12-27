package com.example.homeworkschoolsamsung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private Button inputColor;
    private Button change;
    private Button help;
    private Button exit;
    private EditText r;
    private EditText g;
    private EditText b;

    private int red;
    private int green;
    private int blue;

    private String red2;
    private String green2;
    private String blue2;

    private CheckBox left;
    private CheckBox right;

    private View viewLeftandRight;
    private View viewLeft;
    private View viewRight;
    private View emptiness;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputColor = (Button) findViewById(R.id.inputColor);
        change = (Button) findViewById(R.id.change);
        help = (Button) findViewById(R.id.help);
        exit = (Button) findViewById(R.id.exit);
        viewLeftandRight = (View) findViewById(R.id.viewLeftandRight);
        viewLeft = (View) findViewById(R.id.viewLeft);
        viewRight = (View) findViewById(R.id.viewRight);


        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (left.isChecked()){
                    viewRight.setVisibility(View.INVISIBLE);
                    viewLeft.setVisibility(View.VISIBLE);
                    viewLeft.setBackgroundColor(Color.rgb(red, green, blue));
                }else if (right.isChecked()){
                    viewLeft.setVisibility(View.INVISIBLE);
                    viewRight.setVisibility(View.VISIBLE);
                    viewRight.setBackgroundColor(Color.rgb(red, green, blue));
                }else if (right.isChecked() && left.isChecked()){
                    viewLeftandRight.setVisibility(View.VISIBLE);
                    viewLeftandRight.setBackgroundColor(Color.rgb(red, green, blue));
                }else {

                }
            }
        });

        inputColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogHelp();
            }
        });
    }

    private void showDialog(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.activity_dialog);

        Button load = dialog.findViewById(R.id.load);
        Button cancel = dialog.findViewById(R.id.cancel);
        Button ok = dialog.findViewById(R.id.ok);

        r = (EditText) dialog.findViewById(R.id.red);
        b = (EditText) dialog.findViewById(R.id.blue);
        g = (EditText) dialog.findViewById(R.id.green);

        left = (CheckBox) dialog.findViewById(R.id.left);
        right = (CheckBox) dialog.findViewById(R.id.right);

        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadtext();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a =Integer.parseInt(r.getText().toString());
                int z =Integer.parseInt(b.getText().toString());
                int c=Integer.parseInt(g.getText().toString());

                red = a;
                blue = z;
                green = c;

                red2 = r.getText().toString();
                green2 = g.getText().toString();
                blue2 = b.getText().toString();

                saveText();

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showDialogHelp(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.activity_help);

        Button back = dialog.findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void saveText(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        myEditor.putString("RED", red2);
        myEditor.putString("GREEN", green2);
        myEditor.putString("BLUE", blue2);
        myEditor.commit();

    }

    private void loadtext(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String red = myPreferences.getString("RED", red2);
        String green = myPreferences.getString("GREEN", green2);
        String blue = myPreferences.getString("BLUE", blue2);

        r.setText(red);
        g.setText(green);
        b.setText(blue);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}
