package mahdi.learning.jee.loginwebprofile.bl;


import jakarta.inject.Inject;
import mahdi.learning.jee.loginwebprofile.dal.UserDao;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;
import mahdi.learning.jee.loginwebprofile.security.UtilSecurity;

//@ApplicationScoped
public class UserService {
    @Inject
    private UserDao userDao ;

    @Inject
    private UtilSecurity security;

    public UserDto addUser(UserDto userDto){
        userDao.insert(userDto);
        return userDto;
    }

    public UserDto getUserByEmailUsername(UserDto userDto) {
        try {
           userDto =  userDao.getByUsernamAndEmail(userDto);
            userDto.setPassword(security.decrypt(userDto.getPassword(),userDto.getTxtSecurityKey()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userDto;
    }

    public UserDto getUserByUsernamePassword(UserDto userDto) {
        try {
            UserDto userDto1 = userDao.getByUserName(userDto.getUsername());
            String pass = security.decrypt(userDto1.getPassword(),userDto1.getTxtSecurityKey());
            if(pass.equals(userDto.getPassword())){
                return userDto;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("error for login!!");
    }


}
