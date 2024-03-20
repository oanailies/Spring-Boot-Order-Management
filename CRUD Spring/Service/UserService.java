package com.example.CRUD_Tutorial.Service;

import com.example.CRUD_Tutorial.Entity.User;
import com.example.CRUD_Tutorial.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired private UserRepository repo;

    public UserService(UserRepository repo, BCryptPasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> listAll()
    {
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        repo.save(user);
    }

    public User get(Integer id)
    {
        Optional<User> result = repo.findById(id);
        if(result.isPresent())
        {
            return result.get();
        }
        throw new NotFoundException("nu s a gasit userul");
    }

    public void delete(Integer id)
    {
        Long count=repo.countById(id);
        if(count==null || count ==0)
        {
            throw new NotFoundException("nu s a gasit userul");
        }
        repo.deleteById(id);
    }

    public User findByUsername(String username) {
        return repo.findByUsername(username);
    }
}
