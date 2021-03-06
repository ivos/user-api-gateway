package com.github.ivos.gateway.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {

	@Value("${client.connection-timeout}")
	int clientConnectionTimeout;

	@Value("${client.read-timeout}")
	int clientReadTimeout;

	@Value("${client.write-timeout}")
	int clientWriteTimeout;

	@Bean
	public WebClient webClient() {
		HttpClient httpClient = HttpClient.create()
				.tcpConfiguration(client -> client
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, clientConnectionTimeout)
						.doOnConnected(connection -> connection
								.addHandlerLast(new ReadTimeoutHandler(clientReadTimeout, TimeUnit.MILLISECONDS))
								.addHandlerLast(new WriteTimeoutHandler(clientWriteTimeout, TimeUnit.MILLISECONDS))));

		return WebClient.builder()
				.baseUrl("https://jsonplaceholder.typicode.com/")
				.clientConnector(new ReactorClientHttpConnector(httpClient))
				.build();
	}
}
