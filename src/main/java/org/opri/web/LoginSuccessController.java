package org.opri.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginSuccessController {

    private final JwtEncoder jwtEncoder;

    public LoginSuccessController(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    @GetMapping("/login/success")
    public ResponseEntity<Map<String, String>> loginSuccess() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof OAuth2User principal)) {
            return ResponseEntity.status(401).body(Map.of("error", "Unauthorized"));
        }

        String username = principal.getAttribute("email");
        List<String> roles = List.of("USER");

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .subject(username)
                .claim("roles", roles)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));

        Map<String, String> response = new HashMap<>();
        response.put("token", jwt.getTokenValue());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/token")
    public Map<String, String> getDummyToken() {
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject("dummy@example.com")
                .claim("roles", List.of("USER"))
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .build();

        Jwt jwt = jwtEncoder.encode(JwtEncoderParameters.from(claims));
        return Map.of("token", jwt.getTokenValue());
    }
}
