package my.projects.exception;

public class InvalidPriceException extends RuntimeException{

    private String code;
    private String message;

    public InvalidPriceException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
