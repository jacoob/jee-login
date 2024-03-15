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
        MyEntityManager.getEntityManager().persist(Scope.builder().name(dto.getName()));

    }

    public List<ScopeDto> getScopeByName(ScopeDto dto){
        Query query = MyEntityManager.getEntityManager().createQuery("SELECT s from Scope s where s.name=:name")
                .setParameter("name",dto.getName());
        List<Scope> scopes = query.getResultList();
        List<ScopeDto> scopeDtos = new ArrayList<>();
        scopes.stream().forEach(p->scopeDtos.add(ScopeDto.builder().id(p.getId()).name(p.getName()).build()));
        return scopeDtos;
    }
}
