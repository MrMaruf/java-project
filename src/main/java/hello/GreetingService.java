package hello;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    private String username = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title = null;
    public String greet() {
        String msg = "Hello my dear, lovely, World!";

        if(username != null)
            msg += ("\nYou entered under following username: " + this.username);
        if(title != null)
            msg += ("\nYour title: " + this.title);
        return msg;
    }
    public void resetData(){
        this.setUsername(null);
        this.setTitle(null);
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}