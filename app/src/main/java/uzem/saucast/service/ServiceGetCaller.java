package uzem.saucast.service;

import java.net.InetAddress;
import java.util.List;

import org.apache.http.NameValuePair;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

public class ServiceGetCaller extends AsyncTask<String, Void, String> {

    private ProgressDialog progressDialog;
    private Activity _Activity = null;
    private final static String API_URL = "http://webinar.sakarya.edu.tr/api/WApi";
    private List<NameValuePair> _Data ;
    public ServiceGetCaller(Activity activity) {
        this._Activity = activity;
    }
    public ServiceGetCaller(List<NameValuePair> data) {
    	this._Data = data;
    }
    public ServiceGetCaller(Activity activity,List<NameValuePair> data) {
        this._Activity = activity;
        this._Data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
    @Override
    protected String doInBackground(String... params) {
        ServiceHandler sh = new ServiceHandler();
        String jsonStr =null;
        if(this._Data==null){
        	jsonStr = sh.makeServiceCall(API_URL+params[0], ServiceHandler.GET);
        }else{
        	jsonStr = sh.makeServiceCall(API_URL+params[0], ServiceHandler.POST,this._Data);
        }
        Log.d("Response: ", "> " + jsonStr);

        return jsonStr;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
    }

}


