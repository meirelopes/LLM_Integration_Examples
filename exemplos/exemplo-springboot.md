# ChatLanguageModels

É a API de baixo nível no LangChain4j, oferecendo mais poder e flexibilidade. O ChatLanguageModel é uma evolução dos tradicionais Modelos de Linguagem usados em inteligência artificial. Enquanto os Modelos de Linguagem simples aceitam uma String como entrada e retornam uma String como saída, os ChatLanguageModels são projetados para lidar com interações mais complexas e dinâmicas. Eles aceitam uma ou várias mensagens de chat (ChatMessages) como entrada e retornam uma resposta (AiMessage).

### Características Principais

- Interatividade: Permite conversas contínuas e multi-turnos, onde o contexto da conversa é mantido.
- Flexibilidade: Pode lidar com diferentes tipos de mensagens, incluindo texto e imagens.
- Gerenciamento de Estado: Utiliza conceitos como ChatMemory para armazenar e gerenciar o estado da conversa, facilitando interações mais naturais e coerentes.

### Tipos de Mensagens

Existem quatro tipos principais de mensagens que podem ser usadas com o ChatLanguageModel:

- UserMessage: Mensagem enviada pelo usuário, que pode ser texto ou uma combinação de texto e imagens.
- AiMessage: Mensagem gerada pela IA em resposta a um UserMessage.
- ToolExecutionResultMessage: Resultado da execução de uma ferramenta solicitada pela IA.
- SystemMessage: Mensagem do sistema usada para definir o contexto ou instruções para a IA.

### Vantagens do ChatLanguageModel

- Manutenção de Contexto: Permite que a IA mantenha o contexto de conversas anteriores, proporcionando respostas mais precisas e relevantes.
- Interações Naturais: Facilita interações mais naturais e humanas com os usuários.
- Integração com Ferramentas: Pode solicitar e processar resultados de ferramentas externas, expandindo suas capacidades.

## Integração Spring Boot

Dependências:

```java
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
    <version>0.32.0</version>
</dependency>
```

```java
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-spring-boot-starter</artifactId>
    <version>0.32.0</version>
</dependency>
```
