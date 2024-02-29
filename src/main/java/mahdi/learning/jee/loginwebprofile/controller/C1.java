package mahdi.learning.jee.loginwebprofile.controller;

import mahdi.learning.jee.loginwebprofile.dto.Dto;
import mahdi.learning.jee.loginwebprofile.framwork.MyController;

@MyController("/c1")
public class C1 implements IC{
    @Override
    public MyBaseResponse service(Dto dto) {
        return MyBaseResponse.builder().data("c1").build();
    }
}
