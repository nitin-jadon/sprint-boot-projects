package com.fixedcode.crudoperation.UserRepository;

import com.fixedcode.crudoperation.UserEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
