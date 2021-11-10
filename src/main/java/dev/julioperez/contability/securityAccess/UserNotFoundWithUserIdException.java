package dev.julioperez.contability.securityAccess;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserNotFoundWithUserIdException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(UserNotFoundWithUserIdException.class);

    public UserNotFoundWithUserIdException(String id){
        logger.error("User not found with user id  ".concat(id));
    }
}