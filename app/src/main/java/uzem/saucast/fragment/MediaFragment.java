package uzem.saucast.fragment;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import uzem.saucast.R;
import uzem.saucast.activity.WListActivity;

/**
 * Created by Sümeyye on 26.7.2016.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MediaFragment extends Fragment {

    VideoView presenterView, guestView;
    TextView txtPresenter, txtGuest;
    public static boolean presenterFlag = true, guestFlag = true;
    private static Thread thread1, thread2;
    View rootView;
    String presenter, guest;
    int presenterCount ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().getWindow().setTitle(WListActivity.WEBINAR_TITLE);
        rootView = inflater.inflate(R.layout.activity_stream_video, container, false);
        String _eventID = String.valueOf(WListActivity.WEBINAR_ID);

        Log.i("sumeyye", "Event ID : " + _eventID);
        Log.i("sumeyye", "Guest Stream : \"" + guest + "\"");
        Log.i("sumeyye", "Presenter Stream : \"" + presenter + "\"");

        presenterLoad();
        guestLoad();
        rootView.post(new Runnable() {
            public void run() {
                int h = rootView.getMeasuredHeight();
                presenterView.getLayoutParams().height = h / 2;
                txtPresenter.getLayoutParams().height = h / 2;

                guestView.getLayoutParams().height = h / 2;
                txtGuest.getLayoutParams().height = h / 2;

                ((RelativeLayout.LayoutParams) guestView.getLayoutParams()).setMargins(0, h / 2, 0, 0);
                ((RelativeLayout.LayoutParams) txtGuest.getLayoutParams()).setMargins(0, h / 2, 0, 0);
            }
        });

        return rootView;
    }

    public void presenterLoad() {
        presenter = "rtsp://webinar.sakarya.edu.tr:1935/Webinar/" + WListActivity.WEBINAR_ID + "_presenter" + "_aac";
        presenterView = (VideoView) rootView.findViewById(R.id.presenterView);
        txtPresenter = (TextView) rootView.findViewById(R.id.txtPresenter);
        presenterCount=0;
        presenterView.setVideoPath(presenter);
        presenterView.setVisibility(View.VISIBLE);
        presenterView.requestFocus();

        thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                if (presenterView.isPlaying() == false) {
                    try {
                        if (presenterCount < 10) {
                            presenterCount = presenterCount + 1;
                            presenterView.start();
                        } else {
                            txtPresenter.setVisibility(View.VISIBLE);
                            txtPresenter.setText("Sunucu Çevrimdışı!");
                            presenterView.setVisibility(View.INVISIBLE);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        thread1.start();
        presenterView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                try {
                    thread1 = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                            if (presenterView.isPlaying() == false) {
                                try {
                                    if (presenterCount < 10) {
                                        presenterCount = presenterCount + 1;
                                        presenterView.start();
                                    } else {
                                        txtPresenter.setVisibility(View.VISIBLE);
                                        txtPresenter.setText("Sunucu Çevrimdışı!");
                                        presenterView.setVisibility(View.INVISIBLE);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    thread1.start();
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
                return true;
            }
        });
        presenterView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(final MediaPlayer mp) {
                presenterView.setVisibility(View.INVISIBLE);
                txtPresenter.setText("Sunucu çevrim dışı oldu.!");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Toast.makeText(getActivity().getApplicationContext(), "Video durduruldu.", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        presenterView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                presenterView.setBackgroundColor(0);
                presenterView.setBackgroundResource(0);
                txtPresenter.setVisibility(View.INVISIBLE);
            }
        });


    }

    public void guestLoad() {

        guest = "rtsp://webinar.sakarya.edu.tr:1935/Webinar/" + WListActivity.WEBINAR_ID + "_guest" + "_aac";
        guestView = (VideoView) rootView.findViewById(R.id.guestView);
        txtGuest = (TextView) rootView.findViewById(R.id.txtGuest);
        guestView.setVideoPath(guest);
        boolean play2 = guestView.isPlaying();
        guestView.requestFocus();
        thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                android.os.Process.setThreadPriority(android.os.Process.THREAD_PRIORITY_DISPLAY);
                for (int i = 0; i <= 100; i++) {
                    boolean play = guestView.isPlaying();
                    if (play == false) {
                        guestView.start();
                    }
                }
            }
        });
        thread2.start();

        guestView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                if (guestFlag) {
                    txtGuest.setVisibility(View.VISIBLE);
                    if (presenterFlag) {
                        txtGuest.setText("Konuk Çevrim Dışı!");
                    }
                    guestFlag = false;
                }

                return true;
            }
        });


        guestView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(final MediaPlayer mp) {
                guestView.setVisibility(View.INVISIBLE);
                txtGuest.setText("Konuk çevrim dışı oldu!");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            mp.stop();
                            mp.release();
                            // Toast.makeText(getActivity().getApplicationContext(), "Video durduruldu.", Toast.LENGTH_LONG).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        guestView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                guestView.setBackgroundColor(0);
                guestView.setBackgroundResource(0);
                txtGuest.setVisibility(View.INVISIBLE);
            }
        });
    }
}