package uzem.saucast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sümeyye on 13.7.2016.
 */
public class WatcherModel implements Serializable {

    @SerializedName("Started")
    public boolean Started ;

    @SerializedName("PresenterOnline")
    public boolean PresenterOnline;

    @SerializedName("GuestOnline")
    public boolean GuestOnline;

    @SerializedName("Description")
    public String Description ;

    @SerializedName("Messages")
    public List<Message> Messages ;

    @SerializedName("MessageCount")
    public int MessageCount ;

    @SerializedName("LikesCount")
    public int LikesCount ;

    public boolean getStarted() {
        return Started;
    }
    public void setStarted(boolean started) {
        Started = started;
    }

    public boolean getPresenterOnline() {
        return PresenterOnline;
    }
    public void setPresenterOnline(boolean presenterOnline) {
        PresenterOnline = presenterOnline;
    }

    public boolean getGuestOnline() {
        return GuestOnline;
    }
    public void setGuestOnline(boolean guestOnline) {
        GuestOnline = guestOnline;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    public  List<Message> getMessages() {
        return Messages;
    }
    public void setMessages( List<Message> messages) {
        Messages = messages;
    }

    public int getMessageCount() {
        return MessageCount;
    }
    public void setMessageCount(int messageCount) {
        MessageCount =messageCount;
    }

    public int getLikesCount() {
        return LikesCount;
    }
    public void setLikesCount(int likesCount) {
        LikesCount = likesCount;
    }
}
