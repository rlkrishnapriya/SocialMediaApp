package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.GroupPost;
import com.example.liquibase_demo.entity.Group;
import com.example.liquibase_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupPostRepository extends JpaRepository<GroupPost, Integer> {

    List<GroupPost> findByGroup(Group group);

    List<GroupPost> findByUser(User user);
}