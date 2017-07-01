package uzem.saucast.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Sümeyye on 13.7.2016.
 */
public class Message implements Serializable
//Sender ı sor.
{
    @SerializedName("MessageId")
    public int MessageId;

    @SerializedName("Sender")
    public String Sender;

    @SerializedName("ConnectionId")
    public String ConnectionId;

    @SerializedName("Type")
    public byte Type;

    @SerializedName("WebinarId")
    public int WebinarId;

    @SerializedName("SenderId")
    public int SenderId;

    @SerializedName("TotalCount")
    public int TotalCount;


    @SerializedName("SenderName")
    public String SenderName;

    @SerializedName("Body")
    public String Body;

    @SerializedName("Date")
    public String Date;

    @SerializedName("FormattedDate")
    public String FormattedDate;

    @SerializedName("Confirmed")
    public boolean Confirmed;

    @SerializedName("Deleted")
    public boolean Deleted;


    public int getMessageid() {
        return MessageId;
    }

    public void setMessageid(int _messageid) {
        MessageId = _messageid;
    }

    public String getConnectionId() {
        return ConnectionId;
    }

    public void setConnectionId(String connectionId) {
        ConnectionId = connectionId;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public byte getType() {
        return Type;
    }

    public void setType(byte type) {
        Type = type;
    }

    public int getSenderId() {
        return SenderId;
    }

    public void setSenderId(int senderId) {
        SenderId = senderId;
    }

    public int getWebinarId() {
        return WebinarId;
    }

    public void setWebinarId(int webinarId) {
        WebinarId = webinarId;
    }

    public int getTotalCount() {
        return TotalCount;
    }

    public void setTotalCount(int totalCount) {
        TotalCount = totalCount;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getBody() {
        return Body;
    }

    public void setBody(String _body) {
        Body = _body;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFormattedDate() {
        return FormattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        FormattedDate = formattedDate;
    }

    public boolean getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(boolean _confirmed) {
        Confirmed = _confirmed;
    }

    public boolean getDeleted() {
        return Deleted;
    }

    public void setDeleted(boolean deleted) {
        Deleted = deleted;
    }
}
