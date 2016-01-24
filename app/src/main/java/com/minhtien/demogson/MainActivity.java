package com.minhtien.demogson;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Test myInfor;
    private TextView textView;
    private TextView details;
    private Button saveData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        textView = (TextView) findViewById(R.id.my_text);
        details = (TextView) findViewById(R.id.details);
        saveData = (Button) findViewById(R.id.mButton);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Gson gson = new Gson();
                String json = gson.toJson(myInfor);
                editor.putString("MyObjects",json);
                editor.commit();
                Toast.makeText(MainActivity.this,"da save data",Toast.LENGTH_SHORT).show();
            }
        });


        // get data
        Gson gson =  new Gson();
        String json = sharedPreferences.getString("MyObjects", "");
        myInfor = gson.fromJson(json,Test.class);

        if(myInfor == null)
        {
           textView.setText("Chua co du lieu ! ");
            details.setText("null");
            myInfor = new Test("Tien" , "21");
            saveData.setVisibility(View.VISIBLE);
        }
        else
        {
            textView.setText("Du lieu da duoc luu tu truoc !");
            details.setText(myInfor.getName() + " : " + myInfor.getAge());
            saveData.setVisibility(View.INVISIBLE);
        }
    }

    class Test {
        private String name;
        private String age;

        public Test(String name, String age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }
    }
}
