import fitz
import psycopg2
import numpy as np
from sentence_transformers import SentenceTransformer


def extract_text_from_pdf(pdf_path):
    text = ""
    with fitz.open(pdf_path) as doc:
        for page_num in range(doc.page_count):
            page = doc[page_num]
            text += page.get_text("text")
            text += "\n\n"
    return text


def chunk_text(text, max_words=200):
    paragraphs = text.split("\n\n")
    chunks = []
    chunk = []
    word_count = 0

    for paragraph in paragraphs:
        paragraph_word_count = len(paragraph.split())
        if word_count + paragraph_word_count > max_words:
            chunks.append(" ".join(chunk))
            chunk = []
            word_count = 0
        chunk.append(paragraph)
        word_count += paragraph_word_count

    if chunk:
        chunks.append(" ".join(chunk))

    return chunks


def main(pdf_path):
    textbook_text = extract_text_from_pdf(pdf_path)

    text_chunks = chunk_text(textbook_text)

    model = SentenceTransformer('all-MiniLM-L6-v2')
    embeddings = model.encode(text_chunks)

    conn = psycopg2.connect(
        dbname="apuope_db",
        user="apuope_user",
        password="apuope_password",
        host="localhost",
        port=5434
    )
    cursor = conn.cursor()

    for chunk, embedding in zip(text_chunks, embeddings):
        embedding_array = np.array(embedding).tolist()
        insert_query = "INSERT INTO apuope.textbook_embeddings (chunk, embedding) VALUES (%s, %s)"
        cursor.execute(insert_query, (chunk, embedding_array))

    conn.commit()
    cursor.close()
    conn.close()
    print("Embeddings stored successfully.")


if __name__ == "__main__":
    main("[Software.Requirements(3rd,2013.8)].Karl.E.Wiegers.æå-ç.pdf")
