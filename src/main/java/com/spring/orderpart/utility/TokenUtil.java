package com.spring.orderpart.utility;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Verification;
import org.springframework.stereotype.Component;
@Component
public class TokenUtil {
    private static final String TOKEN_SECRET = "Gate Keeper";

    public String createToken(long id) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            String token = JWT.create().withClaim("user_id", id).sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return null;
    }
    public long decodeToken(String token)
    {
        long userid;
        Verification verification = null;
        try
        {
            verification = JWT.require(Algorithm.HMAC256(TOKEN_SECRET));
        }
        catch (IllegalArgumentException  e)
        {
            e.printStackTrace();
        }
        JWTVerifier jwtverifier = verification.build();
        DecodedJWT decodedjwt=jwtverifier.verify(token);
        Claim claim=decodedjwt.getClaim("user_id");
        userid=claim.asInt();
        return userid;
    }
}
