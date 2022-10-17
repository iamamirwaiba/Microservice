package com.treeleaf.authentication.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JwtRedisRepo {


    public static final String HASH_KEY="jwt";

    @Qualifier("redis")
    private RedisTemplate<String,Object> redisTemplate;


    @Autowired
    JwtRedisRepo(RedisTemplate redisTemplate){
        this.redisTemplate=redisTemplate;
    }

    public JwtRedisToken save(JwtRedisToken token){
        redisTemplate.opsForHash().put(HASH_KEY,token.getKey(),token);
        return token;
    }

    public List<JwtRedisToken> findAll(){
        return (List) redisTemplate.opsForHash().values(HASH_KEY);

    }


    public JwtRedisToken findByKey(String key){
        return (JwtRedisToken) redisTemplate.opsForHash().get(HASH_KEY,key);
    }

    public Map<String,JwtRedisToken> deleteByKey(String key){
        Map<String,JwtRedisToken> tokenMap=new HashMap<>();
        try {
            JwtRedisToken token=(JwtRedisToken) redisTemplate.opsForHash().get(HASH_KEY,key);
            redisTemplate.opsForHash().delete(HASH_KEY, key);
            tokenMap.put("tokenValue",token);
            return tokenMap;
        }
        catch (Exception exception){
            tokenMap.put("tokenValue",new JwtRedisToken());
            return tokenMap;
        }

    }




}
