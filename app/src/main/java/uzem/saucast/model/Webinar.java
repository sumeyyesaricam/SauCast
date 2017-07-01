package uzem.saucast.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Webinar implements Serializable{
	
	 	@SerializedName("WebinarId")
	    private  int WebinarId ;
	 
	 	@SerializedName("WebinarTitle")
	    private  String WebinarTitle ;
	 	
	 	@SerializedName("Description")
	    private  String Description ;
	 
	 	@SerializedName("PresenterName")
	    private  String PresenterName ;
	 	
	 	@SerializedName("GuestName")
	    private  String GuestName ;
	 	
	 	@SerializedName("GuestMail")
	    private  String GuestMail ;
	 
	 	@SerializedName("Online")
	    private  boolean Online ;
	 	
	 	@SerializedName("Completed")
	    private  boolean Completed ;
	 	
	 	@SerializedName("Started")
	    private  boolean Started ;
	 
	 	@SerializedName("StartDate")
	    private  String StartDate ;
	 
	 	@SerializedName("StartMessage")
	    private  String StartMessage ;
	 	
	 	@SerializedName("IsPublic")
	    private  boolean IsPublic ;
	 
	 	@SerializedName("Duration")
	    private  int Duration ;
	 	
	 	@SerializedName("EnApplause")
	    private  boolean EnApplause ;
	 	
	 	@SerializedName("EnMessage")
	    private  boolean EnMessage ;
	 
	 	@SerializedName("EnLobby")
	    private  boolean EnLobby ;
	 	
	 	@SerializedName("MaxUser")
	    private  int MaxUser ;
	 	
	 	public int getWebinarId() {
	        return WebinarId;
	    }
	    public void setWebinarId(int webinarId) {
	    	WebinarId = webinarId;
	    }
	 	public String getWebinarTitle() {
	        return WebinarTitle;
	    }
	    public void setWebinarTitle(String webinarTitle) {
	    	WebinarTitle = webinarTitle;
	    }
	    
	    public String getDescription() {
	        return Description;
	    }
	    public void setDescription(String description) {
	    	Description = description;
	    }
	 	public String getPresenterName() {
	        return PresenterName;
	    }
	    public void setPresenterName(String presenterName) {
	    	PresenterName = presenterName;
	    }
	    public String getGuestName() {
	        return GuestName;
	    }
	    public void setGuestName(String guestName) {
	    	GuestName = guestName;
	    }
	    
	    public String getGuestMail() {
	        return GuestMail;
	    }
	    public void setGuestMail(String guestMail) {
	    	GuestMail = guestMail;
	    }
	 	public boolean getOnline() {
	        return Online;
	    }
	    public void setOnline(boolean online) {
	    	Online = online;
	    }
	    
	    public boolean getCompleted() {
	        return Completed;
	    }
	    public void setCompleted(boolean completed) {
	    	Completed = completed;
	    }
	    public boolean getStarted() {
	        return Online;
	    }
	    public void setStarted(boolean started) {
	    	Started = started;
	    }
	    public String getStartDate() {
	        return StartDate;
	    }
	    public void setStartDate(String startDate) {
	    	StartDate = startDate;
	    }
	 	public String getStartMessage() {
	        return StartMessage;
	    }
	    public void setStartMessage(String startMessage) {
	    	StartMessage = startMessage;
	    }
	    
	    public boolean getIsPublic() {
	        return IsPublic;
	    }
	    public void setIsPublic(boolean isPublic) {
	    	IsPublic = isPublic;
	    }
	 	public int getDuration() {
	        return Duration;
	    }
	    public void setDuration(int duration) {
	    	Duration = duration;
	    }
	    public boolean getEnApplause() {
	        return EnApplause;
	    }
	    public void setEnApplause(boolean enApplause) {
	    	EnApplause = enApplause;
	    }
	    
	    public boolean getEnMessage() {
	        return EnMessage;
	    }
	    public void setEnMessage(boolean enMessage) {
	    	EnMessage = enMessage;
	    }
	 	public boolean getEnLobby() {
	        return EnLobby;
	    }
	    public void setEnLobby(boolean enLobby) {
	    	EnLobby = enLobby;
	    }
	    
	    public int getMaxUser() {
	        return MaxUser;
	    }
	    public void setMaxUser(int maxUser) {
	    	MaxUser = maxUser;
	    }
	    
	    
}
