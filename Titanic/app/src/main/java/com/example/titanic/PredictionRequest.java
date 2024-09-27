package com.example.titanic;

public class PredictionRequest {
    private int Pclass;
    private int Sex;
    private double Age;
    private int SibSp;
    private int Parch;
    private double Fare;
    private int Embarked;

    // Constructor
    public PredictionRequest(int pclass, int sex, double age, int sibSp, int parch, double fare, int embarked) {
        Pclass = pclass;
        Sex = sex;
        Age = age;
        SibSp = sibSp;
        Parch = parch;
        Fare = fare;
        Embarked = embarked;
    }

    // Getters (optional, if needed for serialization)
    public int getPclass() { return Pclass; }
    public int getSex() { return Sex; }
    public double getAge() { return Age; }
    public int getSibSp() { return SibSp; }
    public int getParch() { return Parch; }
    public double getFare() { return Fare; }
    public int getEmbarked() { return Embarked; }
}