package jyp.cooksite.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

//Jwt가 유효한 토큰인지 인증하기 위한 필터. 
public class JwtAuthenticationFilter extends GenericFilterBean {
	private JwtTokenProvider jwtTokenProvider;

	// Jwt Provier 주입
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// Request로 들어오는 Jwt Token의 유효성을 검증(jwtTokenProvider.validateToken)하는 filter를
	// filterChain에 등록합니다.
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);
		
		
		HttpServletRequest req = (HttpServletRequest)request;
		System.out.println("" + req.getHeader("Authorization"));

		Enumeration<String> headers = req.getHeaderNames();

		while (headers.hasMoreElements()) { ////////////////////////////////////////////////////////////
			String headerName = (String) headers.nextElement();
			String value = req.getHeader(headerName);
			System.out.println("headerName:" + headerName + "," + value);
		}
			
		System.out.println("토큰을 검사합니다"+req.getMethod());
		if (token != null && jwtTokenProvider.validateToken(token)) {

			Authentication auth = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
}