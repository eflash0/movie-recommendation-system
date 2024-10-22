from flask import Flask, request, jsonify
from model.recommendation_model import train_model,get_recommendation_for_user
import pandas as pd

app = Flask(__name__)

similarity_df = None
user_item_matrix = None

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
    app.run(port=5000)