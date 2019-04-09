package com.example.mtchat_android.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mtchat_android.R;

public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, chatTypeActivity.class);
        startActivity(intent);
    }



}


//public class MainActivity extends AppCompatActivity {
//    private Button start;
//    private TextView output;
//    private OkHttpClient client;
//    private final class EchoWebSocketListener extends WebSocketListener {
//        private static final int NORMAL_CLOSURE_STATUS = 1000;
//        @Override
//        public void onOpen(WebSocket webSocket, Response response) {
//            webSocket.send("Hello, it's SSaurel !");
//            webSocket.send("What's up ?");
//            webSocket.send(ByteString.decodeHex("deadbeef"));
//            webSocket.close(NORMAL_CLOSURE_STATUS, "Goodbye !");
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, String text) {
//            output("Receiving : " + text);
//        }
//        @Override
//        public void onMessage(WebSocket webSocket, ByteString bytes) {
//            output("Receiving bytes : " + bytes.hex());
//        }
//        @Override
//        public void onClosing(WebSocket webSocket, int code, String reason) {
//            webSocket.close(NORMAL_CLOSURE_STATUS, null);
//            output("Closing : " + code + " / " + reason);
//        }
//        @Override
//        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
//            output("Error : " + t.getMessage());
//        }
//    }
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.chat_layout);
//        start = (Button) findViewById(R.id.start);
//        output = (TextView) findViewById(R.id.output);
//        client = new OkHttpClient();
//        start.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                start();
//            }
//        });
//    }
//    private void start() {
//        Request request = new Request.Builder().url("ws://77.47.224.135:8080/sock/chat").build();
//        EchoWebSocketListener listener = new EchoWebSocketListener();
//        WebSocket ws = client.newWebSocket(request, listener);
//        //  client.dispatcher().executorService().shutdown();
//    }
//    private void output(final String txt) {
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                output.setText(output.getText().toString() + "\n\n" + txt);
//            }
//        });
//    }
//}
