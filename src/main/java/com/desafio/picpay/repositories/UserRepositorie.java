package com.desafio.picpay.repositories;

import com.desafio.picpay.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositorie extends JpaRepository<Users, Long> {
}
