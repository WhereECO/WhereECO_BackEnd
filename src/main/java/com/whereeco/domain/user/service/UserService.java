package com.whereeco.domain.user.service;

import com.whereeco.controller.api.dto.TodoDto;
import com.whereeco.domain.user.entity.User;
import com.whereeco.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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

    public TodoDto findTodo(String userId) {
        User user = userRepository.findTodo(userId);
        return new TodoDto(user.isTodo1(), user.isTodo2(), user.isTodo3());
    }

    public void updateTodoByUserId(String userId, com.whereeco.controller.dto.TodoDto todoDto) {
        userRepository.updateTodoByUserId(userId, todoDto);
    }
}
