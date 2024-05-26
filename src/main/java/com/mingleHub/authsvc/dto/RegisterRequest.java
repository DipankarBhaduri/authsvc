package com.mingleHub.authsvc.dto;

import com.mingleHub.authsvc.constants.Role;
import lombok.Data;
import lombok.experimental.Accessors;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@Accessors(chain = true)
public class RegisterRequest {
    private String firstName;
    private String lastName;

    @NotNull
    private String email;
    private String password;
    private Role role;
}
