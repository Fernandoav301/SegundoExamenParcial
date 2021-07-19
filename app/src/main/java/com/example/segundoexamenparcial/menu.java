package com.example.segundoexamenparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class menu extends AppCompatActivity {
    TextView contenido, nombre, edad, genero, acc,cari,ter;
    String nombreArchivo;
    ImageView caricatura, accion, terror;
    Button reproducir,pause,stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        contenido=(TextView) findViewById(R.id.contenido);
        nombre=(TextView) findViewById(R.id.name);
        edad=(TextView) findViewById(R.id.edad);
        genero=(TextView) findViewById(R.id.genero);
        caricatura=(ImageView)findViewById(R.id.carica);
        accion=(ImageView)findViewById(R.id.acc);
        terror=(ImageView)findViewById(R.id.miedo);
        acc=(TextView)findViewById(R.id.accion);
        cari=(TextView)findViewById(R.id.caricatura);
        ter=(TextView)findViewById(R.id.terror);
        reproducir= (Button)findViewById(R.id.btn1);
        pause= (Button)findViewById(R.id.pause);
        stop= (Button)findViewById(R.id.stop);

        nombreArchivo="archivo.txt";

        LeerArchivo();


        caricatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),peliculas.class);
                startActivity(intent);
            }
        });
        accion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),peliculas.class);
                startActivity(intent);
            }
        });
        terror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),peliculas.class);
                startActivity(intent);
            }
        });



    }


    private void LeerArchivo() {
        String textoffile="";

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(nombreArchivo)));
            textoffile = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffile!=null) {
                String datos [] = textoffile.split(",");
                contenido.setText("Hola: "+datos[0]+" De acuerdo a tu edad: "+datos[1]+" las categorias disponiles son: ");
                //Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito",Toast.LENGTH_SHORT).show();

                double edad=Double.parseDouble(datos[1]);
                if(edad<12){
                      accion.setVisibility(View.INVISIBLE);
                      acc.setVisibility(View.INVISIBLE);
                      ter.setVisibility(View.INVISIBLE);
                      terror.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Tus Categorias Son Caricatura",Toast.LENGTH_SHORT).show();



                }if(edad>12 && edad<18){
                    ter.setVisibility(View.INVISIBLE);
                    terror.setVisibility(View.INVISIBLE);
                    Toast.makeText(getApplicationContext(), "Tus Categorias Son Accion y Caricatura",Toast.LENGTH_SHORT).show();


                }if(edad>18){
                Toast.makeText(getApplicationContext(), "Tus Categorias Son Terror, Accion y Caricatura",Toast.LENGTH_SHORT).show();

            }


            }
                else {
                Toast.makeText(getApplicationContext(), "El Archivo Esta Vacio",Toast.LENGTH_SHORT).show();


            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error"+e.getMessage(),Toast.LENGTH_SHORT).show();

        }

    }

}

