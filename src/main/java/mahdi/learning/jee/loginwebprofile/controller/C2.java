package mahdi.learning.jee.loginwebprofile.controller;

import mahdi.learning.jee.loginwebprofile.dto.Dto;
import mahdi.learning.jee.loginwebprofile.framwork.MyController;

@MyController("/c2")
public class C2 implements IC{
    @Override
    public MyBaseResponse service(Dto dto) {
        return MyBaseResponse.builder().data("c2").build();
    }
}
