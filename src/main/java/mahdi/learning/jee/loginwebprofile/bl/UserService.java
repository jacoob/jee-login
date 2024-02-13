package mahdi.learning.jee.loginwebprofile.bl;


import jakarta.inject.Inject;
import mahdi.learning.jee.loginwebprofile.dal.UserDao;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;

//@ApplicationScoped
public class UserService {
    @Inject
    private UserDao userDao ;

    public UserDto addUser(UserDto userDto){
        userDao.insert(userDto);
        return userDto;
    }

    public UserDto getUserByEmailUsername(UserDto userDto) {
        return userDao.getByUsernamAndEmail(userDto);
    }
}
