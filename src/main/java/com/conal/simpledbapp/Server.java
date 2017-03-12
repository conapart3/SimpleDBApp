package com.conal.simpledbapp;

/**
 * Server object
 */
public class Server {

    private String id;
    private String name;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void printServer() {
        System.out.println("ID: " + id + " | Name: " + name + " | description: " + description);
    }
}
