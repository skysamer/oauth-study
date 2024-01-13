package com.salary.users.dto;

import com.salary.users.entity.Users;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserInfoDto {
    private Long id;
    private String name;
    private String email;
    private String sub;
    private String provider;

    public static UserInfoDto toDto(Users users){
        return UserInfoDto.builder()
                .email(users.getEmail())
                .id(users.getId())
                .name(users.getName())
                .sub(users.getSub())
                .provider(users.getProvider().getValue())
                .build();
    }
}
