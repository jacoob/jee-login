package mahdi.learning.jee.loginwebprofile.dal;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import mahdi.learning.jee.loginwebprofile.dto.UserDto;
import mahdi.learning.jee.loginwebprofile.entity.MyEntityManager;
import mahdi.learning.jee.loginwebprofile.entity.Users;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserDao {


    public void insert(UserDto userDto){
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityManager entityManager = MyEntityManager.getEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(Users.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build());
        entityManager.getTransaction().commit();
//        entityManager.close();
//        entityManagerFactory.close();

    }

    public UserDto getByUsernamAndEmail(UserDto userDto) {
        EntityManager entityManager = MyEntityManager.getEntityManager();
        TypedQuery<Users> query = entityManager.createQuery(
                "SELECT u FROM Users u WHERE u.username = :username AND u.email = :email" , Users.class);
         Users users1 = query
                .setParameter("username", userDto.getUsername())
                .setParameter("email", userDto.getEmail())
                .getSingleResult();
                return UserDto.builder().password(users1.getPassword()).email(users1.getEmail()).build();

    }
}
