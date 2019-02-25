package com.codegym.cms.model;

import javax.persistence.*;
import java.io.Serializable;


@Table(name = "blog")
@Entity
public class Blog implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "authorId")
    private int authorId;

    @Column(name = "tagId")
    private int tagId;

    @Column(name = "content")
    private String content;

    public Blog() {}

    public Blog(int id, String name, int authId, int tagId, String content) {
        this.id = id;
        this.name = name;
        this.authorId = authId;
        this.tagId = tagId;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("Blog[id=%d, name='%s', authId=%d, tagId=%d, content='%s']", id, name, authorId, tagId, content);
    }

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

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authId) {
        this.authorId = authId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
