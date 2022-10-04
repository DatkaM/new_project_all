package peaksoft.new_project_all.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponse {

    private Long id;
    private String option;

    public OptionResponse(Long id, String option) {
        this.id = id;
        this.option = option;
    }
}
