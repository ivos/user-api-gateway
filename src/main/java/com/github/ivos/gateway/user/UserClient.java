package com.github.ivos.gateway.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserClient {

	private final WebClient webClient;

	@Autowired
	public UserClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<UserDto> getUser(Long id) {
		return webClient.get()
				.uri("/users/{id}", id)
				.retrieve()
				.bodyToMono(UserDto.class);
	}
}
