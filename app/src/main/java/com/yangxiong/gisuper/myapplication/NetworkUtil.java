package com.yangxiong.gisuper.myapplication;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Enumeration;

/**
 * function : 网络工具类.
 * <p></p>
 * Created by Leo on 2016/1/28.
 */
@SuppressWarnings({"unused", "BooleanMethodIsAlwaysInverted", "deprecation", "WeakerAccess"})
public class NetworkUtil {

    private static final String TAG = NetworkUtil.class.getSimpleName();

    /** 获取IP mac */
    public static String getLocalIpAddress() {
        try {
            for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if(!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch(SocketException ex) {
            ex.printStackTrace();
        }
        return "";
    }

    /** 获取内网IP */
    public static String getLocalIPAddress(Context context) {
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo    = null;
        if(wifiManager != null) {
            wifiInfo = wifiManager.getConnectionInfo();
        }
        if(wifiInfo != null) {
            int ipAddress = wifiInfo.getIpAddress();
            return String.valueOf(ipAddress&0xFF)+"."+
                    ((ipAddress >> 8)&0xFF)+"."+
                    ((ipAddress >> 16)&0xFF)+"."+
                    ((ipAddress >> 24)&0xFF);
        }
        return null;
    }

    /** 判断网络的连接状态 */
    public static boolean isNetworkConnected(Context context) {
        if(context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo         = null;
            if(mConnectivityManager != null) {
                mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            }
            if(mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    /** 获取当前网络连接的类型信息 */
    public static int getConnectedType(Context context) {
        if(context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo         = null;
            if(mConnectivityManager != null) {
                mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            }
            if(mNetworkInfo != null && mNetworkInfo.isAvailable()) {
                return mNetworkInfo.getType();
            }
        }
        return -1;
    }

    /** 判断当前网络是否为wifi */
    public static boolean isWifi(Context mContext) {
        return getConnectedType(mContext) == ConnectivityManager.TYPE_WIFI;
    }


    /** 获取外网IP */
    public static void getExternalIPAddress(final INetWorkStateCallback callback) {
        new Thread(() -> {
            final String ip;
            InputStream inStream;
            HttpURLConnection httpConnection = null;
            try {
                URL infoUrl    = new URL("http://1212.ip138.com/ic.asp");
                URLConnection connection = infoUrl.openConnection();
                httpConnection = (HttpURLConnection) connection;
                int responseCode = httpConnection.getResponseCode();
                if(responseCode == HttpURLConnection.HTTP_OK) {
                    inStream = httpConnection.getInputStream();
                    BufferedReader reader  = new BufferedReader(new InputStreamReader(inStream, "gb2312"));
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    inStream.close();
                    int start = builder.indexOf("[");
                    int end   = builder.indexOf("]");
                    ip = builder.substring(start+1, end);
                    if(callback != null) {
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                if(httpConnection != null) {
                    httpConnection.disconnect();
                }
            }
        }
        ).start();
    }

    public interface INetWorkStateCallback {
        void onResponse(String externalIP);
    }
}
