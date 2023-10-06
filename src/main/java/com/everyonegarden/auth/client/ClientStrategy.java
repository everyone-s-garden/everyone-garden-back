package com.everyonegarden.auth.client;

import com.everyonegarden.member.enunerate.MemberProvider;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Component
public class ClientStrategy {

    private final WebClient webClient;
    private Map<String, ClientProxy> clients = new HashMap<>();

    public ClientStrategy(WebClient webClient) {
        this.webClient = webClient;
        clients.put(MemberProvider.GOOGLE.name(), new ClientGoogle(webClient));
        clients.put(MemberProvider.KAKAO.name(), new ClientKakao(webClient));
    }

    public ClientProxy getClient(String clientName) {
        return clients.get(clientName);
    }

}
