package com.example.thebaohiem.model.Form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {

    @Setter
    @Getter
    @NotNull
    private String userName;

    @Setter
    @Getter
    @NotNull
    private String password;

    public String toString(){
        return "LoginForm(UserName : "+ this.userName+" , Password :+"+this.password+" ) ";
    }

}
