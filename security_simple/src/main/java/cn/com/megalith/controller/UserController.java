package cn.com.megalith.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.Role;
import java.security.Principal;

/**
 * @Description:
 * @author: zhoum
 * @Date: 2018-11-23
 * @Time: 10:45
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/current")
    @Secured("ROLE_AA")
    public String getCurrentUser(Principal principal){
        System.out.println(principal);
        return "111";
    }
}
