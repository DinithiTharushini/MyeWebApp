package com.mycompany;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace =AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {
    @Autowired private UserRepository repo;

    @Test
    public void testAddNew(){
        User user =new User();
        user.setEmail("123@gmail.com");
        user.setPassword("12345");
        user.setFirstname("Dinithi");
        user.setLastname("Tharushini");

        User saveduser=repo.save(user);
        Assertions.assertThat(saveduser).isNotNull();
        Assertions.assertThat(saveduser.getId()).isGreaterThan(0);

    }
    @Test
    public void testListAll(){
        Iterable<User> users=repo.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user:users){
            System.out.println(user);
        }
    }

    @Test
    public void testupdate(){
        int userId=1;
        Optional<User> optionalUser=repo.findById(userId);
        User user=optionalUser.get();
        user.setPassword("2000");
        repo.save(user);

        User updatedUser=repo.findById(userId).get();
        Assertions.assertThat(updatedUser.getPassword()).isEqualTo("2000");

    }
    @Test
    public void testGet(){
        int userId=1;
        Optional<User> optionalUser=repo.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());


    }
    @Test
    public void testDelete(){
        int userid=1;
        repo.deleteById(userid);
        Optional<User> optionalUser=repo.findById(userid);
        Assertions.assertThat(optionalUser).isNotPresent();

    }
}
