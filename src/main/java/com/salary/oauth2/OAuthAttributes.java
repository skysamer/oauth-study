package com.salary.oauth2;

import com.salary.global.util.RandomStringBuilder;
import com.salary.users.entity.Provider;
import com.salary.users.entity.Role;
import com.salary.users.entity.Users;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Locale;
import java.util.Map;

@Getter
@Builder
@EqualsAndHashCode(of = "sub")
@ToString(exclude = {"attributes", "nameAttributeKey"})
public class OAuthAttributes {
    private Map<String, Object> attributes;
    private String nameAttributeKey;
    private String name;
    private String sub;
    private String email;
    private String provider;

    public static OAuthAttributes of(String provider, String userNameAttributeName, Map<String, Object> attributes){
        if("naver".equals(provider)){
            return ofNaver(provider, userNameAttributeName, attributes);
        }else if("kakao".equals(provider)){
            return ofKakao(provider, userNameAttributeName, attributes);
        }
        return ofGoogle(provider, userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofGoogle(String provider, String userNameAttributeName, Map<String, Object> attributes){
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .sub((String) attributes.get("sub"))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofNaver(String provider, String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        return OAuthAttributes.builder()
                .name((String) response.get("name"))
                .email((String) response.get("email"))
                .sub(String.valueOf(attributes.get("response")))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    private static OAuthAttributes ofKakao(String provider, String userNameAttributeName, Map<String, Object> attributes){
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        return OAuthAttributes.builder()
                .name((String) properties.get("nickname"))
                .email((String) account.get("email"))
                .sub(String.valueOf(attributes.get("id")))
                .provider(provider)
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public Users toEntity(){
        name = name + makeNameIdentifier();
        return Users.builder()
                .name(name)
                .email(email)
                .sub(sub)
                .provider(Provider.valueOf(provider.toUpperCase(Locale.ROOT)))
                .role(Role.USER)
                .build();
    }

    private String makeNameIdentifier(){
        String identifier = "#";
        return identifier + RandomStringBuilder.generateRandomString(5);
    }
}
