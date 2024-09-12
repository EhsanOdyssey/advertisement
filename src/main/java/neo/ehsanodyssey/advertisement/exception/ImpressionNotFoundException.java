package neo.ehsanodyssey.advertisement.exception;

import org.springframework.http.HttpStatus;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public class ImpressionNotFoundException extends ServiceException {

    public ImpressionNotFoundException(String message) {
        super(message);
    }

    public ImpressionNotFoundException(Throwable cause) {
        super(cause);
    }

    public ImpressionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
