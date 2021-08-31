package com.kosmetologistpaycalc.paycalc.Repo;

import com.kosmetologistpaycalc.paycalc.Models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Collection;

public interface PostRepository extends CrudRepository<Post, Long> {
    @Query(value = "SELECT * FROM post u LIMIT 10", nativeQuery = true)
    Collection<Post> findtenlastid(Post posts);
}
