package gauravagrawal.com.quizit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText user;
    Button save;
    private static final String PREFS="QuizIt";
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs=getSharedPreferences(PREFS,MODE_PRIVATE);
        editor=prefs.edit();

        boolean isRegistered=prefs.getBoolean("isRegistered",false);

        if(isRegistered)
            startActivity(new Intent(MainActivity.this,choosing_activity.class));
        user=(EditText)findViewById(R.id.user_name);
        save=(Button)findViewById(R.id.proceed_button);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choice_activity=new Intent(MainActivity.this,choosing_activity.class);
                editor.putString("user_name",user.toString());
                editor.putBoolean("isRegistered",true);
                editor.commit();
                startActivity(choice_activity);
                finish();
            }
        });
    }
}