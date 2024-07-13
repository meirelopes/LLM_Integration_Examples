# Large Language Model (LLM's)

Um Large Language Model (LLM) é um tipo de modelo de aprendizado de máquina treinado para processar e gerar linguagem natural em larga escala. Esses modelos são baseados em arquiteturas de redes neurais profundas, como transformadores, e são capazes de entender, resumir, traduzir, responder a perguntas e até mesmo gerar texto em linguagem natural de forma coerente.

## Características dos LLMs

- Grande Quantidade de Parâmetros: LLMs têm milhões ou até bilhões de parâmetros, o que lhes permite capturar nuances complexas da linguagem.
  Treinamento em Grandes Conjuntos de Dados:

- Eles são treinados em grandes volumes de texto, como artigos da web, livros, e outras fontes de texto, permitindo-lhes aprender padrões linguísticos detalhados.

- Capacidades Avançadas: LLMs podem realizar tarefas complexas de processamento de linguagem natural, como tradução automática, geração de texto, resumo, resposta a perguntas, e muito mais.

- Arquitetura Transformer: A maioria dos LLMs modernos são baseados na arquitetura de transformadores, que permite um processamento paralelo eficiente e melhor captura de dependências de longo alcance no texto.

## Exemplos de LLMs

- GPT-3 (Generative Pre-trained Transformer 3): Desenvolvido pela OpenAI, é um dos modelos de linguagem mais conhecidos, com 175 bilhões de parâmetros.
- BERT (Bidirectional Encoder Representations from Transformers): Desenvolvido pelo Google, é usado para várias tarefas de linguagem natural e é conhecido por sua capacidade de entender o contexto bidirecional.
- T5 (Text-To-Text Transfer Transformer): Também desenvolvido pelo Google, converte todas as tarefas de linguagem em um formato de entrada-saída de texto para texto.

## Aplicações dos LLMs

- Chatbots e Assistentes Virtuais: LLMs podem ser usados para criar chatbots avançados que compreendem e respondem a consultas de usuários de forma natural.

- Tradução Automática: Eles podem traduzir texto entre diferentes idiomas com alta precisão.

- Geração de Conteúdo: LLMs podem gerar artigos, histórias, código de programação e outros tipos de conteúdo textual.

- Análise de Sentimento: Podem ser usados para analisar sentimentos em textos, como opiniões de clientes e comentários em redes sociais.

- Respostas a Perguntas: LLMs podem responder a perguntas com base em grandes bases de conhecimento, como documentos, artigos e bancos de dados.

## LangChain4j

O objetivo do LangChain4j é simplificar a integração de LLMs em aplicativos Java. O LangChain4j opera em dois níveis de abstração:

- Nível baixo: Neste nível, você tem mais liberdade e acesso a todos os componentes de baixo nível, como ChatLanguageModel, UserMessage, AiMessage, EmbeddingStore, Embedding, etc. Esses são os "primitivos" do seu aplicativo com tecnologia LLM. Você tem controle total sobre como combiná-los, mas precisará escrever mais código de colagem.
- Alto nível . Neste nível, você interage com LLMs usando APIs de alto nível como AiServices, que escondem toda a complexidade e boilerplate de você. Você ainda tem a flexibilidade para ajustar e refinar o comportamento, mas isso é feito de forma declarativa.

## Api's de LLM's do LangChain4j

Os LLMs estão atualmente disponíveis em dois tipos de API:

- LanguageModels: A API deles é muito simples - eles aceitam a String como entrada e retornam a String como saída. Essa API está se tornando obsoleta em favor da API de chat (segundo tipo de API).
- ChatLanguageModels: Eles aceitam um ou vários ChatMessages como entrada e retornam um AiMessage como saída. ChatMessage geralmente contém texto, mas alguns LLMs também suportam uma mistura de texto e Images. Exemplos desses modelos de bate-papo incluem o OpenAI gpt-3.5-turbo e o Google gemini-pro.
