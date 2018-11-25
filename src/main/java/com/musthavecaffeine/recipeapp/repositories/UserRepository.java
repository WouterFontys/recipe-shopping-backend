package com.musthavecaffeine.recipeapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.musthavecaffeine.recipeapp.domain.Recipe;
import com.musthavecaffeine.recipeapp.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
