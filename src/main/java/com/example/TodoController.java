package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @RequestMapping("")
    public List<Todo> getAll() {
        return (List<Todo>) todoRepository.findAll();
    }


    @RequestMapping(method=RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Object> createStudent(@RequestBody Todo todo) {

        Todo savedTodo = todoRepository.save(todo);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedTodo.title).toUri();

        return ResponseEntity.created(location).body(savedTodo);

    }


    @RequestMapping(method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity deleteCompleted() {
        todoRepository.deleteCompleted();
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }


    @RequestMapping("/{id}")
    public Todo getOne(@PathVariable("id") Long id) {
        Optional<Todo> todoOptional = todoRepository.findById(id);
        return todoOptional.get();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
    public ResponseEntity update(Todo todo, @PathVariable("id") Long id) {

        Optional<Todo> todoOptional = todoRepository.findById(id);

        if (!todoOptional.isPresent())
            return ResponseEntity.notFound().build();

        todo.setId(id);
        todoRepository.save(todo);

        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, consumes = "application/json")
    public ResponseEntity deleteOne(@PathVariable("id") Long id) {
        todoRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }




}

