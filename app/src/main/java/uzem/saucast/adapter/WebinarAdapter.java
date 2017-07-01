package uzem.saucast.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import uzem.saucast.R;
import uzem.saucast.model.Webinar;

/**
 * Created by Sümeyye on 27.7.2016.
 */
public class WebinarAdapter extends ListAdapter<Webinar> {


    public WebinarAdapter(Context c, List<Webinar> items) {
        super(c, items);
    }
    public static class ViewHolder{

        public TextView txtWebinarName;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder=new ViewHolder();
        Webinar webinar = items.get(position);
        View vi=convertView;
        if(convertView==null){
            try{
                vi = inflater.inflate(R.layout.webinarlist, null);
                holder.txtWebinarName=(TextView)vi.findViewById(R.id.txtWebinarName);
                vi.setTag(holder);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        else
            holder=(ViewHolder)convertView.getTag();
        holder.txtWebinarName.setText(webinar.getWebinarTitle());

        return vi;
    }
}

