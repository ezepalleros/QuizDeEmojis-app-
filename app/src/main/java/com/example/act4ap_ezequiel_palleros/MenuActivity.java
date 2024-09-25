package com.example.act4ap_ezequiel_palleros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        String nombreUsuario = getIntent().getStringExtra("nombreUsuario");

        // Mostrar nombre de usuario
        TextView textView = findViewById(R.id.textView);
        textView.setText("Bienvenido, " + nombreUsuario);

        // Botón para volver a main
        Button buttonVolver = findViewById(R.id.buttonVolver);
        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volverIntent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(volverIntent);
            }
        });

        // Botón para ir a QuestionActivity con la categoría de videojuegos
        Button buttonVideojuegos = findViewById(R.id.buttonVideojuegos);
        buttonVideojuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
                intent.putExtra("categoria", "videojuegos");
                startActivity(intent);
                finish();
            }
        });

        // Botón para ir a QuestionActivity con la categoría de películas
        Button buttonPeliculas = findViewById(R.id.buttonPeliculas);
        buttonPeliculas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
                intent.putExtra("categoria", "peliculas");
                startActivity(intent);
                finish();
            }
        });

        // Botón para ir a QuestionActivity con la categoría de música
        Button buttonMusica = findViewById(R.id.buttonMusica);
        buttonMusica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, QuestionActivity.class);
                intent.putExtra("categoria", "musica");
                startActivity(intent);
                finish();
            }
        });
    }
}

