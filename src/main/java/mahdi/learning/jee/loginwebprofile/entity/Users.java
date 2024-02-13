package mahdi.learning.jee.loginwebprofile.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
@Builder
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private String email;
//    @OneToOne(targetEntity = Users.class )
//    @OneToOne(targetEntity = Users.class , mappedBy = "profile")
//    @JoinColumn(name = "profileid")

    /*@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Profile profile;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", referencedColumnName = "profileId")
    private Profile profile;
}
