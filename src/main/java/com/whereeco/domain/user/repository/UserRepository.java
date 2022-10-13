package com.whereeco.domain.user.repository;

import com.whereeco.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("update User  u set u.todo1 = false, u.todo2 = false , u.todo3 = false")
    @Modifying
    void refreshAll();

    Optional<User> findByUserId(String userId);
}
