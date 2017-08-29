package org.bana.banaresourceserver;

import java.util.HashSet;
import java.util.Set;

import org.bana.entity.Document;
import org.bana.entity.User;
import org.bana.service.DocumentService;
import org.bana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("org.bana.service")
@EntityScan("org.bana.entity")
@ComponentScan("org.bana.controller")
public class BanaResourceServerApplication {
	@Autowired
	private DocumentService documentService;
	
	@Autowired
	private UserService personService;

	public static void main(String[] args) {
		SpringApplication.run(BanaResourceServerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return(x) -> {
			User user = new User("bana", "password", "depok", null);
			personService.save(user);
			
			Set<Document> datas = new HashSet<>();
			
			Document dataDocument1 = new Document("buku1", null);
			Document dataDocument2 = new Document("buku2", null);
			
			documentService.save(dataDocument1);
			documentService.save(dataDocument2);
			
			
			datas.add(dataDocument1);
			datas.add(dataDocument2);
			
			user.setDocuments(datas);
			
			personService.save(user);
			
			dataDocument1.setDataPerson(user);
			dataDocument2.setDataPerson(user);
			
			documentService.save(dataDocument1);
			documentService.save(dataDocument2);
			
		};
	}
}
