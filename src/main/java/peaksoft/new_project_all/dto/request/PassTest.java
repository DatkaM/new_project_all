package peaksoft.new_project_all.dto.request;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class PassTest {

    private Long testId;
    private Map<Long, List<Long>> answer;

}
