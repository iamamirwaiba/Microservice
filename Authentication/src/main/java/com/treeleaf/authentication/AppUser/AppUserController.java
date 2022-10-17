package com.treeleaf.authentication.AppUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    AppUserController(AppUserService appUserService){
        this.appUserService=appUserService;
    }


    @PostMapping(value="/signUp",consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Map<String,String>> signUp(@RequestBody AppUser appUser) throws Exception {
        return appUserService.signUp(appUser);
    }


    @PostMapping(value="/signIn",consumes = {"application/json"})
    public @ResponseBody ResponseEntity<Map<String,String>> signIn(@RequestBody AppUser appUser) throws Exception{
        return appUserService.signIn(appUser);
    }

    @GetMapping("/getAllUsers")
    public @ResponseBody ResponseEntity<Map<String,String>> getAllUser() throws Exception {
        return appUserService.getAllUser();
    }





}
