package mahdi.learning.jee.loginwebprofile.dal;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;
import mahdi.learning.jee.loginwebprofile.entity.MyEntityManager;
import mahdi.learning.jee.loginwebprofile.entity.UserScope;
import mahdi.learning.jee.loginwebprofile.entity.Users;

import java.util.List;

public class UserScopeDao {

    public List<UserScope> getScopesByUserId(Long userId) {
        EntityManager entityManager = MyEntityManager.getEntityManager();
        TypedQuery<UserScope> query = entityManager.createQuery(
                "SELECT us FROM UserScope us WHERE us.users.userId = :userId" , UserScope.class);
        return query
                .setParameter("userId", userId)
                .getResultList();
    }

    public List<UserScope> getScopesByUserIdAndScopeId(Long userId,Long scopeId) {
        EntityManager entityManager = MyEntityManager.getEntityManager();
        TypedQuery<UserScope> query = entityManager.createQuery(
                "SELECT us FROM UserScope us WHERE us.users.userId = :userId and us.scope.id= :scopeId" , UserScope.class);
        return query
                .setParameter("userId", userId)
                .setParameter("scopeId", scopeId)
                .getResultList();
    }
}
