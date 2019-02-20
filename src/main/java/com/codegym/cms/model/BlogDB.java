package com.codegym.cms.model;

public class BlogDB {
    private int id;
    private String name;
    private String authName;
    private String tagName;
    private String content;

    public BlogDB(int id, String name, String authName, String tagName, String content) {
        this.id = id;
        this.name = name;
        this.authName = authName;
        this.tagName = tagName;
        this.content = content;
    }

    public BlogDB(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
