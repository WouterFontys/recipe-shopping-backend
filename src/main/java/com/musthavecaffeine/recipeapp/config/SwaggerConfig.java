package com.musthavecaffeine.recipeapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

	@Bean
	public Docket recipeApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				//.groupName("RecipeController")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.musthavecaffeine.recipeapp.controllers.v1"))
				.paths(regex("/(ingredient|recipe|shoppinglist|user).*"))
				.build()
				.apiInfo(metaData())
				.tags(
						new Tag("IngredientController", "Operations pertaining to ingredients"),
						new Tag("RecipeController", "Operations pertaining to recipes"),
						new Tag("ShoppingListController", "Operations pertaining to shoppinglists"),
						new Tag("UserController", "Operations pertaining to users"));
	}

//	@Bean
//	public Docket ingredientApi() {
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("IngredientController")
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.musthavecaffeine.recipeapp.controllers"))
//				.paths(regex("/ingredient.*"))
//				.build()
//				.apiInfo(metaData())
//				.tags(new Tag("IngredientController", "Operations pertaining to ingredients"));
//	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Recipe App REST API")
				.description("REST API for Recipe App")
				.version("1.0.0")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Must Have Caffeine", "https://github.com/WouterFontys/recipe-shopping-backend", "foo@bar.com"))
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");

		registry
			.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

}
