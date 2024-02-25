package com.fixedcode.crudoperation.UserService;

import com.fixedcode.crudoperation.UserEntity.UserEntity;
import com.fixedcode.crudoperation.UserRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    public String save(UserEntity user)
    {
        try
        {
            userRepository.save(user);
            return "user data saved";
        }
        catch(Exception e)
        {
            throw new RuntimeException("user with ID" +  user.getId() + "is not save" );
        }

    }
    public Optional<UserEntity> getById(Long id)
    {
        return userRepository.findById(id);
    }
    public String update(UserEntity user)
    {
        try
        {
            userRepository.save(user);
            return "user data updated";
        }
        catch(Exception e)
        {
            throw new RuntimeException("user with ID" +  user.getId() + "is not updated" );
        }
    }
    public void delete(UserEntity user)
    {
        userRepository.delete(user);
    }
}
