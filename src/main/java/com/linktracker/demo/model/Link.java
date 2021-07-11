package com.linktracker.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table()
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String url;
    private String shortUrl;
    private Integer nRedirects;
    private boolean valid;

    public Link(String url, String shortUrl) {
        this.url = url;
        this.shortUrl = shortUrl;
        this.nRedirects =0;
        this.valid =true;
    }

    public Link(Integer id, String url, String shortUrl, Integer nRedirects) {
        this.id = id;
        this.url = url;
        this.shortUrl = shortUrl;
        this.nRedirects = nRedirects;
        this.valid =true;
    }

    public Link() {
    }
}
