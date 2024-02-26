package java12.dtoes;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String userName;
    private String fullName;
    private String bio;
    private String email;
    private String phoneNumber;
    private String profileLink;

    public UserDTO(Long id, String userName,String email,String phoneNumber) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
