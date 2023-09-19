package com.mycompany.service;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.Exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repo;

    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> ListAll(){
        return  (List<User>) repo.findAll();
    }

    public void save(User user){
        repo.save(user);
    }

    public User get(int id) throws UserNotFoundException{
        Optional<User> result = repo.findById(id);
        if(result.isPresent()){
            return result.get();
        }
        throw new UserNotFoundException("could not find any users with ID"+id);
    }

    public void delete(int id) throws UserNotFoundException{
        repo.deleteById(id);
    }

    public List<User> findByKeyword(String keyword){
        List<User> firstnameList = repo.findByFirstnameContainingIgnoreCase(keyword);
        return firstnameList;
    }
}
