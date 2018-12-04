package beans;

import javax.ejb.Local;

@Local(Response.class)
public class ResponseModel implements Response {
    
    private int status;
    private String message;

    public ResponseModel(int status, String message)
    {
        this.status=status;
        this.message=message;
    }
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

	@Override
	public String toString() {
		return "ResponseModel [status=" + status + ", message=" + message + "]";
	}
    
}