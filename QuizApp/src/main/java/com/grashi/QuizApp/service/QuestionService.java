package com.grashi.QuizApp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.grashi.QuizApp.dao.QuestionDao;
import com.grashi.QuizApp.model.Question;

@Service
public class QuestionService {

	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
			}catch(Exception e) {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
			}
		 
	}

	public ResponseEntity<String> addQuestion(Question question) {
		try {
			questionDao.save(question);
			return new ResponseEntity<>("success", HttpStatus.CREATED);
			}catch(Exception e) {
				return new ResponseEntity<>("Unsuccessful", HttpStatus.BAD_REQUEST);
			}
		 
		
		
	}

}
