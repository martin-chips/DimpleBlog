package com.dimple.modules.security.service;

import com.dimple.exception.BadRequestException;
import com.dimple.exception.EntityNotFoundException;
import com.dimple.modules.security.service.dto.JwtUserDTO;
import com.dimple.modules.system.service.RoleService;
import com.dimple.modules.system.service.UserService;
import com.dimple.modules.system.service.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @className: UserDetailsServiceImpl
 * @description:
 * @author: Dimple
 * @date: 06/17/20
 */
@RequiredArgsConstructor
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final RoleService roleService;

    @Override
    public JwtUserDTO loadUserByUsername(String username) {
        UserDTO user;
        try {
            user = userService.findByName(username);
        } catch (EntityNotFoundException e) {
            // SpringSecurity会自动转换UsernameNotFoundException为BadCredentialsException
            throw new UsernameNotFoundException("", e);
        }
        if (user == null) {
            throw new UsernameNotFoundException("");
        } else {
            if (!user.getEnabled()) {
                throw new BadRequestException("账号未激活");
            }
            return new JwtUserDTO(
                    user,
                    roleService.mapToGrantedAuthorities(user)
            );
        }
    }
}
