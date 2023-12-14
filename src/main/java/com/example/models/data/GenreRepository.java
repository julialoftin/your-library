package com.example.models.data;

import com.example.models.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Integer> {

//    Optional<Genre> findByName(String name);

}
