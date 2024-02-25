package mahdi.learning.jee.loginwebprofile.bl;

import jakarta.inject.Inject;
import mahdi.learning.jee.loginwebprofile.dal.ScopeDao;
import mahdi.learning.jee.loginwebprofile.dto.ScopeDto;

import java.util.List;

public class ScopeService {

    @Inject
    private ScopeDao scopeDao;
    public void addScope(ScopeDto dto) {
        scopeDao.insertScope(dto);
    }

    public List<ScopeDto> findScopeByUserName(ScopeDto scopeDto){
        return scopeDao.getScopeByUsername(scopeDto);
    }
}
