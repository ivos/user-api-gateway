package com.github.ivos.gateway.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public Mono<UserPostsDto> getUserWithPosts(@PathVariable Long id) {
		log.info("Get user with posts, id: {}", id);
		return userService.getUserWithPosts(id);
	}

	@ExceptionHandler
	public ResponseEntity<Void> handle(WebClientResponseException.NotFound exception) {
		return ResponseEntity.notFound()
				.build();
	}
}
