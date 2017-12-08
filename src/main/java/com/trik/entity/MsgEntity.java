package com.trik.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Trik.Song on 2017/11/27
 */

@Entity(name="message")
public class MsgEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String message;
    private String title;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle(){return title;}

    public void setTitle(String title){this.title=title;}
}
