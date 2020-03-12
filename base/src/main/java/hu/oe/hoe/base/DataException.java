package hu.oe.hoe.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author javaee
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Adat konfliktus." )
public class DataException extends RuntimeException{
    
}
