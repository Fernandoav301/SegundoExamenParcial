package com.example.segundoexamenparcial;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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

public class peliculas extends AppCompatActivity {
    private boolean visto;
    ImageView fotografia;
    TextView contenido2;
    String nombreArchivo;
    Button reproducir,pause,stop;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas);
        fotografia=(ImageView) findViewById(R.id.fotocap);
        contenido2=(TextView)findViewById(R.id.contenido2);
        reproducir= (Button)findViewById(R.id.btn1);
        pause= (Button)findViewById(R.id.pause);
        stop= (Button)findViewById(R.id.stop);
        nombreArchivo="archivo.txt";


        visto=false;


        if(!visto)
            mostrarMensaje();

    }




    private void mostrarMensaje() {
        AlertDialog.Builder alert = new AlertDialog.Builder(peliculas.this);
        alert.setTitle(R.string.dialog_title);
        alert.setMessage(R.string.dialog_message);
        alert.setIcon(R.mipmap.bienvenida);
        alert.setPositiveButton(R.string.dialog_positive, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TomarFotografia();

            }
        });
        alert.setNegativeButton(R.string.dialog_negative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "lamentamos que no aceptaras", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),menu.class);
                startActivity(intent);
                visto=true;
            }
        });

        AlertDialog dialog = alert.create();
        dialog.show();

    }

    private void TomarFotografia() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 0);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0) {
            if(resultCode==RESULT_OK) {
                Bitmap bitmap = (Bitmap)data.getExtras().get("data");
                fotografia.setImageBitmap(bitmap);
                LeerArchivo();



            }

        }
    }
    private void LeerArchivo() {
        String textoffile="";

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openFileInput(nombreArchivo)));
            textoffile = bufferedReader.readLine();
            bufferedReader.close();
            if (textoffile!=null) {
                String datos [] = textoffile.split(",");
                contenido2.setText("Bienvenido: "+datos[0]+" tu edad es: "+datos[1]+" y tu genero es: "+datos[2]+" ya puedes reproducir tu pelicula!!");
                //Toast.makeText(getApplicationContext(), "Contenido Leido Con Exito",Toast.LENGTH_SHORT).show();
                double edad=Double.parseDouble(datos[1]);
                if(edad<12){
                    Uri myUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.naruto);
                    VideoView videoView = (VideoView) findViewById(R.id.video);
                    videoView.setVideoURI(myUri);
                    videoView.setMediaController(new MediaController( this));
                    videoView.requestFocus();


                    reproducir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            videoView.start();
                        }

                    });
                    stop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),menu.class);
                            startActivity(intent);
                        }
                    });
                    pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            videoView.pause();
                        }
                    });




                }if(edad>12 && edad<18){
                    Uri myUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.rapido);
                    VideoView videoView = (VideoView) findViewById(R.id.video);
                    videoView.setVideoURI(myUri);
                    videoView.setMediaController(new MediaController( this));
                    videoView.requestFocus();


                    reproducir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            videoView.start();
                        }

                    });
                    stop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),menu.class);
                            startActivity(intent);
                        }
                    });
                    pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            videoView.pause();
                        }
                    });
                }if(edad>18){
                    Uri myUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.monja);
                    VideoView videoView = (VideoView) findViewById(R.id.video);
                    videoView.setVideoURI(myUri);
                    videoView.setMediaController(new MediaController( this));
                    videoView.requestFocus();


                    reproducir.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            videoView.start();
                        }

                    });
                    stop.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(),menu.class);
                            startActivity(intent);
                        }
                    });
                    pause.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            videoView.pause();
                        }
                    });
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