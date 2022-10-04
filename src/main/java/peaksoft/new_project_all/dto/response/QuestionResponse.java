package peaksoft.new_project_all.dto.response;

import lombok.Getter;
import lombok.Setter;
import peaksoft.new_project_all.enums.QuestionType;

import java.util.List;

@Getter
@Setter
public class QuestionResponse {

    private Long id;
    private String question;
    private QuestionType questionType;
    private List<OptionResponse> options;

    public QuestionResponse(Long id, String question, QuestionType questionType) {
        this.id = id;
        this.question = question;
        this.questionType = questionType;
    }
}
