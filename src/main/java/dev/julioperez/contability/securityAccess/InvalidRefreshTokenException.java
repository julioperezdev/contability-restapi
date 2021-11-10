package dev.julioperez.contability.securityAccess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidRefreshTokenException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(InvalidRefreshTokenException.class);

    public InvalidRefreshTokenException(){
        logger.error("Invalid refresh Token");
    }
}