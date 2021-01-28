package com.olmo.adivinanzas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Map<String, String> map;
    List<String> list;
    TextView adivinanza;
    TextView fallos,aciertos,intentos;
    EditText respuesta;
    Button comprobar;
    int contador;
    Integer[] index;
    Integer numFallos, numIntentos, numAciertos;
    String resp, aux;
    int r;
    ArrayList<Integer> usados;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numFallos = numAciertos =  0;
        numIntentos = 3;
        adivinanza = (TextView) findViewById(R.id.adivinanza);
        respuesta = (EditText) findViewById(R.id.respuesta);
        fallos = (TextView) findViewById(R.id.numFallos);
        aciertos = (TextView) findViewById(R.id.numAciertos);
        intentos =  (TextView) findViewById(R.id.numIntentos);
        intentos.setText(numIntentos.toString());
        aciertos.setText("0");
        fallos.setText("0");


        aux = "";
        usados = new ArrayList();

        comprobar = (Button) findViewById(R.id.buttonComprobar);
        comprobar.setOnClickListener(this);
        contador = 0;
        list = new ArrayList<>();
        /*1*/list.add("Cuál es la estrella que no tiene luz");
        /*2*/list.add("Vuelo de noche, duermo en el día y nunca veras plumas en ala mía");
        /*3*/ list.add("Qué cosa es que cuanto más le quitas más grande es");
        /*4*/ list.add("No es más grande que una nuez, sube al monte y no tiene pies.");
        map = new HashMap<>();
        map.put("Cuál es la estrella que no tiene luz", "estrella de mar");
        map.put("Vuelo de noche, duermo en el día y nunca veras plumas en ala mía", "murcielago");
        map.put("Qué cosa es que cuanto más le quitas más grande es", "agujero");
        map.put("No es más grande que una nuez, sube al monte y no tiene pies.", "caracol");
        adivinanza.setText(list.get(contador));



        intent = getIntent();

        if(intent.getBooleanExtra("orden",true)){
            for(int i =0; i<list.size();i++){

            }
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonComprobar:

                if(numIntentos>1){
                /*Obtenemos la respuesta correcta*/
                resp = map.get(adivinanza.getText().toString());

                System.out.println(resp);
                Random random = new Random();
                r = random.nextInt(resp.length());
                while (usados.contains(r)) {
                    r = random.nextInt(resp.length());
                }
                usados.add(r);

                /*Si respuesta correcta*/
                if (respuesta.getText().toString().toLowerCase().contains(resp)) {
                    contador++;
                    numIntentos=3;
                    numAciertos++;
                    aciertos.setText(numAciertos.toString());
                    if (contador < list.size()) {
                        respuesta.setText("");
                        adivinanza.setText(list.get(contador));
                    } else {
                        adivinanza.setText("Ya no hay más :(");
                    }


                    /*si respuesta incorrecta*/
                } else {
                    numFallos++;
                    if (numIntentos == 3) {
                        aux="";
                        for (int i = 0; i < resp.length(); i++) {

                            aux = aux.concat("_");
                        }
                        numIntentos--;
                    }else{
                        numIntentos--;
                    }
                    aux = aux.substring(0, r)
                            + resp.charAt(r)
                            + aux.substring(r + 1);
                    respuesta.setText("");
                    respuesta.setHint(aux);
                    intentos.setText(numIntentos.toString());
                    fallos.setText(numFallos.toString());
                }
                /*Si haces más de 3 intentos*/
                }else{
                    contador++;
                    numIntentos=3;
                    if (contador < list.size()) {
                        respuesta.setText("");
                        respuesta.setHint("");
                        adivinanza.setText(list.get(contador));
                        Toast respuestaCorrecto = Toasty.success(this, "La respuesta era:" + resp, Toast.LENGTH_SHORT, false);
                        respuestaCorrecto.setGravity(Gravity.CENTER,0,750);
                        respuestaCorrecto.show();
                       numIntentos=3;
                       intentos.setText(numIntentos.toString());
                    } else {
                        adivinanza.setText("Ya no hay más :(");
                }
                break;
        }
    }
}}