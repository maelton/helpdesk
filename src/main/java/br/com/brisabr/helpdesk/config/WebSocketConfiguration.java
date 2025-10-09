package br.com.brisabr.helpdesk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {
    private final AuthChannelInterceptor authChannelInterceptor;
    public WebSocketConfiguration(AuthChannelInterceptor authChannelInterceptor) {
        this.authChannelInterceptor = authChannelInterceptor;
    }

    /**
     * Registra os endpoints STOMP, que são os pontos de entrada para a conexão WebSocket.
     * Os clientes usarão esses endpoints para iniciar o "handshake" e estabelecer uma conexão persistente.
     * @param registry O registro de endpoints STOMP.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Define o endpoint "/ws" que os clientes usarão para se conectar.
        // .withSockJS() oferece uma opção de fallback para navegadores que não suportam WebSockets nativamente.
        registry.addEndpoint("/ws").withSockJS();
    }

    /**
     * Configura o broker de mensagens, que é responsável por rotear as mensagens
     * entre clientes e o servidor.
     * @param registry O registro do message broker.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Define "/app" como o prefixo para destinos de mensagens que são roteadas para
        // métodos de processamento da aplicação (anotados com @MessageMapping).
        // Este é o caminho de IDA (clientes -> servidor).
        registry.setApplicationDestinationPrefixes("/app");
        
        // Habilita um broker de mensagens em memória para retransmitir mensagens de volta aos clientes
        // em destinos que começam com "/topic". Este é o caminho de VOLTA (servidor -> clientes).
        // Clientes podem criar canais/tópicos se inscrevendo neles, especificando um caminho que começe 
        // com /topic. Por exemplo, /topic/ticket/{ticketId}.
        registry.enableSimpleBroker("/topic");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(authChannelInterceptor);
    }
}