package com.salary.oauth2;

import com.salary.users.entity.Provider;
import com.salary.users.entity.Users;
import com.salary.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        ClientRegistration registration = userRequest.getClientRegistration();
        String provider = registration.getRegistrationId();
        String userNameAttributeName = registration.getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuthAttributes attributes = OAuthAttributes.of(provider, userNameAttributeName, oAuth2User.getAttributes());
        Users user = registerUserInfo(attributes, provider);
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRole())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }

    private Users registerUserInfo(OAuthAttributes attributes, String provider){
        Users user = userRepository.findByEmailAndProvider(attributes.getEmail(), Provider.valueOf(provider.toUpperCase(Locale.ROOT)))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
