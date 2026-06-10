package com.example.quiz_service.Controller;


import com.example.quiz_service.Service.QuizService;
import com.example.quiz_service.model.DTO.QuizDTO;
import com.example.quiz_service.model.QuestionWrapper;
import com.example.quiz_service.model.Response;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizCrontroller {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.category(),quizDTO.numQ(),quizDTO.title());
    }

    @GetMapping("getQuiz/{id}")
    @RateLimiter(name = "quizService", fallbackMethod = "rateLimiterFallback")
    public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
        System.out.println("quiz");
        return quizService.getQuizQuestions(id);
    }

    public ResponseEntity<List<QuestionWrapper>> rateLimiterFallback(int id){
        return ResponseEntity.status(429).build();
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
