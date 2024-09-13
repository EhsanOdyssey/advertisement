package neo.ehsanodyssey.advertisement.exception;

import org.springframework.http.HttpStatus;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-13 Sep/Fri
 **/
public class CheckPerformanceException extends ServiceException {

    public CheckPerformanceException(String message) {
        super(message);
    }

    public CheckPerformanceException(Throwable cause) {
        super(cause);
    }

    public CheckPerformanceException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
