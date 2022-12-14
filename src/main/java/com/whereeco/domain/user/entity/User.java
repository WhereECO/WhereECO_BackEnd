package com.whereeco.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.whereeco.controller.dto.TodoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true,length = 20)
    private String userId;

    // 클라이언트에서 pwd 필드를 사용할 필요가 없으므로,
    // response 보내지 않도록 함
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 255)
    private String pwd;

    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = true, length = 30)
    private String todoText1 = "텀블러는 챙겼나요?";
    @Column(nullable = true, length = 30)
    private String todoText2 = "분리수거를 해요!";
    @Column(nullable = true, length = 30)
    private String todoText3 = "대중교통을 이용해요!";

    private boolean todo1;
    private boolean todo2;
    private boolean todo3;

    public User(String userId, String pwd, String name) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
    }

    public void updateTodo(TodoDto todoDto) {
        this.todoText1 = todoDto.getTodoText1();
        this.todoText2 = todoDto.getTodoText2();
        this.todoText3 = todoDto.getTodoText3();
        this.todo1 = todoDto.isTodo1();
        this.todo2 = todoDto.isTodo2();
        this.todo3 = todoDto.isTodo3();
    }
}
