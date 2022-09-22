package com.treeleaf.authentication.AppUser;

import com.treeleaf.authentication.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AppUserServiceImpl implements AppUserService{

    private  AppUserRepo appUserRepo;

    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;


    @Autowired
    AppUserServiceImpl(AppUserRepo appUserRepo, UserDetailsService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder,AuthenticationManager authenticationManager,JwtUtil jwtUtil){
        this.appUserRepo=appUserRepo;
        this.userDetailsService=userDetailsService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    @Override
    public ResponseEntity<Map<String, String>> signUp(AppUser appUser) throws Exception {

        Map<String,String> map=new HashMap<>();
        String encodedPassword=bCryptPasswordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodedPassword);
        try {
            appUserRepo.save(appUser);
            map.put("message","Sign Up successful");
        }
        catch (Exception e){
            throw new Exception("Failed To add User");
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> signIn(AppUser appUser) throws Exception {

        Map<String,String> frs=new HashMap<>();
        UserDetails appUser1= userDetailsService.loadUserByUsername(appUser.getEmail());
        AppUser appUser2=appUserRepo.findAppUserByEmail(appUser.getEmail()).get();
        frs.put("email",appUser2.getEmail());
        frs.put("id",appUser2.getId()+"");
        frs.put("fullName",appUser2.getFullName());

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
            frs.put("message","Login Successful");
        }

        catch (Exception ex){
            throw new Exception("Username or password Invalid");
        }
        String token=jwtUtil.generateToken(appUser.getUsername());
        frs.put("token",token);
        return new ResponseEntity<>(frs,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<String, String>> getAllUser() throws Exception {
        Map<String,String> userMap=new HashMap<>();
        List<AppUser> users=appUserRepo.findAll();
        for(AppUser u:users){
            userMap.put("Name",u.getFullName());
        }

        return new ResponseEntity<>(userMap,HttpStatus.OK);
    }

}
