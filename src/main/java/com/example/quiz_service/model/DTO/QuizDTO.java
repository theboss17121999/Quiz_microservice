package com.example.quiz_service.model.DTO;


public record QuizDTO(
        String category,
        int numQ,
        String title
) {
}
