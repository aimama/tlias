package com.learn.project_tlias.tlias;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class TliasApplicationTests {

    @Test
    void contextLoads() {
    }

    //生成JWT令牌
    @Test
    public void genJWT() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "Tom");
        String jwt = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, "itheima")
                .setExpiration(new Date(System.currentTimeMillis() + 12 * 3600 * 1000))
                .compact();
        System.out.println(jwt);
    }

    //解析JWT令牌
    @Test
    public void testParesJWT() {
        Claims itheima = Jwts.parser()
                .setSigningKey("itheima")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNjkwOTExMDQxLCJ1c2VybmFtZSI6IlRvbSJ9.s3C84TzCUBqstKf6PRErywtLzSy_mLmnZHCFl1WhAsY")
                .getBody();
        System.out.println(itheima);
    }

}
