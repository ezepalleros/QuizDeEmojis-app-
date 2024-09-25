package com.example.act4ap_ezequiel_palleros;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswerActivity extends AppCompatActivity {

    private int currentQuestionIndex;
    private String categoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        TextView resultadoText = findViewById(R.id.correcto);
        ImageView resultImage = findViewById(R.id.resultImage);
        Button nextButton = findViewById(R.id.nextButton);

        // Obtener la respuesta seleccionada y correcta
        Intent intent = getIntent();
        String selectedAnswer = intent.getStringExtra("selectedAnswer");
        String correctAnswer = intent.getStringExtra("correctAnswer");
        boolean isCorrect = intent.getBooleanExtra("isCorrect", false);
        currentQuestionIndex = intent.getIntExtra("currentQuestionIndex", 0);
        categoria = intent.getStringExtra("categoria");
        int puntuacion = intent.getIntExtra("puntuacion", 0);

        // Muestra el resultado correcto y si se acertó o no
        if (isCorrect) {
            resultadoText.setText("¡Correcto! Era " + correctAnswer);
            resultImage.setImageResource(getCorrectImageResource(categoria, currentQuestionIndex));
        } else {
            resultadoText.setText("¡Incorrecto! Era " + correctAnswer);
            resultImage.setImageResource(getCorrectImageResource(categoria, currentQuestionIndex));
        }

        // Listener para el botón siguiente
        nextButton.setOnClickListener(v -> {
            currentQuestionIndex++;

            // Verificar si quedan más preguntas en la categoría actual
            if (currentQuestionIndex < getQuestionArrayLength()) {
                // Intent para volver a QuestionActivity y cargar la siguiente pregunta
                Intent nextQuestionIntent = new Intent(AnswerActivity.this, QuestionActivity.class);
                nextQuestionIntent.putExtra("currentQuestionIndex", currentQuestionIndex);
                nextQuestionIntent.putExtra("categoria", categoria);
                nextQuestionIntent.putExtra("puntuacion", puntuacion);
                startActivity(nextQuestionIntent);
                finish();
            } else {
                // Si no hay más preguntas, ir a TotalActivity
                Intent intentToTotal = new Intent(AnswerActivity.this, TotalActivity.class);
                intentToTotal.putExtra("score", puntuacion);
                startActivity(intentToTotal);
                finish();
            }
        });
    }

    // Método para mostrar la imagen
    private int getCorrectImageResource(String categoria, int questionIndex) {
        if ("videojuegos".equalsIgnoreCase(categoria)) {
            if (questionIndex == 0) {
                return R.drawable.correcto_game0;
            } else if (questionIndex == 1) {
                return R.drawable.correcto_game1;
            } else if (questionIndex == 2) {
                return R.drawable.correcto_game2;
            }
        } else if ("peliculas".equalsIgnoreCase(categoria)) {
            if (questionIndex == 0) {
                return R.drawable.correcto_movie0;
            } else if (questionIndex == 1) {
                return R.drawable.correcto_movie1;
            } else if (questionIndex == 2) {
                return R.drawable.correcto_movie2;
            }
        } else if ("musica".equalsIgnoreCase(categoria)) {
            if (questionIndex == 0) {
                return R.drawable.correcto_music0;
            } else if (questionIndex == 1) {
                return R.drawable.correcto_music1;
            } else if (questionIndex == 2) {
                return R.drawable.correcto_music2;
            }
        }

        // Mostrar esta imagen si no se encuentra ninguna
        return R.drawable.correcto_music0;
    }

    // Dependiendo de la categoría y la longitud se obtiene la siguiente pregunta
    private int getQuestionArrayLength() {
        switch (categoria) {
            case "videojuegos":
                return QandA.questionGames.length;
            case "peliculas":
                return QandA.questionMovies.length;
            case "musica":
                return QandA.questionMusic.length;
            default:
                return 0;
        }
    }
}

