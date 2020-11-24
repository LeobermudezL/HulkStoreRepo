package com.example.hulkstore.repository;

import com.example.hulkstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository <User, Long> {
}
