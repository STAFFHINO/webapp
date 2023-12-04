package storage.dto;

import lombok.Builder;
import lombok.Data;

import java.sql.Date;

@Data
@Builder
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Date birthdate;
}
