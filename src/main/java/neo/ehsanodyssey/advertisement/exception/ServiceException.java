package neo.ehsanodyssey.advertisement.exception;

import org.springframework.http.HttpStatus;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public abstract class ServiceException extends RuntimeException {

    public ServiceException() {
    }

    public ServiceException(String message) {
        this(message, null);
    }

    public ServiceException(Throwable cause) {
        this(null, cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus httpStatus();
}
