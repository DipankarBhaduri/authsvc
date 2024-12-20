package com.mingleHub.authsvc.dto.assessment;

import com.mingleHub.authsvc.constants.DocumentType;
import com.mingleHub.authsvc.constants.ProgrammingLanguage;
import com.mingleHub.authsvc.constants.QuestionDifficultyLevel;
import com.mingleHub.authsvc.constants.QuestionType;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class QuestionCreationRequest {
    private String question;
    private QuestionType questionType;
    private QuestionDifficultyLevel questionDifficultyLevel;
    private ProgrammingLanguage programmingLanguage;
    private Option option;
    private Long score;
    private Long durationInSec;
    private Document document;
    private Long minimumCharacterLength;
    private Long maximumCharacterLength;

    @Data
    @Accessors(chain = true)
    private static class Option {
        private Boolean isCorrect;
        private String value;
    }

    @Data
    @Accessors(chain = true)
    private static class Document {
        private DocumentType documentType;
        private String s3Url;
    }
}