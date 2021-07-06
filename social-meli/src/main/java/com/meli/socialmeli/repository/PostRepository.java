package com.meli.socialmeli.repository;

import com.meli.socialmeli.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
}
