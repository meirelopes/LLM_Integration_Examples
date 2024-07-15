# Chat Memory

No LangChain4j, a gestão de mensagens de chat pode ser complexa e trabalhosa. Para facilitar esse processo, a biblioteca oferece uma abstração chamada ChatMemory, que vem com várias implementações prontas para uso. Essa abstração pode ser utilizada como um componente de baixo nível independente ou como parte de um componente de alto nível, como os serviços de IA (AI Services).

## Funções do ChatMemory

ChatMemory atua como um contêiner para mensagens de chat (ChatMessages), e é suportado por uma lista (List). Ele oferece funcionalidades adicionais importantes, como:

1. Política de Despejo (Eviction Policy): Gerencia como e quando as mensagens são removidas para liberar espaço.
2. Persistência: Permite armazenar mensagens de maneira persistente, evitando perda de dados.
3. Tratamento Especial de SystemMessage: Gerencia mensagens do sistema de forma diferenciada.
4. Tratamento Especial de Tool Messages: Lida com mensagens relacionadas a ferramentas de maneira específica.

## Memória vs. Histórico

- Histórico: Mantém todas as mensagens entre o usuário e a IA intactas, representando o que foi realmente dito na interface do usuário.
- Memória: Armazena informações selecionadas que são apresentadas ao modelo de linguagem (LLM) para simular a "lembrança" da conversa. A memória pode modificar o histórico de várias maneiras, como resumindo ou removendo mensagens desnecessárias.

## Políticas de Despejo

As políticas de despejo são necessárias por vários motivos:

- Janela de Contexto do LLM: Há um limite no número de tokens que o LLM pode processar de uma vez. Quando esse limite é excedido, algumas mensagens devem ser removidas.
- Controle de Custo: Cada token tem um custo, e remover mensagens desnecessárias ajuda a reduzir o custo.
- Controle de Latência: Quanto mais tokens são enviados, mais tempo o LLM leva para processá-los.

## Implementações de Políticas de Despejo

### MessageWindowChatMemory:

- Funciona como uma janela deslizante, mantendo as N mensagens mais recentes.
- Útil para prototipagem rápida, mas pode não ser eficiente em termos de número variável de tokens.

### TokenWindowChatMemory:

- Também opera como uma janela deslizante, mas foca em manter os N tokens mais recentes.
- Mensagens são indivisíveis; se uma mensagem não couber, ela é removida completamente.
- Requer um Tokenizer para contar os tokens em cada mensagem.

## Persistência

Por padrão, as implementações de ChatMemory armazenam as mensagens na memória. No entanto, se for necessária persistência, você pode implementar um ChatMemoryStore personalizado para armazenar mensagens em qualquer armazenamento persistente.

- updateMessages(): Chamado sempre que uma nova ChatMessage é adicionada ao ChatMemory. Isso ocorre normalmente duas vezes durante cada interação com o LLM: uma vez quando uma nova UserMessage é adicionada e novamente quando uma nova AiMessage é adicionada.
- getMessages(): Chamado sempre que todas as mensagens são solicitadas. Deve retornar todas as mensagens associadas ao ID de memória fornecido.
- deleteMessages(): Chamado quando ChatMemory.clear() é invocado.

## Tratamento Especial de SystemMessage

SystemMessage é tratada de forma especial:

- Sempre mantida uma vez adicionada.
- Somente uma SystemMessage é mantida por vez.
- Novas SystemMessages com o mesmo conteúdo são ignoradas.
- Novas SystemMessages com conteúdo diferente substituem a anterior.

## Tratamento Especial de Tool Messages

ToolExecutionRequest e ToolExecutionResultMessage:

- ToolExecutionRequest: Esta é uma mensagem que representa uma solicitação para executar uma ferramenta específica. Por exemplo, pode ser um pedido para buscar informações, executar um cálculo, ou realizar uma operação que não pode ser diretamente feita pelo modelo de linguagem.

- ToolExecutionResultMessage: Esta mensagem contém o resultado da execução da ferramenta solicitada pelo ToolExecutionRequest. Por exemplo, se a ferramenta solicitada era para buscar informações em um banco de dados, a ToolExecutionResultMessage conterá os dados recuperados.
  Se uma AiMessage contendo ToolExecutionRequest for removida, as mensagens órfãs ToolExecutionResultMessage também serão removidas para evitar problemas com alguns provedores de LLM. Isso é importante por alguns motivos:

1. Coerência do Contexto: Manter o contexto coerente para o modelo de linguagem. Se a solicitação de execução de ferramenta é removida, o resultado dessa execução não faz sentido sozinho e pode causar confusão ou respostas incorretas do modelo.

2. Restrições de Provedores de LLM: Alguns provedores de modelos de linguagem (como OpenAI) podem ter restrições que proíbem o envio de resultados de execução de ferramentas sem a solicitação correspondente. Enviar uma mensagem de resultado sem a solicitação original pode levar a comportamentos inesperados ou erros.

## Uso com Serviços de IA e Canais de Conversação

ChatMemory pode ser integrado em várias estruturas, como:

- Memória de bate-papo separada para cada usuário.
- Memória de bate-papo persistente para cada usuário.
- Memória de bate-papo com ConversationalChain ou ConversationalRetrievalChain.

Exemplo 1:

Chat memory para um único usuário

```java
<dependency>
    <groupId>dev.langchain4j</groupId>
    <artifactId>langchain4j-open-ai-spring-boot-starter</artifactId>
    <version>0.32.0</version>
</dependency>
```
