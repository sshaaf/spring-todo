package com.example;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

    public List<Todo> findByTitle(String title);

    public List<Todo> findByUrl(String url);

    public List<Todo> findByCompleted(boolean completed);

    public void deleteCompleted();

}