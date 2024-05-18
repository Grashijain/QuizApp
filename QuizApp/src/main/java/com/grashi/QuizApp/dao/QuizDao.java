package com.grashi.QuizApp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grashi.QuizApp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
