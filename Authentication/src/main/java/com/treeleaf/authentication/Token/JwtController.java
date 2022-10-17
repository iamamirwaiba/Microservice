package com.treeleaf.authentication.Token;

import com.treeleaf.authentication.Util.JwtUtil;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/jwt")
public class JwtController {

    private JwtUtil jwtUtil;
    private UserDetailsService service;
    
    private JwtRedisRepo jwtRedisRepo;

    final Logger logger =
            LoggerFactory.getLogger(JwtController.class);


    @Autowired
    public JwtController(JwtUtil jwtUtil,UserDetailsService service,JwtRedisRepo jwtRedisRepo){
        this.jwtUtil=jwtUtil;
        this.service=service;
        this.jwtRedisRepo=jwtRedisRepo;
    }

    @RequestMapping("/validate")
    public @ResponseBody Map<String, String> validateJwt(HttpServletRequest request, HttpServletResponse httpServletResponse){
        String bearerToken=request.getHeader("Authorization");
        String token=bearerToken.substring(7,bearerToken.length());
        String newToken;
        Map<String,String> response=new HashMap<>();

        if(jwtUtil.isTokenExpired(token)){
            JwtRedisToken refreshToken=jwtRedisRepo.findByKey(token);
            if(!jwtUtil.isTokenExpired(refreshToken.getValue())){
                jwtRedisRepo.deleteByKey(token);
                token=jwtUtil.generateToken(jwtUtil.extractUsername(refreshToken.getValue()),new Date(System.currentTimeMillis()+1000 * 60 *1));
                jwtRedisRepo.deleteByKey(jwtUtil.extractUsername(token));
                jwtRedisRepo.save(new JwtRedisToken(jwtUtil.extractUsername(token),token));
                jwtRedisRepo.save(new JwtRedisToken(token,refreshToken.getValue()));
                response.put("token",token);
            }
        }
        try {
            String email = jwtUtil.extractUsername(token);
            UserDetails appUser=service.loadUserByUsername(email);
            if(jwtUtil.validateToken(token,appUser)){
                JwtRedisToken redisToken=jwtRedisRepo.findByKey(email);
                if(redisToken.getValue().equals(token)&&redisToken.getKey().equals(email)){
                    response.put("message","true");
                    response.put("status","Valid Token");
                }
                else {
                    response.put("message", "false");
                    response.put("status", "Token and User Mismatch");
                }
                return response;
            }
            else{
                response.put("status","false");
                response.put("message","Token Invalid");
            }
            return response;
            
        }
        catch (Exception e){
            logger.info("Invalid JWT");
            response.put("message","Invalid JWT");
            response.put("status","false");
            System.out.println(new JSONObject(response));
            System.out.println("/////");
            return response;
        }
    }

}
