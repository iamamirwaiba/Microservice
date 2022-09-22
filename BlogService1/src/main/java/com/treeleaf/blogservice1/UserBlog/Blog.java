package com.treeleaf.blogservice1.UserBlog;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="user_id")
    private int userId;
    @Column(name="post")
    private String post;

    public Blog(int userId, String post) {
        this.userId = userId;
        this.post = post;
    }

    public Blog(String post){
        this.post=post;
    }
}
