package com.treeleaf.springgateway.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private int id;
    private int userId;
    private String post;

    public Blog(int userId,String text){
        this.userId=userId;
        this.post=text;
    }

}
