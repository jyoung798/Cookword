package jyp.cooksite.config;

import java.util.Base64;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jyp.cooksite.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider { //jwt�� ���� �ϰ� �����ϴ� ����Դϴ�.

	@Value("spring.jwt.secret")
	private String secretKey;
	
	private Long tokenValidMilisecond = 1000L *60 * 60 ; //��ū �Ⱓ 60�� ��ȿ�Ⱓ 
	
	private final UserService userService;
	
	@PostConstruct
	protected void init() {
		secretKey= Base64.getEncoder().encodeToString(secretKey.getBytes());
	}
	
	// Jwt ��ū ����
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // claim ���� �ΰ����� ������ ������ �� �ֽ��ϴ�. �̰����� ���� ������ ��ū�� ������ �� �ֽ��ϴ�.
                .setIssuedAt(now) // ��ū ��������
                .setExpiration(new Date(now.getTime() + tokenValidMilisecond)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey) // ��ȣȭ �˰���, secret�� ����
                .compact();
    }
    
    //���� ��ū�� ��ȸ�մϴ�. 
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }
    
 // Jwt ��ū���� ȸ�� ���� ���� ����
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
  
    public String resolveToken(HttpServletRequest request) {
    	


        return request.getHeader("Authorization");
    }
    
    // Jwt ��ū�� ��ȿ�� + �������� Ȯ��
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}
