package uzem.saucast.fragment;

import android.annotation.TargetApi;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

import uzem.saucast.R;
import uzem.saucast.activity.LiveActivity;
import uzem.saucast.activity.WListActivity;
import uzem.saucast.adapter.MessageAdapter;
import uzem.saucast.application.SauCastApplication;
import uzem.saucast.helper.SauCastClient;
import uzem.saucast.model.Message;

/**
 * Created by Sümeyye on 26.7.2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MessageFragment extends DialogFragment {

    ListView listMessage;
    View view;
    Button btnClose, btnSend;
    EditText etMessage;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    int msgCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = inflater.inflate(R.layout.activity_message, null, false);

        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        final SauCastApplication app = (SauCastApplication) getActivity().getApplication();
        SetScreenElements();
        CallApplication();
        btnClose.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            dismiss();
                                            LiveActivity.isOpen=false;
                                        }
                                    }
        );
        return view;
    }

    public void CallApplication() {
        try {
            final SauCastApplication app = (SauCastApplication) getActivity().getApplication();
            if (app.getWatcherModel() != null) {
                messageList = app.getWatcherModel().getMessages();
                if (messageList != null) {
                    setListAdapter(messageList);
                }
                app.Client.setEventMessageListener(new SauCastClient.SauCastMessageListener() {

                    @Override
                    public void messageOn(Message m) {
                        if(messageList.size()==0){
                            messageList.add(m);
                            setListAdapter(messageList);
                            if(!LiveActivity.isOpen){
                                msgCount++;
                                app.setCount(msgCount);
                            }
                        }
                        else{
                        if (messageList.get(messageList.size()-1).getMessageid() != m.MessageId) {
                                messageList.add(m);
                        //app.getWatcherModel().getMessages().add(m);
                        setListAdapter(messageList);
                        if(!LiveActivity.isOpen){
                            msgCount++;
                            app.setCount(msgCount);
                        }}
                        }

                    }

                    @Override
                    public void messageDeleted(int id, int total) {
                        for (int i = 0; i < messageList.size(); i++) {
                            if (messageList.get(i).getMessageid() == id) {
                                messageList.remove(i);
                                //app.getWatcherModel().getMessages().remove(i);
                            }
                        }
                        setListAdapter(messageList);
                    }
                });
                btnSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Message msg = new Message();
                        msg.setBody(etMessage.getText().toString());
                        msg.setSenderName("test2 test2");
                        msg.setType((byte) 1);
                        msg.setWebinarId(WListActivity.WEBINAR_ID);
                        msg.setSender("watcher");
                        msg.setSenderId(0);
                        etMessage.getText().clear();
                        app.Client.SendMessage(msg);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setListAdapter(final List<Message> msgList) {
        try {
            if (getActivity() != null)
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        messageAdapter = new MessageAdapter(getActivity(), msgList);
                        listMessage.setAdapter(messageAdapter);
                    }
                });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SetScreenElements() {
        listMessage = (ListView) view.findViewById(R.id.listMessage);
        btnClose = (Button) view.findViewById(R.id.btnClose);
        btnSend = (Button) view.findViewById(R.id.btnSend);
        etMessage = (EditText) view.findViewById(R.id.etMessage);
    }
}
