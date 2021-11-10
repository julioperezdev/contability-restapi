package dev.julioperez.contability.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorOccurredWhenSendingEmailException extends RuntimeException{

    Logger logger = LoggerFactory.getLogger(ErrorOccurredWhenSendingEmailException.class);

    public ErrorOccurredWhenSendingEmailException(String personToSend){
        logger.error("Exception occurred when sending mail to ".concat(personToSend));
    }
}
