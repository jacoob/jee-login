package mahdi.learning.jee.loginwebprofile.bl;


import jakarta.inject.Inject;
import mahdi.learning.jee.loginwebprofile.dal.ProfileDao;
import mahdi.learning.jee.loginwebprofile.dto.ProfileDto;

public class ProfileService {

    @Inject
    private ProfileDao profileDao;

    public void addProfile(ProfileDto profileDto) {
        profileDao.addProfile(profileDto);
    }
}
