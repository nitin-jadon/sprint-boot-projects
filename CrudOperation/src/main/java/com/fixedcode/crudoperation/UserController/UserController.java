package com.fixedcode.crudoperation.UserController;

import com.fixedcode.crudoperation.UserEntity.UserEntity;
import com.fixedcode.crudoperation.UserService.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.EXPECTATION_FAILED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserEntity user)
    {
        log.info(user.getFirstName(), user.getLastName(), user.getSalary());
        String validation = userService.save(user);
        if(validation.equals("user data saved"))
        {
            return ResponseEntity.status(OK).body("Data is saved successfully");
        }
        else
        {
            return ResponseEntity.status(EXPECTATION_FAILED).body("Data is not saved");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getById(@PathVariable Long id)
    {
        Optional<UserEntity> user = userService.getById(id);
        return ResponseEntity.status(OK).body(user.get());
    }
    @PostMapping("/update/")
    public ResponseEntity<String> update(@RequestBody UserEntity user)
    {

        String validation = userService.update(user);
        if(validation.equals("user data updated"))
        {
            return ResponseEntity.status(OK).body("Data is updated successfully");
        }

        return ResponseEntity.status(EXPECTATION_FAILED).body("Data is not updated");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
    {
        Optional<UserEntity> user= userService.getById(id);
        if(user.isPresent())
        {
            userService.delete(user.get());
            return ResponseEntity.status(OK).body("User Data is deleted successfully");
        }
        return ResponseEntity.status(EXPECTATION_FAILED).body("User Data does not exists.");
    }
}
