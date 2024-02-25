package mahdi.learning.jee.loginwebprofile.dto;

import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import mahdi.learning.jee.loginwebprofile.entity.Users;

@Data
@Builder
public class ScopeDto {
    private Long id;
    private String name;
    private Users user ;
}
