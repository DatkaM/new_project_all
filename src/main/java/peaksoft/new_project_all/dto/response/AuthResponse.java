package peaksoft.new_project_all.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import peaksoft.new_project_all.enums.Role;

@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {

    private String email;
    private String token;
    private Role role;
}
