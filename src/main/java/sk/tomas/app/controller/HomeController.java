package sk.tomas.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sk.tomas.app.bo.Key;
import sk.tomas.app.service.IdentityService;

import java.util.List;

/**
 * Created by tomas on 23.12.2016.
 */
@RestController
public class HomeController {

    @Autowired
    private IdentityService identityService;

    @RequestMapping("/")
    List<Key> home() {
        return identityService.getKeys();
    }


}
