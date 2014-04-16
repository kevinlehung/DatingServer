package com.ds.security.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class DsUserDetails extends User {
	private static final long serialVersionUID = -3614724270308267823L;
	
	private com.ds.persist.domain.User dsUser;
	
	public DsUserDetails(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, com.ds.persist.domain.User dsUser) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);
		this.dsUser = dsUser;
	}

	public com.ds.persist.domain.User getDsUser() {
		return dsUser;
	}

	public void setDsUser(com.ds.persist.domain.User dsUser) {
		this.dsUser = dsUser;
	}
	
	

}
