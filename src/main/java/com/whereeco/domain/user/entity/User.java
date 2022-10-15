package com.whereeco.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String todoText1;
    @Column(nullable = true, length = 30)
    private String todoText2;
    @Column(nullable = true, length = 30)
    private String todoText3;

    private boolean todo1;
    private boolean todo2;
    private boolean todo3;


    public User(String userId, String pwd, String name) {
        this.userId = userId;
        this.pwd = pwd;
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
