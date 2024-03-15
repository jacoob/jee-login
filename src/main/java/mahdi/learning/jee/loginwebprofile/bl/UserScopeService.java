package mahdi.learning.jee.loginwebprofile.bl;

import jakarta.inject.Inject;
import mahdi.learning.jee.loginwebprofile.dal.UserScopeDao;
import mahdi.learning.jee.loginwebprofile.entity.UserScope;

import java.util.List;

public class UserScopeService {

    @Inject
    private UserScopeDao userScopeDao;

    public List<UserScope> getScopesByUserId(Long userId){
        return userScopeDao.getScopesByUserId(userId);
    }


    public Boolean isAccessScopesUserIdToScopeId(Long userId,Long scopeId){
        return userScopeDao.getScopesByUserIdAndScopeId(userId,scopeId).size()>0?true:false;
    }

}
