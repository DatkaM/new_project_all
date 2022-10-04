package peaksoft.new_project_all.dto.response;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestInnerPageResponse {

    private Long id;
    private String name;
    private String duration;
    private List<QuestionResponse> questions;

    public TestInnerPageResponse(Long id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
}
