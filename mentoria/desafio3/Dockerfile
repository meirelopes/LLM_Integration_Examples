# Use a imagem base especificada
FROM chromadb/chroma:0.5.6.dev67

# Exponha a porta que o aplicativo irá usar
EXPOSE 8000

# Comando para iniciar o serviço quando o contêiner for executado
CMD ["uvicorn", "chromadb.app:app", "--host", "0.0.0.0", "--port", "8000"]
