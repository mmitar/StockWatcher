package beans;

import javax.ejb.Local;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * implements parent Response and extends ReponseModel
 */
@Local(Response.class)
@XmlRootElement(name="Response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResponseDataModel extends ResponseModel implements Response {
	
    private Stock data;
    
    /**
     * @param status
     * @param message
     */
    public ResponseDataModel() 
    {
        super(0, "");
        this.data = null;
    }

    /**
     * @param status
     * @param message
     * @param data
     */
    public ResponseDataModel(int status, String message, Stock data) 
    {
        super(status, message);
        this.data = data;
    }

    /**
     * @return the data
     */
    public Stock getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Stock data) {
        this.data = data;
    }

	@Override
	public String toString() {
		return "ResponseDataModel [data=" + data.toString() + "]";
	}
    
}