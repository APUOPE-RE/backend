from sentence_transformers import SentenceTransformer
from flask import Flask, request, jsonify

app = Flask(__name__)
model = SentenceTransformer('all-MiniLM-L6-v2')  # Use the same model as in your embeddings

@app.route('/embed', methods=['POST'])
def embed_text():
    data = request.get_json()
    text = data.get("text", "")
    embedding = model.encode([text]).tolist()
    return jsonify({"embedding": embedding[0]})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)
