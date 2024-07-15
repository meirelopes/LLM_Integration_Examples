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

## Exemplos

`OpenAiChatModel` é uma classe fornecida pela biblioteca langchain4j que encapsula a configuração e o uso do modelo de linguagem OpenAI GPT. Essa classe facilita a integração com a API da OpenAI, permitindo que você configure parâmetros como a chave da API, o modelo específico a ser usado, a temperatura (que controla a aleatoriedade das respostas), entre outros.

### Exemplo 1

```java
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.spring.AiService;

@AiService
public class Assistant1 {
    String apiKey = System.getenv("OPENAI_API_KEY");
    OpenAiChatModel chatModel = OpenAiChatModel.withApiKey(apiKey);
}

```

```java
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AssistantController1 {
    private final Assistant1 assistant1;

    @PostMapping("/chat1")
    public String chat(@RequestBody String message) {

        return assistant1.chatModel.generate(message);
    }
}
```

`@SystemMessage` é utilizada para definir uma mensagem do sistema que será enviada ao modelo de linguagem no contexto de um serviço de assistente. Essa mensagem serve como uma instrução ou diretriz sobre como o assistente deve se comportar ou responder às solicitações dos usuários. Em outras palavras, é uma forma de definir o comportamento padrão do assistente.

`@AiService` é uma anotação para gerar automaticamente uma implementação da interface que faz uso do modelo de linguagem especificado. Isso simplifica a criação de serviços de assistente de IA, permitindo que você se concentre na lógica de negócios e na configuração do modelo, sem precisar lidar diretamente com as chamadas de API de baixo nível para o modelo de linguagem.

`AiServices`  é uma classe que permite que você crie serviços de assistente configurando-os com o modelo de linguagem desejado e outras propriedades específicas, como a temperatura de resposta, a chave da API, etc.

### Exemplo 2

```java
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant2 {
    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
```

```java
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig2 {
    @Bean
    public Assistant2 assistent2() {
        return AiServices.builder(Assistant2.class)
                .chatLanguageModel(chatLanguageModel2())
                .build();
    }
    @Bean
    public ChatLanguageModel chatLanguageModel2() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        return new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(0.7)
                .build();
    }
}
```

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AssistantController2 {

    @Autowired
    Assistant2 assistant2;

    @PostMapping("/chat2")
    public String chat(@RequestBody String message) {
        return assistant2.chat(message);
    }
}
```

`AiMessage` é uma classe que representa uma mensagem trocada com o modelo de linguagem. Essa classe encapsula a mensagem de texto e, possivelmente, outras informações, como solicitações de execução de ferramentas. A classe AiMessage é usada para representar as respostas geradas pelo modelo de linguagem em uma interação com o assistente.

`@UserMessage` é usada para marcar o parâmetro de um método na interface de um serviço de assistente de IA como a mensagem do usuário que será enviada ao modelo de linguagem. Essa anotação ajuda a biblioteca langchain4j a identificar qual parte da entrada deve ser tratada como a mensagem do usuário na interação com o modelo de IA.

### Exemplo 3

```java
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant3 {
    @SystemMessage(
            """
                  Você é um assistente de conversa. Responda de maneira amigável e
                  se não souber algo, apenas desculpe-se e diga que não pode ajudar pois não sabe do que se trata.
                  """
    )
    AiMessage chat(@UserMessage dev.langchain4j.data.message.UserMessage userMessage);

}
```

```java
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AssistantConfig3 {
    @Bean
    public Assistant3 assistent3() {
        return AiServices.builder(Assistant3.class)
                .chatLanguageModel(chatLanguageModel3())
                .build();
    }
    @Bean
    public ChatLanguageModel chatLanguageModel3() {
        String apiKey = System.getenv("OPENAI_API_KEY");
        return new OpenAiChatModel.OpenAiChatModelBuilder()
                .apiKey(apiKey)
                .modelName("gpt-3.5-turbo")
                .temperature(0.7)
                .build();
    }
}
```

```java
import dev.langchain4j.data.message.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import dev.langchain4j.data.message.AiMessage;

@RestController
public class AssistentController3 {

    @Autowired
    private Assistant3 assistant3;

    @PostMapping("/chat3")
    public ResponseEntity<ChatRequestDto> chat3(@RequestBody String message) {
            AiMessage response = assistant3.chat(UserMessage.from(message));
            return ResponseEntity.ok(new ChatRequestDto(response));
    }
}
```

```java
import dev.langchain4j.agent.tool.ToolExecutionRequest;
import dev.langchain4j.data.message.AiMessage;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class ChatRequestDto {
    private String text;
    private List<ToolExecutionRequest> toolExecutionRequests;

    public ChatRequestDto(AiMessage aiMessage) {
        this.text = aiMessage.text();
        this.toolExecutionRequests = aiMessage.toolExecutionRequests() != null ? aiMessage.toolExecutionRequests() : Collections.emptyList();
    }
}
```
