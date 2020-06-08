package pojo;

public class ErrorPojo {

    private String status;
    private String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Ошибка. \n" + message + "\n";
    }
}
