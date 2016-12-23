package sk.tomas.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tomas on 23.12.2016.
 */
@RestController
public class HomeController {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }


}
