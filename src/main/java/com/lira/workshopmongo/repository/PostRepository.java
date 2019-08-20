package com.lira.workshopmongo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.lira.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// ?0, pega o parâmetro do método, se tiver mais parâmetro segue sequência ?1,
	// ?2 etc.
	// i, ignora maiúscula e minuscúla
	@Query("{ 'title': { $regex: ?0, $options: 'i'} }")
	List<Post> searchTitle(String text);

	List<Post> findByTitleContainingIgnoreCase(String text);

}
