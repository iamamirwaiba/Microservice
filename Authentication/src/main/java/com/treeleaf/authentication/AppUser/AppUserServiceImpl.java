package com.treeleaf.authentication.AppUser;

import com.treeleaf.authentication.Token.JwtRedisRepo;
import com.treeleaf.authentication.Token.JwtRedisToken;
import com.treeleaf.authentication.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    private JwtRedisRepo jwtRedisRepo;


    @Autowired
    AppUserServiceImpl(AppUserRepo appUserRepo, UserDetailsService userDetailsService,BCryptPasswordEncoder bCryptPasswordEncoder,AuthenticationManager authenticationManager,JwtUtil jwtUtil,JwtRedisRepo jwtRedisRepo){
        this.appUserRepo=appUserRepo;
        this.userDetailsService=userDetailsService;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.authenticationManager=authenticationManager;
        this.jwtUtil=jwtUtil;
        this.jwtRedisRepo=jwtRedisRepo;
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
        AppUser appUser1;
        try {
            appUser1=appUserRepo.findAppUserByEmail(appUser.getEmail()).get();
        }
        catch (Exception ex){
            frs.put("message","Credentials not found");
            return new ResponseEntity<>(frs,HttpStatus.UNAUTHORIZED);
        }

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
            String bearerToken=jwtUtil.generateToken(appUser.getUsername(),new Date(System.currentTimeMillis() + 1000 * 60 * 1));
            String refreshToken=jwtUtil.generateToken(appUser.getUsername(),new Date(System.currentTimeMillis() + 1000 * 60 *60 *10));
            frs.put("token",bearerToken);
            frs.put("email",appUser1.getEmail());
            frs.put("id",appUser1.getId()+"");
            frs.put("fullName",appUser1.getFullName());
            frs.put("message","Login Successful");
            if(jwtRedisRepo.findByKey(appUser1.getEmail())!=null){
                JwtRedisToken redisToken=jwtRedisRepo.findByKey(appUser1.getEmail());
                jwtRedisRepo.deleteByKey(appUser1.getEmail());
                jwtRedisRepo.deleteByKey(redisToken.getValue());
            }
            jwtRedisRepo.save(new JwtRedisToken(appUser1.getEmail(),bearerToken));
            jwtRedisRepo.save(new JwtRedisToken(bearerToken,refreshToken));
        }

        catch (Exception ex){
            frs.put("message","Password or Username incorrect");
            return new ResponseEntity<>(frs,HttpStatus.UNAUTHORIZED);
        }
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
