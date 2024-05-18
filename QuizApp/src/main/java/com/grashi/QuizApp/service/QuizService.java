package com.grashi.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.grashi.QuizApp.dao.QuestionDao;
import com.grashi.QuizApp.dao.QuizDao;
import com.grashi.QuizApp.model.Question;
import com.grashi.QuizApp.model.QuestionWrapper;
import com.grashi.QuizApp.model.Quiz;
import com.grashi.QuizApp.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizdao;
	
	@Autowired
	QuestionDao questiondao;
	

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions = questiondao.findRandomQuestionsByCategory(category, numQ); 
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizdao.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED); 
	}


	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizdao.findById(id);
		List<Question> questionFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<QuestionWrapper>();
		for(Question q : questionFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
			questionForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}


	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizdao.findById(id).get();
		
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;	
		
		i++;	
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}
	
	
	
	
	
}
