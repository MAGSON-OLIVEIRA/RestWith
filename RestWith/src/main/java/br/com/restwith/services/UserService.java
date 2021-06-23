package br.com.restwith.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.restwith.repository.UserRepository;

import java.util.Optional;

@Service   // spring cuide da injeção de dependecia dessa classe usando o autorad
public class UserService implements UserDetailsService {
	
	@Autowired
	private UserRepository repsitory;

	public UserService(UserRepository repsitory) {
		this.repsitory = repsitory;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return Optional.of(this.repsitory.findByUsername(username))
				.orElseThrow(() -> new UsernameNotFoundException(username+" not found"));

/*		var user = this.repsitory.findByUsername(username);
		if(user != null) {
			return user;
		}else {
			throw new UsernameNotFoundException(username+" not found");
		}*/
	}
	
	
		
}
