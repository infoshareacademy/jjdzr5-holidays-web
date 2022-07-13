package org.isa.holidaysweb.config.userlogging;

import org.isa.holidaysweb.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.isa.holidaysweb.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserPrincipalDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDto userDto = userService.findByUserName(username);
        return new UserPrincipal(userDto);
    }
}
