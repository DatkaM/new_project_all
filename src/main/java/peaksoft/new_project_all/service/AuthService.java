package peaksoft.new_project_all.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.new_project_all.dto.request.AuthRequest;
import peaksoft.new_project_all.dto.request.StudentRegisterRequest;
import peaksoft.new_project_all.dto.response.AuthResponse;
import peaksoft.new_project_all.dto.response.StudentRegisterResponse;
import peaksoft.new_project_all.model.AuthInfo;
import peaksoft.new_project_all.model.Student;
import peaksoft.new_project_all.repository.AuthInfoRepository;
import peaksoft.new_project_all.repository.StudentRepository;
import peaksoft.new_project_all.security.jwt.JwtUtils;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthInfoRepository authInfoRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse login(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                authRequest.getEmail(),
                authRequest.getPassword())
        );
        AuthInfo authInfo = authInfoRepository.findByEmail(authentication.getName()).
                orElseThrow(()->
                        new BadCredentialsException("bad credentials")
                );
        String token = jwtUtils.generateToken(authInfo.getUsername());
        return new AuthResponse(authInfo.getUsername(),token,authInfo.getRole());
    }

    public StudentRegisterResponse register(StudentRegisterRequest request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Student student = new Student(request);
        Student savedStudent = studentRepository.save(student);
        String[] array = savedStudent.getName().split(" ",2);
        String token = jwtUtils.generateToken(savedStudent.getEmail());
        return new StudentRegisterResponse(array[0],array[0],savedStudent.getEmail(),token,savedStudent.getAuthInfo().getRole());
    }


}
