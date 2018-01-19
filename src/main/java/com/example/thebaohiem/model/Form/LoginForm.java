package com.example.thebaohiem.model.Form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor

public class LoginForm {

    @Setter
    @Getter
    @NotEmpty
    private String userName;

    @Setter
    @Getter
    @NotEmpty
    private String password;

}
