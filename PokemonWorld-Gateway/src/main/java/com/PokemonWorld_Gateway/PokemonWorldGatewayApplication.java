package com.PokemonWorld_Gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;



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
				.route("pokemons", route -> route
						.path(
								"/api/pokemons",
								"/api/pokemons/{uuid}"
						)
						.uri(pokemonUrl)
				)
				.route("trainers", route -> route
						.path(
								"/api/trainers",
								"/api/trainers/{uuid}"
						)
						.uri(trainerUrl)
				)
				.build();
	}


}
