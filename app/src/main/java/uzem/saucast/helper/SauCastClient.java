package uzem.saucast.helper;

import android.util.Log;

import microsoft.aspnet.signalr.client.Action;
import microsoft.aspnet.signalr.client.ErrorCallback;
import microsoft.aspnet.signalr.client.LogLevel;
import microsoft.aspnet.signalr.client.Logger;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import uzem.saucast.activity.LiveActivity;
import uzem.saucast.activity.WListActivity;
import uzem.saucast.model.LikeModel;
import uzem.saucast.model.Message;
import uzem.saucast.model.WatcherModel;

/**
 * Created by Sümeyye on 25.7.2016.
 */
public class SauCastClient {
    private SauCastListener listener;
    private SauCastMessageListener messageListener;
    public HubConnection mHubConnection;
    public HubProxy mHubProxy;
    String HubUrl = "http://webinar.sakarya.edu.tr";

    public SauCastClient() {

    }

    public void startSignalR() {
        Platform.loadPlatformComponent(new AndroidPlatformComponent());
        Logger logger = new Logger() {

            @Override
            public void log(String message, LogLevel level) {
                Log.i(" Bilgi", message);
            }
        };

        mHubConnection = new HubConnection(HubUrl, "", true, logger);
        mHubProxy = mHubConnection.createHubProxy("WebinarHub");

        mHubProxy.subscribe(new Object() {
            @SuppressWarnings("unused")
            public void load(WatcherModel wmodel) {
                listener.Load(wmodel);
            }

            @SuppressWarnings("unused")
            public void statusOn(boolean started) {
                listener.statusOn(started);
            }

            @SuppressWarnings("unused")
            public void presenterOn(String connectId) {
                listener.presenterOn(connectId);
            }

            @SuppressWarnings("unused")
            public void presenterOff(String connectId) {
                listener.presenterOff(connectId);
            }

            @SuppressWarnings("unused")
            public void webinarEndOn(int id) {
                listener.webinarEndOn(id);
            }

            @SuppressWarnings("unused")
            public void messageOn(Message m) {
                messageListener.messageOn(m);
                if(!LiveActivity.isOpen)
                listener.messageCount();
            }

            @SuppressWarnings("unused")
            public void messageDeleted(int id, int total) {
                messageListener.messageDeleted(id, total);
            }

            @SuppressWarnings("unused")
            public void guestOn(String connectId) {
                listener.guestOn(connectId);
            }

            @SuppressWarnings("unused")
            public void guestOff(String connectId) {
                listener.guestOff(connectId);
            }

            @SuppressWarnings("unused")
            public void likedOn(LikeModel like) {
                listener.likedOn(like);
            }

            @SuppressWarnings("unused")
            public void descriptionOn(String d) {
                listener.descriptionOn(d);
            }

            @SuppressWarnings("unused")
            public void banned(String code) {
                listener.banned(code);
            }

            @SuppressWarnings("unused")
            public void limit(String code) {
                listener.limit(code);
            }
        });
        mHubConnection.error(new ErrorCallback() {
            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        mHubConnection.connected(new Runnable() {
            @Override
            public void run() {
                Log.i("Sumeyye", "Bağlandı");
            }
        });
        mHubConnection.start()
                .done(new Action<Void>() {
                    @Override
                    public void run(Void obj) throws Exception {
                        Log.i("Connect", "Bağlanma bitti.");
                        mHubProxy.invoke("Watcher", 12177).done(new Action<Void>() {
                            @Override
                            public void run(Void obj) {
                                Log.i("Sumeyye", "Watcher Bağlandı");
                            }
                        });
                    }
                });

    }

    public void SendMessage(Message msg) {
        mHubProxy.invoke("message", msg);
    }
    public void SendLike(int userid){
        mHubProxy.invoke("like","Watcher", WListActivity.WEBINAR_ID,userid);
    }
    public interface SauCastListener {
        public void Load(WatcherModel wmodel);

        public void statusOn(boolean started);

        public void presenterOn(String connectId);

        public void presenterOff(String connectId);

        public void webinarEndOn(int id);

        public void guestOn(String connectId);

        public void guestOff(String connectId);

        public void likedOn(LikeModel like);

        public void descriptionOn(String d);

        public void banned(String code);

        public void limit(String code);

        public void messageCount();
    }

    public interface SauCastMessageListener {

        public void messageOn(Message m);
        public void messageDeleted(int id, int total);

    }

    public void setEventListener(SauCastListener listener) {
        this.listener = listener;
    }

    public void setEventMessageListener(SauCastMessageListener messageListener) {
        this.messageListener = messageListener;
    }
}
