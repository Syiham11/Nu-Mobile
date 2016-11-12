package com.adfin.numobile.utility;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ClientThread implements Runnable
{
    //For debug
    private final String TAG = "ClientThread";

    private Socket socket;
    private String ip;
    private int port;
    private Handler receiveHandler;
    public Handler sendHandler;
    BufferedReader bufferedReader;
    private InputStream inputStream;
    private OutputStream outputStream;
    public boolean isConnect = false;
    Context context;
    private static final int MEGABYTE = (1024*1024);

    public ClientThread(Handler handler) {
        // TODO Auto-generated constructor stub
        this.receiveHandler = handler;

        Log.d(TAG, "ClientThread's construct is OK!!");
    }
    public ClientThread(Handler handler, String ip, String port) {
        // TODO Auto-generated constructor stub
        this.receiveHandler = handler;
        this.ip = ip;
        this.port = Integer.valueOf(port);
        Log.d(TAG, "ClientThread's construct is OK!!");
    }
    public ClientThread()
    {
        Log.d(TAG, "It is may be construct's problem...");
    }

    public ClientThread(String tes)
    {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public void run()
    {
        try
        {
            Log.d(TAG, "Into the run()");
            socket = new Socket(ip, port);
            isConnect = socket.isConnected();
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
         //   MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
            //To monitor if receive Msg from Server
            try {
                new Thread() {
                    @Override
                    public void run() {
                        System.gc();
                     //   CRC32 crc32 = new CRC32();
                        byte[] buffer = new byte[257762];


                        final StringBuilder stringBuilder = new StringBuilder();
                        try {
                            while (socket.isConnected()) {
                                int readSize = inputStream.read(buffer);
                                Log.d(TAG, "readSize:" + readSize);


                                //If Server is stopping
                                if (readSize == -1) {
                                    inputStream.close();
                                    outputStream.close();
                                }
                                if (readSize == 0) continue;
                                try {
                                    //Update the receive editText
                                    stringBuilder.append(new String(buffer, 0, readSize));
                                    Log.e("buffer     ==", "" + buffer);

                                    Log.e("testestestestestes     ==", "" + new String(buffer, 0, readSize));
                                    Message msg = new Message();
                                    msg.what = 0x123;
                                    msg.obj = new String(buffer, 0, readSize);
                                    Log.d(TAG, "=============================:" + msg.obj);

                                    receiveHandler.sendMessage(msg);

                                } catch (Exception e) {

                                }
                            }
                        } catch (IOException e) {
                            Log.d(TAG, e.getMessage());
                            e.printStackTrace();
                        }
                    }


                }.start();
            }catch (Exception e){

            }
            //To Send Msg to Server
            Looper.prepare();
            sendHandler = new Handler()
            {
                @Override
                public void handleMessage(Message msg)
                {
                    if (msg.what == 0x852)
                    {
                        try
                        {
                            outputStream.write((msg.obj.toString() + "\r\n").getBytes());
                            outputStream.flush();
                        }
                        catch (Exception e)
                        {
                            Log.d(TAG, e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();

        } catch (SocketTimeoutException e)
        {
            // TODO Auto-generated catch block
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }catch (UnknownHostException e)
        {
            // TODO Auto-generated catch block
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            Log.d(TAG, e.getMessage());
            e.printStackTrace();
        }
    }



}
