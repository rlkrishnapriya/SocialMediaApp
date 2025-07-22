package com.example.liquibase_demo.repository;

import com.example.liquibase_demo.entity.GroupMember;
import com.example.liquibase_demo.entity.Group;
import com.example.liquibase_demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Integer> {

    List<GroupMember> findByGroup(Group group);

    List<GroupMember> findByUser(User user);

    Optional<GroupMember> findByGroupAndUser(Group group, User user);

    boolean existsByGroupAndUser(Group group, User user);
}