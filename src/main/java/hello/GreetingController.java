package hello;

import org.joda.time.LocalTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/greeting")
public class GreetingController {

    private final GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping("/")
    public @ResponseBody String greeting() {
        LocalTime currentTime = new LocalTime();
        GreetingService greeter = new GreetingService();
        String msg = "The current local time is: " + currentTime;
        msg += "|| \n" + service.greet();

        return msg;
    }
    @PostMapping("/user")
    String newUsername(@RequestBody String newUsername) {
        service.setUsername(newUsername);
        return service.getUsername();
    }
    @PutMapping("/user/{username}")
    String newTitle(@RequestBody String newTitle, @PathVariable String username) throws Exception {
        if(service.getUsername().equals(username)) {
            service.setTitle(newTitle);
            return service.getTitle();
        }
        else
            throw new Exception("Not found");
    }
    @DeleteMapping("/user/{username}")
    void deleteUserData(@PathVariable String username ) {
        if(service.getUsername().equals(username))
        {
            service.setUsername(null);
            service.setTitle(null);
        }

    }

}
