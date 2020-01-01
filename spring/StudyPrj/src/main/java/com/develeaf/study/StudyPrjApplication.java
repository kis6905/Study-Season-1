package com.develeaf.study;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.develeaf.study.StudyPrjApplication.Team;
import com.develeaf.study.StudyPrjApplication.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
		private MyService myService;
		
		@GetMapping("/user/{userSeq}")
		public User getUser(@PathVariable(name = "userSeq") Long userSeq) {
			log.info("userSeq = {}", userSeq);
			User user = myService.getUser(userSeq);
			return user;
		}
		
		@GetMapping("/user")
		public User getUserName(@RequestParam(name = "userName") String userName) {
			log.info("userName = {}", userName);
			User user = myService.getUserByName(userName);
			return user;
		}
		
		@GetMapping("/team/{teamSeq}")
		public Team getTeam(@PathVariable(name = "teamSeq") Long teamSeq) {
			log.info("teamSeq = {}", teamSeq);
			Team team = myService.getTeam(teamSeq);
			
			log.info("team: {}", team.getName());
			// default fetch=LAZY
			log.info("userListSize: {}", team.getUserList().size());
			
			return team;
		}
	}
	
	@Service
	public static class MyService {
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private TeamRepository teamRepository;
		
		public User getUser(Long userSeq) {
			return userRepository.findById(userSeq).orElse(null);
		}
		
		public User getUserByName(String userName) {
			return userRepository.findUserByName(userName);
		}
		
		public Team getTeam(Long teamSeq) {
			return teamRepository.findById(teamSeq).orElse(null);
		}
		
	}
	
	@Data
	@Entity(name = "User")
	@Table(name = "user")
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class User {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long userSeq;
		@Column(length = 255, nullable = true)
		private String name;
		@Column(length = 255, nullable = true)
		private String address;
		
		@ManyToOne
		@JoinColumn(name = "team_seq")
		@JsonBackReference
		private Team team;
		
		@ManyToMany
		@JoinTable(name = "user_role",
				   joinColumns = @JoinColumn(name = "user_seq"),
				   inverseJoinColumns = @JoinColumn(name = "role_seq"))
		private List<Role> roleList;
	}
	
	@Data
	@Entity(name = "Team")
	@Table(name = "team")
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Team {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long teamSeq;
		@Column(length = 255, nullable = true)
		private String name;
		
		@OneToMany
		@JoinColumn(name = "team_seq")
		@JsonManagedReference
		private List<User> userList;
	}
	
	@Data
	@Entity(name = "Role")
	@Table(name = "role")
	@Builder
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Role {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long roleSeq;
		@Column(length = 255, nullable = true)
		private String name;
	}
}

@Repository
interface UserRepository extends JpaRepository<User, Long> {
	@Query(value = "SELECT u FROM User u WHERE u.name = :name")
	User findUserByName(@Param("name") String userName);
}

@Repository
interface TeamRepository extends JpaRepository<Team, Long> {
}
