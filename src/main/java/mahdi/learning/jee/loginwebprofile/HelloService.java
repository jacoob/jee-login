package mahdi.learning.jee.loginwebprofile;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Default;

//@RequestScoped
//@Default
public class HelloService {
    public String say(){
        return "say";
    }
}
