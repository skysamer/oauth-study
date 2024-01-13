package com.salary.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void saveRefreshToken(JwtToken jwtToken){
        refreshTokenRepository.save(new RefreshToken(jwtToken.getRefreshToken(), jwtToken.getKey()));
    }

    public String getRefreshToken(String token){
        RefreshToken refreshToken = refreshTokenRepository.findRefreshTokenByRefreshToken(token).orElse(null);
        if(refreshToken == null){
            return "";
        }
        return jwtTokenProvider.createAccessToken(refreshToken.getSub());
    }
}
