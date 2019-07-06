package com.example.mtchat_android.activitys;

        import android.content.Intent;
        import android.os.Bundle;
        import android.os.CountDownTimer;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.webkit.WebView;

        import com.example.mtchat_android.R;
        import com.example.mtchat_android.activitys.interlocutionActivity.ChatActivity;
        import com.example.mtchat_android.models.StartSocketConnection;
        import com.example.mtchat_android.models.StaticModels;
        import com.example.mtchat_android.toasts.ToastAlert;

public class LoadingAnimationActivity extends AppCompatActivity {


   private int countClickedBackButton = 0;
   private WebView myBrowser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.loadind_animation_layout);

            StaticModels.loadingAnimationActivity = this;
            myBrowser = (WebView) findViewById(R.id.web_view);
            myBrowser.loadUrl("file:///android_asset/animation/animation.html");


    }



    public void goToChat() {
        this.finish();
        Intent intent = new Intent(this, ChatActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        countClickedBackButton++;
        if(countClickedBackButton==2) {
            if (StaticModels.setting.isGoToChat()) {
                closeConnection();
                goToChatTypeActivity();
            }
            else{
                closeConnection();
                goToInterlocutorInfoActivity();
            }
        }

        ToastAlert.toastAlert(this,"Press again to back");

        new Thread(new Runnable() {
            @Override
            public void run() {
                new CountDownTimer(2_000,1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                    }
                    @Override
                    public void onFinish() {
                        countClickedBackButton=0;
                    }
                }.start();
            }
        }).run();


    }


    private  void goToInterlocutorInfoActivity()
    {
        this.finish();
        Intent intent = new Intent(this, InterlocutorInfoActivity.class);
        startActivity(intent);
    }

    private  void goToChatTypeActivity()
    {
        this.finish();
        Intent intent = new Intent(this, ChatTypeActivity.class);
        startActivity(intent);
    }


    /**
     * If user pressed button "back", then close connection
     * @return close connection status
     */
    private boolean closeConnection()
    {
        try {
            this.finish();
            StartSocketConnection.webSocket.close(4999, "Back to intelocutor info activity");
            return true;
        }
        catch (Exception e)
        {
            Log.d("Connection","Error close connection");
            return false;
        }
    }
}


