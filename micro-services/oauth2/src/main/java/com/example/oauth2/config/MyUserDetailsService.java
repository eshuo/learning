package com.example.oauth2.config;

import com.example.oauth2.entity.User;
import com.example.oauth2.server.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @Description
 * @Author eshuo
 * @Date 2022-11-09 14:40
 * @Version V1.0
 */
@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserService userService;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     *
     * @return a fully populated user record (never <code>null</code>)
     *
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User authInfo = userService.findByUser(username);
        if (null != authInfo) {
            return new org.springframework.security.core.userdetails.User(authInfo.getUsername(), authInfo.getPassword(), Arrays.asList(new SimpleGrantedAuthority("ADMIN"),
                    new SimpleGrantedAuthority("USER")));
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }
}
