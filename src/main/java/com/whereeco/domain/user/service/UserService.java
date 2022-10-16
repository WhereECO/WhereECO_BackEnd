package com.whereeco.domain.user.service;

import com.whereeco.controller.api.dto.TodoCheckAndUrlDto;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.repository.UserRepository;
import com.whereeco.domain.youtubeurl.entity.YoutubeUrl;
import com.whereeco.domain.youtubeurl.service.YoutubeUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final YoutubeUrlService youtubeUrlService;

    public void refreshAll(){
        userRepository.refreshAll();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


    public TodoCheckAndUrlDto findTodoAndUrl(String userId) {
        User user = userRepository.findTodo(userId);
        Map<String, String> urlMap = youtubeUrlService.getRandomUrlMap(YoutubeUrl.URL_COUNT, "");
        return new TodoCheckAndUrlDto(user.isTodo1(), user.isTodo2(), user.isTodo3()
            , urlMap.get("url1"), urlMap.get("url2"), urlMap.get("url3"));
    }

    @Transactional  // update 쿼리는 Transactional readonly로도 동작? https://stackoverflow.com/questions/28151794/why-does-jpa-modifying-query-require-transactional-annotation
    public void updateTodoByUserId(String userId, com.whereeco.controller.dto.TodoDto todoDto) {
        userRepository.updateTodoByUserId(userId, todoDto);
    }


    @Transactional
    public void updateTodoCheckByUserId(String userId, TodoCheckAndUrlDto todoCheckAndUrlDto) {
        userRepository.updateTodoCheckByUserId(userId, todoCheckAndUrlDto);
    }
}
