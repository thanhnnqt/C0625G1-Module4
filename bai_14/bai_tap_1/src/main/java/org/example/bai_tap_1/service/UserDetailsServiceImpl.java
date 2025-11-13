package org.example.bai_tap_1.service;

import lombok.RequiredArgsConstructor;
import org.example.bai_tap_1.entity.AppUser;
import org.example.bai_tap_1.entity.UserRole;
import org.example.bai_tap_1.repository.AppUserRepository;
import org.example.bai_tap_1.repository.UserRoleResposiotry;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    private final UserRoleResposiotry userRoleResposiotry;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository, UserRoleResposiotry userRoleResposiotry) {
        this.appUserRepository = appUserRepository;
        this.userRoleResposiotry = userRoleResposiotry;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.appUserRepository.findByUserName(userName);
        if (appUser == null) {
            System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
        }
        System.out.println("Found User: " + appUser);
        // lấy ra các role của user
        // [ROLE_USER, ROLE_ADMIN,..]
        List<UserRole> userRoles = this.userRoleResposiotry.findByAppUser(appUser);

        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (userRoles != null) {
            for (UserRole userRole : userRoles) {
                // ROLE_USER, ROLE_ADMIN,..
                GrantedAuthority authority = new SimpleGrantedAuthority(userRole.getAppRole().getRoleName());
                grantList.add(authority);
            }
        }
        UserDetails userDetails = new User(appUser.getUserName(),
                appUser.getEncrytedPassword(), grantList);

        return userDetails;
    }

}
