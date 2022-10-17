package com.treeleaf.authentication.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@RedisHash("JwtRedisToken")
public class JwtRedisToken implements Serializable {

    @Id
    private String key;

    private String value;

    public JwtRedisToken(String key,String value) {
        this.key=key;
        this.value=value;
    }
}
