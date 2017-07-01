package uzem.saucast.model;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class Watcher implements Serializable{
	
	@SerializedName("MediaServer")
    private  String MediaServer ;
 
 	@SerializedName("MediaApp")
    private  String MediaApp ;
 	
 	@SerializedName("StartMessage")
    private  String StartMessage ;
 	
 	@SerializedName("Description")
    private  String Description ;
 	
 	@SerializedName("StartDate")
    private  String StartDate ;

 	@SerializedName("WebinarId")
    private  int WebinarId ;
 
 	@SerializedName("WebinarTitle")
    private  String WebinarTitle ;
 	
 	@SerializedName("Started")
    private  boolean Started ;
 	
 	@SerializedName("WatcherId")
    private  int WatcherId ;
 	
 	@SerializedName("Name")
    private  String Name ;
 	
 	@SerializedName("Surname")
    private  String Surname ;
 
 	@SerializedName("Email")
    private  String Email ;
 	
 	@SerializedName("Code")
    private  String Code ;
 	
 	@SerializedName("Online")
    private  boolean Online ;
 	
 	@SerializedName("Banned")
    private  boolean Banned ;
 
 	@SerializedName("ConnectionId")
    private  String ConnectionId ;
 
 	
 	@SerializedName("Date")
    private  String Date ;
 	
 	@SerializedName("PresenterStream")
    private  String PresenterStream ;
 
 	@SerializedName("PresenterName")
    private  String PresenterName ;
 	
 	@SerializedName("GuestStream")
    private  String GuestStream ;
 	
 	@SerializedName("GuestName")
    private  String GuestName ;
 	
 	public String getCode() {
        return Code;
    }
    public void setCode(String code) {
    	Code = code;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
    	Email = email;
    }
    public String getMediaServer() {
        return MediaServer;
    }
    public void setMediaServer(String mediaServer) {
    	MediaServer = mediaServer;
    }
 	public String getMediaApp() {
        return MediaApp;
    }
    public void setMediaApp(String mediaApp) {
    	MediaApp = mediaApp;
    }
    
    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
    	Description = description;
    }
 	public String getWebinarTitle() {
        return WebinarTitle;
    }
    public void setWebinarTitle(String webinarTitle) {
    	WebinarTitle = webinarTitle;
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
 	public boolean getOnline() {
        return Online;
    }
    public void setOnline(boolean online) {
    	Online = online;
    }
    
    public boolean getBanned() {
        return Banned;
    }
    public void setBanned(boolean banned) {
    	Banned = banned;
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
    
    public String getConnectionId() {
        return ConnectionId;
    }
    public void setConnectionId(String connectionId) {
    	ConnectionId = connectionId;
    }
 	public int getWebinarId() {
        return WebinarId;
    }
    public void setWebinarId(int webinarId) {
    	WebinarId = webinarId;
    }
    public int getWatcherId() {
        return WatcherId;
    }
    public void setWatcherId(int watcherId) {
    	WatcherId = watcherId;
    }
    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
    	Date = date;
    }
    
    public String getPresenterStream() {
        return PresenterStream;
    }
    public void setPresenterStream(String presenterStream) {
    	PresenterStream = presenterStream;
    }
 	public String getPresenterName() {
        return PresenterName;
    }
    public void setPresenterName(String presenterName) {
    	PresenterName = presenterName;
    }
    
    public String getGuestStream() {
        return GuestStream;
    }
    public void setGuestStream(String guestStream) {
    	GuestStream = guestStream;
    }
    
    public String getGuestName() {
        return GuestName;
    }
    public void setGuestName(String guestName) {
    	GuestName = guestName;
    }
	
}
