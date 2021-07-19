package com.example.segundoexamenparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    Button menu;
    String nombreArchivo;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        menu=(Button)findViewById(R.id.vermenu);
        nombreArchivo="archivo.txt";



        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mostrarmenu();

            }
        });


    }




    private void mostrarmenu() {
        AlertDialog.Builder alert = new AlertDialog.Builder( MainActivity.this);
        final View customlayout= getLayoutInflater().inflate(R.layout.dialog_layout, null);
        alert.setCancelable(false);
        EditText nombre= customlayout.findViewById(R.id.name);
        EditText edad= customlayout.findViewById(R.id.edad);
        EditText genero= customlayout.findViewById(R.id.genero);


        alert.setView(customlayout);
        alert.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String generos = genero.getText().toString();
                String name = nombre.getText().toString();
                String edades = edad.getText().toString();


                if(!generos.equals("") && (!edades.equals("")) && (!name.equals("")) ) {
                    GuardarCaptura (name+","+edades+","+generos);
                    Intent intent = new Intent(getApplicationContext(), menu.class);
                    startActivity(intent);


                } else {
                            Toast.makeText(getApplicationContext(), "Error no haz capturado nada", Toast.LENGTH_SHORT).show();
                        }
                    }

        });
        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void GuardarCaptura(String capturado){
        FileOutputStream fos=null;
        try {
            fos=openFileOutput(nombreArchivo, Context.MODE_PRIVATE);
        }catch (FileNotFoundException e){
            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        try {
            if(fos!=null){
                fos.write(capturado.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

        }
        try {
            if(fos != null);
            fos.close();
            menu.setText("");
            Toast.makeText(getApplicationContext(), "Contenido Guardado Con Exito",Toast.LENGTH_SHORT).show();


        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

    }


