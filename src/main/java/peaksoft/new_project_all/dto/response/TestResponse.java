package peaksoft.new_project_all.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestResponse {

    private Long id;
    private String name;
    private String duration;

    public TestResponse(Long id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }
}
