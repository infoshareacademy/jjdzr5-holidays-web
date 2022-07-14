package org.isa.holidaysweb.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.UUID;


public class User {

    @Transient
    @Size(min = 8, max = 25, message = "These passwords don't match.")
    @NotBlank(message = "This field is required")
    private String passwordConfirm;

    @Size(min = 4, max = 32, message = "Please use between 6 and 32 characters.")
    @NotBlank(message = "This field is required")
    private String userName;

    @Size(min = 8, max = 25, message = "Please use between 8 and 25 characters.")
    @NotBlank(message = "This field is required")
    private String password;


    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return Objects.equals(userName, that.userName) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password);
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
