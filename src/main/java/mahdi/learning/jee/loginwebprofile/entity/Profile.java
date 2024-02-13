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
public class Profile {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long profileId;
    private String name;
    private String lastname;
    private String birthday;

//    @OneToOne(targetEntity = Profile.class, mappedBy = "user")
//    @OneToOne(targetEntity = Users.class)
//    @JoinColumn(name = "userid")
    @OneToOne(mappedBy = "profile")
    private Users user;
}
