package com.example.registrationlogindemo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserRoleRequestDto {
    private List<Long> userIds;
}
