package com.example;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NamedQueries({
        @NamedQuery(name = "Todo.findAll", query = "SELECT i from Todo i"),
        @NamedQuery(name = "Todo.byTitle", query = "SELECT i FROM Todo i WHERE i.title = :title"),
        @NamedQuery(name = "Todo.byUrl", query = "SELECT i from Todo i WHERE i.url = :url"),
        @NamedQuery(name = "Todo.findByCompleted", query = "SELECT i from Todo i WHERE i.completed = :completed"),
        @NamedQuery(name = "Todo.deleteCompleted", query = "DELETE FROM Todo WHERE completed = true")
})

public class Todo {

    // {"title":"things need to be done","order":1,"completed":false}

    public long getId() {
        return id;
    }

    public void setId(long id){
        this.id = id;
    }

    @Id
    public long id;

    @NotBlank
    @Column(unique = true)
    public String title;

    public boolean completed;

    @Column(name = "ordering")
    public int order;

    public String url;

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getOrder() {
        return order;
    }

    public String getUrl() {
        return url;
    }


}
