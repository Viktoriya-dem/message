package com.example.message.repo;

import com.example.message.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Integer> {

    @Query(nativeQuery = true, value = "Select * from (select * from messages order by id desc limit 10) as new order by id")
    List<Message> getTenLastMessages();

}
