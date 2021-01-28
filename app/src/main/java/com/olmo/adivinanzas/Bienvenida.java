package com.olmo.adivinanzas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Bienvenida extends AppCompatActivity implements View.OnClickListener {

    Button buttonAleatorio, buttonNormal;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenida);

        buttonAleatorio = findViewById(R.id.buttonAleatorio);
        buttonAleatorio.setOnClickListener(this);

        buttonNormal = findViewById(R.id.buttonNormal);
        buttonNormal.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonAleatorio:
                 intent = new Intent(Bienvenida.this, MainActivity.class
                );
                intent.putExtra("orden",false);
                startActivity(intent);

                break;
            case R.id.buttonNormal:
                 intent = new Intent(Bienvenida.this, MainActivity.class
                );
                intent.putExtra("orden",true);
                startActivity(intent);
                break;
        }
    }
}