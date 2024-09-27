from flask import Flask, request, jsonify
import pickle
import numpy as np

# Load the pre-trained model
with open('titanic_model.pkl', 'rb') as file:
    model = pickle.load(file)

app = Flask(__name__)

@app.route('/')
def home():
    return "Welcome to the Titanic Survival Predictor!"

@app.route('/predict', methods=['POST'])
def predict():
    data = request.json
    try:
        # Extracting features from the input
        features = np.array([
            data['Pclass'],
            data['Sex'],  # 0 for male, 1 for female
            data['Age'],
            data['SibSp'],
            data['Parch'],
            data['Fare'],
            data['Embarked']  # 0 for Cherbourg, 1 for Queenstown, 2 for Southampton
        ]).reshape(1, -1)
        
        # Make prediction
        prediction = model.predict(features)
        return jsonify({'Survived': int(prediction[0])})  # 0 or 1
    except Exception as e:
        return jsonify({'error': str(e)}), 400

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000,debug=True) 
