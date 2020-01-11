package com.github.ivos.gateway.user;

import com.github.ivos.gateway.post.PostDto;
import lombok.Value;

import java.util.List;

@Value
public class UserPostsDto {

	private String name;
	private String username;
	private String email;
	private List<PostDto> posts;
}
