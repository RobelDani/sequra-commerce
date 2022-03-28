package com.sequra.commerce;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sequra.commerce.entity.Merchants;
import com.sequra.commerce.entity.Orders;
import com.sequra.commerce.entity.Shoppers;
import com.sequra.commerce.service.MerchantsService;
import com.sequra.commerce.service.OrdersService;
import com.sequra.commerce.service.ShoppersService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner runMerchants(final MerchantsService merchantsService) {
		return args -> {
			final ObjectMapper mapper = new ObjectMapper();
			final TypeReference<List<Merchants>> typeReference = new TypeReference<List<Merchants>>() {
			};
			final InputStream inputStream = TypeReference.class.getResourceAsStream("/json/merchants.json");
			try {
				final List<Merchants> merchants = mapper.readValue(inputStream, typeReference);
				merchantsService.save(merchants);
				System.out.println("Merchants Saved!");
			} catch (final IOException e) {
				System.out.println("Unable to save merchants: " + e.getMessage());
			}
		};
	}

	@Bean
	CommandLineRunner runShoppers(final ShoppersService shoppersService) {
		return args -> {
			final ObjectMapper mapper = new ObjectMapper();
			final TypeReference<List<Shoppers>> typeReference = new TypeReference<List<Shoppers>>() {
			};
			final InputStream inputStream = TypeReference.class.getResourceAsStream("/json/shoppers.json");
			try {
				final List<Shoppers> shoppers = mapper.readValue(inputStream, typeReference);
				shoppersService.save(shoppers);
				System.out.println("Shoppers Saved!");
			} catch (final IOException e) {
				System.out.println("Unable to save shoppers: " + e.getMessage());
			}
		};
	}

	@Bean
	CommandLineRunner runOrders(final OrdersService ordersService) {
		return args -> {
			final ObjectMapper mapper = new ObjectMapper();
			final TypeReference<List<Orders>> typeReference = new TypeReference<List<Orders>>() {
			};
			final InputStream inputStream = TypeReference.class.getResourceAsStream("/json/orders.json");
			try {
				final List<Orders> orders = mapper.readValue(inputStream, typeReference);
				ordersService.save(orders);
				System.out.println("Orders Saved!");
			} catch (final IOException e) {
				System.out.println("Unable to save orders: " + e.getMessage());
			}
		};
	}
}
