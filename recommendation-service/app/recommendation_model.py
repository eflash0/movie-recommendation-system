import pandas as pd
from sklearn.decomposition import TruncatedSVD
from sklearn.metrics.pairwise import cosine_similarity

def train_model(interactions):
    df = pd.DataFrame(interactions)
    df['user_id'] = df['user'].apply(lambda x: x['userId'])
    df['weighted_rating'] = df['rating']
    df.loc[df['favorite'], 'weighted_rating'] += 1
    df.loc[df['watchList'], 'weighted_rating'] += 0.5
    user_item_matrix = df.pivot_table(
        index='user_id',
        columns='mediaId',
        values='weighted_rating'                           
    ).fillna(0)
    svd = TruncatedSVD(n_components=20)
    user_item_matrix_svd = svd.fit_transform(user_item_matrix)
    similariy_matrix = cosine_similarity(user_item_matrix_svd)
    similarity_df = pd.DataFrame(similariy_matrix,index=user_item_matrix.index,columns=user_item_matrix.index)
    return similarity_df, user_item_matrix

def get_recommendation_for_user(similarity_df, user_id, user_item_matrix, top_n=10):
    similar_users = similarity_df[user_id].sort_values(ascending=False).index[1:top_n+1]
    similar_users_ratings = user_item_matrix.loc[similar_users].mean()
    user_rated_items = user_item_matrix.loc[user_id]
    recommendations = similar_users_ratings[user_rated_items == 0].sort_values(ascending=False)
    
    return recommendations.index[:top_n].tolist()