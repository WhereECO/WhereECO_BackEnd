package com.whereeco.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodoDto {

    private String todoText1;
    private String todoText2;
    private String todoText3;

    // 인스턴스 변수 boolean 의 기본값은 false 이다.
    // 지역변수는 초기화 필요
    private boolean todo1;
    private boolean todo2;
    private boolean todo3;
}
