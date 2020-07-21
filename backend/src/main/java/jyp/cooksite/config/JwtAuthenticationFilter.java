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

//Jwt�� ��ȿ�� ��ū���� �����ϱ� ���� ����. 
public class JwtAuthenticationFilter extends GenericFilterBean {
	private JwtTokenProvider jwtTokenProvider;

	// Jwt Provier ����
	public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
		this.jwtTokenProvider = jwtTokenProvider;
	}

	// Request�� ������ Jwt Token�� ��ȿ���� ����(jwtTokenProvider.validateToken)�ϴ� filter��
	// filterChain�� ����մϴ�.
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
			
		System.out.println("��ū�� �˻��մϴ�"+req.getMethod());
		if (token != null && jwtTokenProvider.validateToken(token)) {

			Authentication auth = jwtTokenProvider.getAuthentication(token);
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		filterChain.doFilter(request, response);
	}
}