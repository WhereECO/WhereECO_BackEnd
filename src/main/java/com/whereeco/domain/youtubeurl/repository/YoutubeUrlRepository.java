package com.whereeco.domain.youtubeurl.repository;

import com.whereeco.domain.youtubeurl.entity.YoutubeUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface YoutubeUrlRepository extends JpaRepository<YoutubeUrl, Long> {
}
