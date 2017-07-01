package uzem.saucast.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import uzem.saucast.R;
import uzem.saucast.adapter.WebinarAdapter;
import uzem.saucast.helper.JsonParser;
import uzem.saucast.model.Webinar;
import uzem.saucast.service.ServiceGetCaller;

public class WListActivity extends AppCompatActivity {

    public static int WEBINAR_ID =0;
    public static String WEBINAR_TITLE="";
    public static boolean WEBINAR_STARTED=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);
        GetWebinar();
    }

    public void GetWebinar() {
        try {
            String jsonStr = new ServiceGetCaller(WListActivity.this).execute("/List").get();
            if (jsonStr != null) {
                try {
                    final List<Webinar> webinars = JsonParser.getWebinarObject(jsonStr);

                    final ListView webinarList = (ListView) findViewById(R.id.listWebinar);
                    if (webinars != null) {
                        WebinarAdapter webinarAdapter = new WebinarAdapter(WListActivity.this, webinars);
                        webinarList.setAdapter(webinarAdapter);
                        webinarList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> parent,
                                                    View view, int position, long id) {
                                Webinar webinar = webinars.get(position);
                                Intent intent = new Intent(WListActivity.this, LiveActivity.class);
                                WEBINAR_ID=webinar.getWebinarId();
                                WEBINAR_TITLE=webinar.getWebinarTitle();
                                    startActivity(intent);
                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Lütfen bir kullanıcı adı giriniz.", Toast.LENGTH_LONG).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
