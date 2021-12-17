package my.projects.exception;

public class NoProductException extends RuntimeException {

    private String title;

    public NoProductException(String title) {
        this.title = title;
    }

    @Override
    public String getMessage() {
        return "Product '" + title + "' doesn't exist";
    }
}
