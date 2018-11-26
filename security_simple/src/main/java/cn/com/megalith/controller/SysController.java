package cn.com.megalith.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @Description:
 * @author: zhoum
 * @Date: 2018-11-23
 * @Time: 13:47
 */
@RestController
@RequestMapping("/sys")
public class SysController {

    @GetMapping("/permisson")
    public String listPermissons(Principal principal) {

        System.out.println("999");
        return "666";
    }
}
