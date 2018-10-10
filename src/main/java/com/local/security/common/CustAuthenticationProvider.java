package com.local.security.common;

import com.local.security.entity.UserEntity;
import com.local.security.exception.UsernameNotfoundException;
import com.local.security.service.LoginServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * 自定义登陆验证
 */
@Slf4j
@Component
public class CustAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private LoginServiceImpl loginService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String pwd = (String) authentication.getCredentials();
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
		UserEntity userEntity = (UserEntity) loginService.loadUserByUsername(username);
		log.info("name"+username+",pwd"+pwd+",details"+details.getRemoteAddress()+details.getSessionId());
		if (!passwordEncoder.matches(pwd, userEntity.getPwd())) {
			log.error("用户登陆失败:密码错误!" + pwd);
			throw new UsernameNotfoundException("用户登陆失败:密码错误!");
		}
		return new UsernamePasswordAuthenticationToken(username, pwd, userEntity.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> aClass) {
		//return aClass.equals(UsernamePasswordAuthenticationToken.class);
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(aClass);
	}
}