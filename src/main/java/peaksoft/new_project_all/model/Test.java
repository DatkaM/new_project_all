package peaksoft.new_project_all.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tests")
@Getter
@Setter
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(generator = "test_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "test_gen",sequenceName = "test_seq",allocationSize = 1)
    private Long id;
    private String name;
    private String duration;

    public Test(Long id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Question> questions;

    public Test(String name, String duration) {
        this.name = name;
        this.duration = duration;
    }

    public void addQuestionToTest(Question question){
        if (this.questions == null) {
           this.questions = new ArrayList<>();
        }
        this.questions.add(question);
    }
}
