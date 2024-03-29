package com.accendl.web.security.customuser;

import com.accendl.web.service.AccountService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AccountService accountService;

    public CustomUserDetailsService(AccountService accountService) {
        Assert.notNull(accountService, "AccountService must not be null!");
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            CustomUser customUser = accountService.findCustomUserByEmail(username);
            if (customUser == null) {
                throw new UsernameNotFoundException("用户名 " + username + " 没有找到");
            }
            return new CustomUserDetails(customUser);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    static final class CustomUserDetails extends CustomUser implements UserDetails{
        private static final List<GrantedAuthority> ROLE_USER = Collections
                .unmodifiableList(AuthorityUtils.createAuthorityList("ROLE_USER"));

        public CustomUserDetails(CustomUser customUser) {
            super(customUser);
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return ROLE_USER;
        }

        @Override
        public String getUsername() {
            return getEmail();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

    }
}
