package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musthavecaffeine.recipeapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
