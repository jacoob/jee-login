package mahdi.learning.jee.loginwebprofile.controller;

import lombok.Data;
import mahdi.learning.jee.loginwebprofile.dto.Dto;

public interface IC {
    public MyBaseResponse service(Dto dto);

}
