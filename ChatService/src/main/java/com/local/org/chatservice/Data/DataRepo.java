package com.local.org.ChatService.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepo extends JpaRepository<DataEntity,Integer> {
}
