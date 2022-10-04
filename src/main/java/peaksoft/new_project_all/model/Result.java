package peaksoft.new_project_all.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "results")
@Getter
@Setter
@NoArgsConstructor
public class Result {

    @Id
    @GeneratedValue(generator = "student_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen",sequenceName = "student_seq",allocationSize = 1)
    private Long id;
    private Integer amountOfCorrectAnswer;
    private Integer amountOfWrongAnswer;
    private Integer point;
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.DETACH})
    private Student student;

    public Result(Integer amountOfCorrectAnswer, Integer amountOfWrongAnswer, Integer point, Student student) {
        this.amountOfCorrectAnswer = amountOfCorrectAnswer;
        this.amountOfWrongAnswer = amountOfWrongAnswer;
        this.point = point;
        this.student = student;
    }
}
