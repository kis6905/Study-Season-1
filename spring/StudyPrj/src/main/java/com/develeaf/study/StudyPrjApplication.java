package com.develeaf.study;

import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.develeaf.study.StudyPrjApplication.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
public class StudyPrjApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudyPrjApplication.class, args);
	}
	
	@RestController
	@Slf4j
	public static class MyController {
		
		@Autowired
		private UserRepository userRepository;
		
		@PostConstruct
		void postConstruct() {
			log.info("postConstruct()...");
			User user = new User();
			user.setId(1L);
			user.setName("iskwon");
			user.setAddress("paju");
			userRepository.save(user);
		}
		
		@GetMapping("/user/{id}")
		public User getUser(@PathVariable(name = "id") String id) {
			log.info("id = {}", id);
//			User user = User.builder().name("iskwon").address("paju").build();
			User user = userRepository.findByName(id);
			return user;
		}
		
	}
	
	@Data
	@Entity(name = "User")
	@Table(name = "user")
	@NoArgsConstructor
	public static class User {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		@Column(length = 255, nullable = true)
		private String name;
		@Column(length = 255, nullable = true)
		private String address;
	}
}

@Repository
interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}
