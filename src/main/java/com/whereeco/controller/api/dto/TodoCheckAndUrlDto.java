package com.whereeco.controller.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TodoCheckAndUrlDto {

    private boolean todo1;
    private boolean todo2;
    private boolean todo3;
    private String url1;
    private String url2;
    private String url3;
}
