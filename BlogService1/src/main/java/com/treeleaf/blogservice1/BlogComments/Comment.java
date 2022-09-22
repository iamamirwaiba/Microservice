package com.treeleaf.blogservice1.BlogComments;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="blog_id")
    private int blogId;
    @Column(name="comment")
    private String comment;

    public Comment(int blogId, String comment) {
        this.blogId = blogId;
        this.comment = comment;
    }

    public Comment(String comment) {
        this.comment = comment;
    }
}
