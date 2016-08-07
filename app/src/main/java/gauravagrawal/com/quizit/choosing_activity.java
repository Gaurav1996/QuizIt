package gauravagrawal.com.quizit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class choosing_activity extends AppCompatActivity {

    Button host_b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosing_activity);
        host_b=(Button)findViewById(R.id.host_button);
        host_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check_if_hotspot_on();
            }
        });
    }
    void check_if_hotspot_on()
    {
        WifiManager wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);
        Method[]wmMethods=wifi.getClass().getDeclaredMethods();
        for(Method method:wmMethods)
        {
            if(method.getName().equals("isWifiApEnabled"))
            {
                try
                {
                    boolean isWifiAPenabled=(boolean)method.invoke(wifi);
                    if(!isWifiAPenabled)
                    {
                        //Toast.makeText(getApplicationContext(),"HotSpot on",Toast.LENGTH_LONG).show();
                        AlertDialog.Builder alertbox=new AlertDialog.Builder(choosing_activity.this);
                        alertbox.setTitle("HotSpot off");
                        alertbox.setMessage("Your Hotspot is turned off!You Should turn on your hotspot to continue being host");
                        alertbox.show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Yayy!Hotspot on :)", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(),NsdChatActivity.class));
                        finish();
                    }
                }
                catch(IllegalArgumentException e)
                {
                    Toast.makeText(getApplicationContext(),"Some Exception",Toast.LENGTH_LONG).show();
                }
                catch(IllegalAccessException e)
                {
                    Toast.makeText(getApplicationContext(),"Some Exception",Toast.LENGTH_LONG).show();
                }
                catch(InvocationTargetException e)
                {
                    Toast.makeText(getApplicationContext(),"Some Exception",Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
