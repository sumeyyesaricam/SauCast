package uzem.saucast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Sümeyye on 14.7.2016.
 */
public class LikeModel implements Serializable
//Sender ı sor.
{
    @SerializedName("ConnectionId")
    public String ConnectionId ;

    @SerializedName("WebinarId")
    public int WebinarId;

    @SerializedName("Role")
    public String Role  ;

    @SerializedName("UserId")
    public int UserId  ;

    @SerializedName("Count")
    public int Count   ;

    public String getConnectionId() {
        return ConnectionId;
    }

    public void setConnectionId(String connectionId) {
        ConnectionId =connectionId;
    }

    public int getWebinarId() {
        return WebinarId;
    }

    public void setWebinarId(int webinarId) {
        WebinarId = webinarId;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

}
