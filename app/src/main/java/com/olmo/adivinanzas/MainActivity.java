package com.olmo.adivinanzas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    Map<String, String> map;
    List<String> list;
    TextView adivinanza;
    TextView fallos,aciertos,intentos;
    EditText respuesta;
    Button comprobar;
    int index;
    Integer numFallos, numIntentos;
    String resp, aux;
    int r;
    ArrayList<Integer> usados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numFallos = 0;
        numIntentos = 3;
        adivinanza = (TextView) findViewById(R.id.adivinanza);
        respuesta = (EditText) findViewById(R.id.respuesta);
        fallos = (TextView) findViewById(R.id.numFallos);
        aciertos = (TextView) findViewById(R.id.numAciertos);
        intentos =  (TextView) findViewById(R.id.numIntentos);
        aux = "";
        usados = new ArrayList();

        comprobar = (Button) findViewById(R.id.buttonComprobar);
        comprobar.setOnClickListener(this);
        index = 0;
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
        adivinanza.setText(list.get(index));


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.buttonComprobar:
                /*Obtenemos la respuesta correcta
                * ç*/
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
                    index++;
                    numIntentos=3;
                    if (index < list.size()) {
                        respuesta.setText("");
                        adivinanza.setText(list.get(index));
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

                    fallos.setText(numFallos.toString());
                }
                break;
        }
    }
}