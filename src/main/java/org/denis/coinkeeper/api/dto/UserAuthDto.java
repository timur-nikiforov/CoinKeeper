package org.denis.coinkeeper.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

@Data
@Builder
@Component
@AllArgsConstructor
@NoArgsConstructor
public class UserAuthDto {

    @NotNull
    @Email(message = "bad email")
    private String email;

    @NotNull
    @Length(min = 6,max = 30,message = "length password must be more 6 symbols and less 30 symbols")
    private String password;
}
