package com.treeleaf.blogservice1.UserBlog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepo extends JpaRepository<Blog,Integer> {

    @Modifying
    @Query("update Blog u set u.post= :text where u.id= :id")
    void updateBlog(@Param("id") int id, @Param("text") String text);

}
