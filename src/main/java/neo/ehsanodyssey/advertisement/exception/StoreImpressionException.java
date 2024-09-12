package neo.ehsanodyssey.advertisement.exception;

import org.springframework.http.HttpStatus;

/**
 * @author : EhsanOdyssey (AmirEhsan Shahmirzaloo)
 * @mailto : <a href="mailto:ehsan.shahmirzaloo@gmail.com">EhsanOdyssey</a>
 * @project : advertisement
 * @created : 2024-09-12 Sep/Thu
 **/
public class StoreImpressionException extends ServiceException {

    public StoreImpressionException(String message) {
        super(message);
    }

    public StoreImpressionException(Throwable cause) {
        super(cause);
    }

    public StoreImpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus httpStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
