package com.github.ivos.gateway.user;

import com.github.ivos.gateway.post.PostClient;
import com.github.ivos.gateway.post.PostDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class UserService {

	private final UserClient userClient;
	private final PostClient postClient;

	@Autowired
	public UserService(UserClient userClient, PostClient postClient) {
		this.userClient = userClient;
		this.postClient = postClient;
	}

	public Mono<UserPostsDto> getUserWithPosts(Long id) {
		Mono<UserDto> userMono = userClient.getUser(id);
		Flux<PostDto> postsFlux = postClient.listUserPosts(id);

		return userMono.zipWith(postsFlux.collectList(), this::mergeUserPosts);
	}

	private UserPostsDto mergeUserPosts(UserDto user, List<PostDto> posts) {
		log.debug("Merging post to user {}", user);
		return new UserPostsDto(user.getName(), user.getUsername(), user.getEmail(), posts);
	}
}
