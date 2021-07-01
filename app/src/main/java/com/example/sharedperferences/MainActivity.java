package com.example.sharedperferences;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText nameTxt;
    Button saveNameBtn,showNameBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nameTxt=findViewById(R.id.nameTxt);
        saveNameBtn=findViewById(R.id.saveName);
        showNameBtn=findViewById(R.id.showName);
        SharedPreferences sp =PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor=sp.edit();
        saveNameBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getSharedPreferences("users",MODE_PRIVATE);
                if(TextUtils.isEmpty(nameTxt.getText().toString())){
                    showMessage();
                    return;
                }
                editor.putString("name",nameTxt.getText().toString());
                editor.apply();
            }
        });
        showNameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=sp.getString("name","There is no name yet");
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void showMessage(){
        new AlertDialog.Builder(this).
                setMessage("enter your name first ")
                .setIcon(R.drawable.ic_launcher_foreground)
                .setPositiveButton("Ok",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        Toast.makeText(getBaseContext(),"thanks for understanding",Toast.LENGTH_SHORT).show();
                    }
                }).create()
                .show();
    }
}