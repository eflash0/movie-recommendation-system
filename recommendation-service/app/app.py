from flask import Flask, request, jsonify
from model.recommendation_model import train_model,get_recommendation_for_user
import pandas as pd
import pickle
import os

app = Flask(__name__)

CURRENT_DIR = os.path.dirname(os.path.abspath(__file__))

# Paths for saving the model
MODEL_DIR = os.path.join(CURRENT_DIR, "pre-trained_model")
SIMILARITY_MODEL_PATH = os.path.join(MODEL_DIR, "similarity_df.pkl")
USER_ITEM_MATRIX_PATH = os.path.join(MODEL_DIR, "user_item_matrix.pkl")

similarity_df = None
user_item_matrix = None

def save_model(similarity_df,user_item_matrix) : 
    with open(SIMILARITY_MODEL_PATH, 'wb') as sim_file:
        pickle.dump(similarity_df, sim_file)
    with open(USER_ITEM_MATRIX_PATH, 'wb') as uim_file:
        pickle.dump(user_item_matrix, uim_file)
    print("model saved to files")

def load_model():
    global similarity_df,user_item_matrix

    if os.path.exists(SIMILARITY_MODEL_PATH) and os.path.exists(USER_ITEM_MATRIX_PATH):
        with open(SIMILARITY_MODEL_PATH, 'rb') as sim_file:
            similarity_df = pickle.load(sim_file)
        with open(USER_ITEM_MATRIX_PATH, 'rb') as uim_file:
            user_item_matrix = pickle.load(uim_file)    
        print("model loaded from files")
    else:
        print("no pre-trained model found")                 

@app.route('/train', methods=['POST'])
def train():
    interactions = request.json
    interactions = request.json
    if interactions:
        global similarity_df, user_item_matrix
        similarity_df, user_item_matrix = train_model(interactions)
        pd.set_option('display.max_rows', None)
        pd.set_option('display.max_columns', None)
        print(similarity_df)
        print(user_item_matrix)
        save_model(similarity_df,user_item_matrix)
        return jsonify({"message": "Model trained successfully"}), 200
    else:
        return jsonify({"error": "No interactions provided"}), 400

@app.route('/recommend', methods=['POST'])
def recommend():
    global similarity_df, user_item_matrix
    print("regjiorg",similarity_df)

    if similarity_df is None or user_item_matrix is None:
        return jsonify({"error": "Model not trained. Please call /train endpoint first."}), 400

    # Step 3: Get the user ID from query parameters or request data
    # user_id = int(request.args.get('userId'))  # Assuming userId is passed as a query parameter
    user_id = int(request.json['userId'])

    print("regkpoerkpgoergkperkgrg",user_id)
    # Step 4: Generate recommendations by passing the similarity matrix and the user-item matrix
    recommendations = get_recommendation_for_user(similarity_df, user_id, user_item_matrix)
    print(recommendations)
    # Step 5: Return the recommendations as a JSON response
    return jsonify(recommendations)

if __name__ == '__main__':
    load_model()
    app.run(port=5000)