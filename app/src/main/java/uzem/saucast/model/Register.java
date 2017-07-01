package uzem.saucast.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Register implements Serializable {
	
	@SerializedName("WebinarId")
    private  int WebinarId ;
 
 	@SerializedName("Email")
    private  String Email ;
 	
 	@SerializedName("Name")
    private  String Name ;
 
 	@SerializedName("Surname")
    private  String Surname ;
 	
 	public int getWebinarId() {
        return WebinarId;
    }
    public void setWebinarId(int webinarId) {
    	WebinarId = webinarId;
    }
 	public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
    	Email = email;
    }
    
    public String getName() {
        return Name;
    }
    public void setName(String name) {
    	Name = name;
    }
 	public String getSurname() {
        return Surname;
    }
    public void setSurname(String surname) {
    	Surname = surname;
    }
}
