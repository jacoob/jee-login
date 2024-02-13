package mahdi.learning.jee.loginwebprofile.dal;

import jakarta.persistence.EntityManager;
import mahdi.learning.jee.loginwebprofile.dto.ProfileDto;
import mahdi.learning.jee.loginwebprofile.entity.MyEntityManager;
import mahdi.learning.jee.loginwebprofile.entity.Profile;

public class ProfileDao {

    public void addProfile(ProfileDto profileDto) {
        EntityManager entityManager = MyEntityManager.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(
                Profile.builder()
                        .profileId(profileDto.getId())
                        .birthday(profileDto.getBirthday())
                        .name(profileDto.getName())
                        .lastname(profileDto.getLastname())
                        .build());
        entityManager.getTransaction().commit();
    }
}
