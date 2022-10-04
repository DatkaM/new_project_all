package peaksoft.new_project_all.dto.request;

import lombok.Getter;
import lombok.Setter;
import peaksoft.new_project_all.dto.request.OptionRequest;
import peaksoft.new_project_all.enums.QuestionType;

import java.util.List;

@Getter
@Setter
public class QuestionRequest {

    private String question;
    private QuestionType questionType;
    private List<OptionRequest> options;

    public QuestionRequest(String question, QuestionType questionType) {
        this.question = question;
        this.questionType = questionType;
    }
}
