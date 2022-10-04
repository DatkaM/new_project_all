package peaksoft.new_project_all.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import peaksoft.new_project_all.dto.request.PassTestRequest;
import peaksoft.new_project_all.dto.request.TestRequest;
import peaksoft.new_project_all.dto.response.ResultResponse;
import peaksoft.new_project_all.dto.response.TestInnerPageResponse;
import peaksoft.new_project_all.dto.response.TestResponse;
import peaksoft.new_project_all.model.AuthInfo;
import peaksoft.new_project_all.service.TestService;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/test")
public class TestApi {

    private final TestService testService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public TestResponse saveTest(@RequestBody TestRequest request) {
        return testService.save(request);
    }

    @PostMapping("{id}")
    public TestInnerPageResponse getById(@PathVariable Long id) {
        return testService.getTestById(id);
    }

    @PostMapping("/pass")
    public ResultResponse passTest(@RequestBody PassTestRequest request,
                                   Authentication authentication) {
        AuthInfo authInfo = (AuthInfo) authentication.getPrincipal();
        return testService.passTest(request,authInfo);
    }
}
