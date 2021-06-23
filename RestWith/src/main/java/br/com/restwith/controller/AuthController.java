package br.com.restwith.controller;

import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.restwith.repository.UserRepository;
import br.com.restwith.security.AccountCredentialsVo;
import br.com.restwith.security.jwt.JwtTokenProvider;
import io.swagger.annotations.ApiOperation;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	
	@ApiOperation(value = "Auth")
	@PostMapping( value = "/signin", produces = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity sigin(@RequestBody  AccountCredentialsVo data) {
		
		try {
			var username = data.getUsername();
			var password = data.getPassword();
			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
			var user = userRepository.findByUsername(username);
			var token = "";
			
			if(user != null) {
				token = jwtTokenProvider.createToken(username, user.getRoles());
			}else {
				throw new UsernameNotFoundException("Nome erro " + username);
			}
			
			Map<Object, Object> model = new HashedMap();
			model.put("username", username);
			model.put("token", token);
			
		return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("invaldi usename-password supplied");
		}
	}
	 
}
