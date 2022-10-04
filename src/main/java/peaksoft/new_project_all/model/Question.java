package peaksoft.new_project_all.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.new_project_all.enums.QuestionType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questions")
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(generator = "question_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "question_gen",sequenceName = "question_seq",allocationSize = 1)
    private Long id;
    private String question;
    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Option> optionList;

    public Question(String question, QuestionType questionType) {
        this.question = question;
        this.questionType = questionType;
    }

    public void addOptionToQuestion(Option option) {
        if (this.optionList == null) {
            this.optionList = new ArrayList<>();
        }
        this.optionList.add(option);
    }
}
