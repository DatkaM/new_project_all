package peaksoft.new_project_all.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestRequest {

    private String name;
    private String duration;
    private List<QuestionRequest> questions;

}
