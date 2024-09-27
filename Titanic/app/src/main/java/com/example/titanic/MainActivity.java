package com.example.titanic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String BASE_URL = "http://192.168.1.4:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextAge = findViewById(R.id.editTextAge);
        RadioGroup radioGroupSex = findViewById(R.id.radioGroupSex);
        RadioGroup radioGroupEmbarked = findViewById(R.id.radioGroupEmbarked);
        Button buttonPredict = findViewById(R.id.buttonPredict);
        TextView textViewResult = findViewById(R.id.textViewResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        buttonPredict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double age = Double.parseDouble(editTextAge.getText().toString());
                int sex = radioGroupSex.getCheckedRadioButtonId() == R.id.radioMale ? 1 : 2;
                int embarked;
                if (radioGroupEmbarked.getCheckedRadioButtonId() == R.id.radioC) {
                    embarked = 0; // C
                } else if (radioGroupEmbarked.getCheckedRadioButtonId() == R.id.radioQ) {
                    embarked = 1; // Q
                } else {
                    embarked = 2; // S
                }

                // Create a PredictionRequest with other parameters as needed
                PredictionRequest request = new PredictionRequest(1, sex, age, 0, 0, 0.0, embarked); // Example values for Pclass, SibSp, Parch, and Fare
                Call<PredictionResponse> call = apiService.predictSurvival(request);
                call.enqueue(new Callback<PredictionResponse>() {
                    @Override
                    public void onResponse(Call<PredictionResponse> call, retrofit2.Response<PredictionResponse> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            textViewResult.setText("Survived: " + response.body().getSurvived());
                        } else {
                            textViewResult.setText("Prediction failed.");
                        }
                    }

                    @Override
                    public void onFailure(Call<PredictionResponse> call, Throwable t) {
                        Log.e("Error", t.getMessage());
                        textViewResult.setText("Error: " + t.getMessage());
                    }
                });
            }
        });
    }
}
