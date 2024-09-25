package com.example.act4ap_ezequiel_palleros;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TotalActivity extends AppCompatActivity {

    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total);

        score = getIntent().getIntExtra("score", 0);

        TextView scoreText = findViewById(R.id.scoreText);
        Button volverMenuButton = findViewById(R.id.volver_menu);

        // Texto para mostrar tu puntaje
        scoreText.setText("Tu puntaje: " + score);

        // Intent para volver a MenuActivity
        volverMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(TotalActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });

    }
}

