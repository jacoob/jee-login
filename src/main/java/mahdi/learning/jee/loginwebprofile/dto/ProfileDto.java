package mahdi.learning.jee.loginwebprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mahdi.learning.jee.loginwebprofile.entity.Users;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDto {
    private Long id;
    private String name;
    private String lastname;
    private String birthday;
    private String email;
    private Users users;
}
