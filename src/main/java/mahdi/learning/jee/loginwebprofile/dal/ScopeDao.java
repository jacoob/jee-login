package mahdi.learning.jee.loginwebprofile.dal;

import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import mahdi.learning.jee.loginwebprofile.dto.ScopeDto;
import mahdi.learning.jee.loginwebprofile.entity.MyEntityManager;
import mahdi.learning.jee.loginwebprofile.entity.Scope;

import java.util.ArrayList;
import java.util.List;

public class ScopeDao {
    public void insertScope(ScopeDto dto){
        MyEntityManager.getEntityManager().persist(Scope.builder().user(dto.getUser()).name(dto.getName()));

    }

    public List<ScopeDto> getScopeByUsername(ScopeDto dto){
        Query query = MyEntityManager.getEntityManager().createQuery("SELECT S from Scope s where s.user.username:?").setParameter(1,dto.getUser().getUsername());
        List<Scope> scopes = query.getResultList();
        List<ScopeDto> scopeDtos = new ArrayList<>();
        scopes.stream().forEach(p->scopeDtos.add(ScopeDto.builder().id(p.getId()).user(p.getUser()).name(p.getName()).build()));
        return scopeDtos;
    }
}
