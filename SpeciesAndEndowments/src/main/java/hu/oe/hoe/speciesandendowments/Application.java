package hu.oe.hoe.speciesandendowments;



import hu.oe.hoe.base.OpenApiApplication;
import hu.oe.hoe.model.Ability;
import hu.oe.hoe.model.Species;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.hateoas.RelProvider;

@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableCaching
@SpringBootApplication
@EntityScan(basePackageClasses = {Species.class,Ability.class})
@EnableJpaRepositories(basePackageClasses = {AbilityRepository.class})
@ComponentScan(basePackageClasses={SpeciesResource.class,AbilityRepository.class})
@OpenAPIDefinition(    
       
        security = {@SecurityRequirement(name = "jwt-token", scopes = {"admin","user"})},
    info = @io.swagger.v3.oas.annotations.info.Info(
        title = "Heroes of Empires - species", 
        version = "v1",
        description = "OE Microservice Project", 
        license = @io.swagger.v3.oas.annotations.info.License(name = "Apache 2.0", url = "http://foo.bar"), 
        contact = @Contact(url = "http://valami.com", name = "Karóczkai (K)risztián", email = "krisztian@mozilla.hu")))

public class Application extends OpenApiApplication{
       
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }    
    
}
