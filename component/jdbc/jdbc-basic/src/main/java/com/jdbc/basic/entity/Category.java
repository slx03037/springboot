package com.jdbc.basic.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

import java.time.LocalDateTime;

/**
 * @author shenlx
 * @description
 * @date 2026/2/24 21:21
 */
public class Category {
    private final @Id Long id;
    private String name, description;
    private LocalDateTime created;
    private Long inserted;

    public Category(String name, String description) {

        this.id = null;
        this.name = name;
        this.description = description;
        this.created = LocalDateTime.now();
    }

    @PersistenceCreator
    Category(Long id, String name, String description, LocalDateTime created, Long inserted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.created = created;
        this.inserted = inserted;
    }

    public void timeStamp() {

        if (inserted == 0) {
            inserted = System.currentTimeMillis();
        }
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public Long getInserted() {
        return this.inserted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public void setInserted(Long inserted) {
        this.inserted = inserted;
    }

    public Category withId(Long id) {
        return this.id == id ? this : new Category(id, this.name, this.description, this.created, this.inserted);
    }

    @Override
    public String toString() {
        return "Category(id=" + this.getId() + ", name=" + this.getName() + ", description=" + this.getDescription()
                + ", created=" + this.getCreated() + ", inserted=" + this.getInserted() + ")";
    }
}
