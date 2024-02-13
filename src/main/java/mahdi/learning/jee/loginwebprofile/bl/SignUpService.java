package mahdi.learning.jee.loginwebprofile.bl;

import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import mahdi.learning.jee.loginwebprofile.dal.ProfileDao;
import mahdi.learning.jee.loginwebprofile.dal.UserDao;
import mahdi.learning.jee.loginwebprofile.dto.ProfileDto;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;
import mahdi.learning.jee.loginwebprofile.entity.MyEntityManager;
import mahdi.learning.jee.loginwebprofile.entity.Profile;
import mahdi.learning.jee.loginwebprofile.entity.Users;

public class SignUpService {
    @Inject
    private UserDao userDao;
    @Inject
    private ProfileDao profileDao;

    public void add(ProfileDto profileDto, UserDto userDto) {
        EntityManager entityManager = MyEntityManager.getEntityManager();

        entityManager.getTransaction().begin();

        Profile profile = entityManager.merge(
                Profile.builder()
                        .name(profileDto.getName())
                        .lastname(profileDto.getLastname())
                        .birthday(profileDto.getBirthday())
                        .build()
        );
        entityManager.getTransaction().commit();

        entityManager.getTransaction().begin();
        Users user = entityManager.merge(Users.builder().username(userDto.getUsername())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .profile(profile)
                .build());
        entityManager.getTransaction().commit();
    }
}
