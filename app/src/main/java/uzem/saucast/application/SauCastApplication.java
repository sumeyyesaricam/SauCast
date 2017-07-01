package uzem.saucast.application;

import android.app.Application;

import uzem.saucast.helper.SauCastClient;
import uzem.saucast.model.Message;
import uzem.saucast.model.WatcherModel;

/**
 * Created by Sümeyye on 25.7.2016.
 */
public class SauCastApplication extends Application {

    public SauCastClient Client;
    public WatcherModel watcherModel;
    public int count = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Client = new SauCastClient();
            setMessageListener();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setMessageListener() {
        Client.setEventMessageListener(new SauCastClient.SauCastMessageListener() {

            @Override
            public void messageOn(Message m) {
                    if (watcherModel.getMessages().get(watcherModel.getMessages().size()-1).getMessageid() != m.MessageId) {
                        watcherModel.getMessages().add(m);
                        count++;
                    }
            }

            @Override
            public void messageDeleted(int id, int total) {
                for (int i = 0; i < watcherModel.getMessages().size(); i++) {
                    if (watcherModel.getMessages().get(i).getMessageid() == id)
                        watcherModel.getMessages().remove(i);
                }
            }
        });
    }

    public void setWatcherModel(WatcherModel watcherModel) {
        this.watcherModel = watcherModel;
    }

    public WatcherModel getWatcherModel() {
        return watcherModel;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
