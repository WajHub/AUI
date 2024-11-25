package com.PokemonWorld_Gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;


@SpringBootApplication
public class PokemonWorldGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonWorldGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${world.pokemon.url}") String pokemonUrl,
			@Value("${world.trainer.url}") String trainerUrl
	) {
		return builder
				.routes()
				.route("trainers", route -> route
						.path(
								"/api/trainers",
								"/api/trainers/{trainerId}"
						)
						.uri(trainerUrl)
				)
				.route("pokemons", route -> route
						.path(
								"/api/pokemons",
								"/api/pokemons/{uuid}",
								"/api/trainers/{uuid}/pokemons"
						)
						.uri(pokemonUrl)
				)
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}



}
