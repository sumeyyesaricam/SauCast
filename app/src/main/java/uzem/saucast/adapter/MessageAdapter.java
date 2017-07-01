package uzem.saucast.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uzem.saucast.R;
import uzem.saucast.model.Message;

/**
 * Created by Sümeyye on 11.7.2016.
 */
public class MessageAdapter extends ListAdapter<Message> {

    public MessageAdapter(Context c, List items) {
        super(c, items);
    }

    public static class ViewHolder {
        public TextView txtSenderName, txtMessage;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = new ViewHolder();
        View vi = convertView;
        if (convertView == null) {

            vi = inflater.inflate(R.layout.messagelist, null);
            holder.txtSenderName = (TextView) vi.findViewById(R.id.txtSenderName);
            holder.txtMessage = (TextView) vi.findViewById(R.id.txtMessage);
            vi.setTag(holder);

        } else
            holder = (ViewHolder) convertView.getTag();
        try {
            Message message = items.get(position);
            holder.txtSenderName.setText(message.getSenderName() + ":");
            holder.txtMessage.setText(message.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vi;
    }
}
