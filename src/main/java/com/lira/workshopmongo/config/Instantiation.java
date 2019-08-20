package com.lira.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.lira.workshopmongo.domain.Post;
import com.lira.workshopmongo.domain.User;
import com.lira.workshopmongo.dto.AuthorDTO;
import com.lira.workshopmongo.repository.PostRepository;
import com.lira.workshopmongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob));

		Post post = new Post(null, sdf.parse("20/08/2019"), "Partiu viagem", "Vou viajar para São Paulo, abraços",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("20/08/2019"), "Bom dia", "Borá tomar café!!", new AuthorDTO(maria));

		postRepository.saveAll(Arrays.asList(post, post2));

		maria.getPosts().addAll(Arrays.asList(post, post2));
		userRepository.save(maria);
	}

}
