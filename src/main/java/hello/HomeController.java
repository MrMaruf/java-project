package hello;

import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @RequestMapping("/")
    public @ResponseBody String home() {
        LocalTime currentTime = new LocalTime();
        Greeter greeter = new Greeter();
        String msg = "The current local time is: " + currentTime;
        msg += "\n" + greeter.sayHello();

        return msg;
    }
}
