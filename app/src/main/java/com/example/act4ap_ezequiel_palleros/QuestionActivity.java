package com.example.act4ap_ezequiel_palleros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QuestionActivity extends AppCompatActivity {

    private int currentQuestionIndex = 0;
    private String correctAnswer;
    private String categoria;
    private int puntuacion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        categoria = getIntent().getStringExtra("categoria");
        currentQuestionIndex = getIntent().getIntExtra("currentQuestionIndex", 0);
        puntuacion = getIntent().getIntExtra("puntuacion", 0); // Obtener el puntaje acumulado

        TextView questionText = findViewById(R.id.questionText);
        TextView emojiBox = findViewById(R.id.emojiBox);
        RadioGroup optionGroup = findViewById(R.id.optionGroup);

        // Llamada al método para cargar la siguiente pregunta
        loadQuestion(questionText, emojiBox, optionGroup);

        //  Verificar si se seleccionó una opción para hacer un intent
        Button confirmButton = findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Verificar cuál opción está seleccionada
                int selectedId = optionGroup.getCheckedRadioButtonId();
                if (selectedId == -1) {
                    Toast.makeText(QuestionActivity.this, "Por favor, selecciona una opción.", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton selectedRadioButton = findViewById(selectedId);
                    String selectedAnswer = selectedRadioButton.getText().toString();

                    // Verificar si la respuesta es correcta
                    if (selectedAnswer.equals(correctAnswer)) {
                        puntuacion++; // Incrementar el puntaje por respuesta correcta
                    }

                    // Crear intent para abrir AnswerActivity
                    Intent intent = new Intent(QuestionActivity.this, AnswerActivity.class);
                    intent.putExtra("selectedAnswer", selectedAnswer);
                    intent.putExtra("correctAnswer", correctAnswer);
                    intent.putExtra("isCorrect", selectedAnswer.equals(correctAnswer));
                    intent.putExtra("currentQuestionIndex", currentQuestionIndex);
                    intent.putExtra("categoria", categoria);
                    intent.putExtra("puntuacion", puntuacion);

                    startActivity(intent);
                    finish();  // Cerrar esta actividad
                }
            }
        });

        // Botón para volver a MenuActivity
        Button volverMenuButton = findViewById(R.id.volver_menu);
        volverMenuButton.setOnClickListener(v -> {
            Intent intent = new Intent(QuestionActivity.this, MenuActivity.class);
            startActivity(intent);
            finish();
        });
    }

    // Método para cargar la pregunta, emojis y opciones correspondiente dependiendo de la posición en el array
    private void loadQuestion(TextView questionText, TextView emojiBox, RadioGroup optionGroup) {
        if ("videojuegos".equals(categoria)) {
            questionText.setText(QandA.questionGames[currentQuestionIndex]);
            emojiBox.setText(QandA.emojisGames[currentQuestionIndex]);
            setOptions(optionGroup, QandA.optionsGames[currentQuestionIndex]);
            correctAnswer = QandA.correctGames[currentQuestionIndex];
        } else if ("peliculas".equals(categoria)) {
            questionText.setText(QandA.questionMovies[currentQuestionIndex]);
            emojiBox.setText(QandA.emojisMovies[currentQuestionIndex]);
            setOptions(optionGroup, QandA.optionsMovies[currentQuestionIndex]);
            correctAnswer = QandA.correctMovies[currentQuestionIndex];
        } else if ("musica".equals(categoria)) {
            questionText.setText(QandA.questionMusic[currentQuestionIndex]);
            emojiBox.setText(QandA.emojisMusic[currentQuestionIndex]);
            setOptions(optionGroup, QandA.optionsMusic[currentQuestionIndex]);
            correctAnswer = QandA.correctMusic[currentQuestionIndex];
        }
    }

    private void setOptions(RadioGroup optionGroup, String[] options) {
        optionGroup.removeAllViews();
        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextColor(getResources().getColor(android.R.color.white));
            optionGroup.addView(radioButton);
        }
    }
}



