package com.ds.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ds.persist.domain.User;
import com.ds.persist.repositories.UserRepo;
import com.ds.security.bean.DsUserDetails;

@Component
public class DsUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepo userRepo;
	
	public UserDetails loadUserByUsername(String userEmail)
			throws UsernameNotFoundException {
		User user = userRepo.findByUserEmail(userEmail);
		UserDetails userDetails = null;
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Cannot find user by userEmail [%s]", userEmail));
		}
		
		String password = user.getPassword();
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("USER"));
		userDetails = new DsUserDetails(userEmail, password, enabled,
				accountNonExpired, credentialsNonExpired,
				accountNonLocked,
				authorities, user);
		return userDetails;
	}

}
