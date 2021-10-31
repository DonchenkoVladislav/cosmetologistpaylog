package com.kosmetologistpaycalc.paycalc.Services;

import com.kosmetologistpaycalc.paycalc.Models.Role;
import com.kosmetologistpaycalc.paycalc.Models.User;
import com.kosmetologistpaycalc.paycalc.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByUsername (String userName){
        return userRepository.findByUserName(userName);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername (String userName) throws UsernameNotFoundException{
        User user = findByUsername(userName);
        if (user == null){
            throw new UsernameNotFoundException(String.format("Пользователя '%s' не существует", userName));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), maproletoauthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> maproletoauthorities(Collection<Role> roles){
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}
