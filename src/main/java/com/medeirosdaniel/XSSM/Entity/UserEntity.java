package com.medeirosdaniel.XSSM.Entity;

import com.medeirosdaniel.XSSM.Enums.ProfileEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
public class UserEntity implements Serializable {

    private static final String LENGHMIN8 = "Requires a minimum of 8 characters";
    private static final long serialVersionUID = 2564224907344618399L;

    @Id
    private String id;

    @Indexed(unique = true)
    @NotBlank(message = "UserName required")
    @Size(min = 8, message = LENGHMIN8)
    private String username;

    @Size(min = 8, message = LENGHMIN8)
    private String password;

    @Indexed(unique = true)
    @Email
    @NotBlank(message = "E-Mail required")
    @Size(min = 8, message = LENGHMIN8)
    private String email;

    private String cnpj;

    @NotBlank(message = "Phone required")
    private String phone1;

    private String hashUrl;

    private Boolean isWhatsApp;

    private Boolean isTelegram;

    private String phone2;

    private ProfileEnum profile;

    private Boolean lockAccount;

    private String unlockCode;

}
