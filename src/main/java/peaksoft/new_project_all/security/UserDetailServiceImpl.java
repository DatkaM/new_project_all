package peaksoft.new_project_all.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import peaksoft.new_project_all.repository.AuthInfoRepository;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final AuthInfoRepository authInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return authInfoRepository.findByEmail(username).orElseThrow(()->
                 new RuntimeException("not found"));
    }
}
