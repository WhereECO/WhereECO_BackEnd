package com.whereeco.domain.user.repository;

import com.whereeco.controller.api.dto.TodoCheckAndUrlDto;
import com.whereeco.controller.dto.TodoDto;
import com.whereeco.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("update User  u set u.todo1 = false, u.todo2 = false , u.todo3 = false")
    @Modifying
    void refreshAll();

    User findByUserId(String userId);


    @Query("select u from User u where u.userId = :userId")
    User findTodo(@Param("userId") String userId);

    // @Param Object 보내는 법. #{#Object.name}
    @Query("update User u set u.todo1 = :#{#todoDto.todo1}, u.todo2 = :#{#todoDto.todo2} , u.todo3 = :#{#todoDto.todo3}" +
            ", u.todoText1 = :#{#todoDto.todoText1}, u.todoText2 = :#{#todoDto.todoText2}, u.todoText3 = :#{#todoDto.todoText3} where u.userId = :userId")
    @Modifying
    void updateTodoByUserId(@Param("userId") String userId, @Param("todoDto") TodoDto todoDto);

    @Query("update User u set u.todo1 =:#{#todoDto.todo1}, u.todo2 = :#{#todoDto.todo2} , u.todo3 = :#{#todoDto.todo3}" +
            " where u.userId = :userId")
    @Modifying
    void updateTodoCheckByUserId(@Param("userId") String userId, @Param("todoDto") TodoCheckAndUrlDto todoCheckAndUrlDto);
}
