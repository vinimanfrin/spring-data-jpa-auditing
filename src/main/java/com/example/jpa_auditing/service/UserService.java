package com.example.jpa_auditing.service;

import com.example.jpa_auditing.domain.User;
import com.example.jpa_auditing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> createMockUsers(){
        User user1 = new User(null,"vinicius@gmail.com","Vinicius");
        User user2 = new User(null,"joaozinho@gmail.com","Joaozinho");
        User user3 = new User(null,"mariazinha@gmail.com","Mariazinha");
        User user4 = new User(null,"samarinha@gmail.com","Samarinha");
        User user5 = new User(null,"messi@gmail.com","Messi");
        User user6 = new User(null,"cr7@gmail.com","Cr7");

        return repository.saveAll(List.of(user1,user2,user3,user4,user5,user6));
    }
}
