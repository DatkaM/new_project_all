package peaksoft.new_project_all.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.new_project_all.dto.request.AuthRequest;
import peaksoft.new_project_all.dto.request.StudentRegisterRequest;
import peaksoft.new_project_all.dto.response.AuthResponse;
import peaksoft.new_project_all.dto.response.StudentRegisterResponse;
import peaksoft.new_project_all.service.AuthService;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/auth")
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public StudentRegisterResponse register(@RequestBody StudentRegisterRequest request) {
        return authService.register(request);
    }
}
