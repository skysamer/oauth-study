package com.salary.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/jwt/*")
@RequiredArgsConstructor
public class JwtController {
    private final JwtTokenService jwtTokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/validate-access")
    public ResponseEntity<Void> validateAccessToken(@RequestHeader("access-token") String accessToken){
        boolean isValid = jwtTokenProvider.validateToken(accessToken);
        if(isValid){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/validate-refresh")
    public ResponseEntity<Void> validateRefreshToken(@RequestHeader("refresh-token") String refreshToken){
        String accessToken = jwtTokenService.getRefreshToken(refreshToken);
        if("".equals(accessToken)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add("access-token", accessToken);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
