package br.com.restwith.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.restwith.repository.UserRepository;

@Service   // spring cuide da injeção de dependecia dessa classe usando o autorad
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repsitory;

	public UserService(UserRepository repsitory) {
		this.repsitory = repsitory;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		var user = this.repsitory.findByUserName(userName);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException(user+" not found");
		}
	}
	
	
		
}
