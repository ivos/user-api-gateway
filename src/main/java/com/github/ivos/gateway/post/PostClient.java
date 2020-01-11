package com.github.ivos.gateway.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class PostClient {

	private final WebClient webClient;

	@Autowired
	public PostClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Flux<PostDto> listUserPosts(Long userId) {
		return webClient.get()
				.uri("/posts?userId={userId}", userId)
				.retrieve()
				.bodyToFlux(PostDto.class);
	}
}
