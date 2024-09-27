package com.example.titanic;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.example.titanic.PredictionResponse;
import com.example.titanic.PredictionRequest;

public interface ApiService {
    @POST("/predict")
    Call<PredictionResponse> predictSurvival(@Body PredictionRequest request);
}