package com.whereeco.domain.youtubeurl.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "youtube_url")
@Getter
@Setter
@NoArgsConstructor
public class YoutubeUrl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;

    public YoutubeUrl(String url) {
        this.url = url;
    }
}






















