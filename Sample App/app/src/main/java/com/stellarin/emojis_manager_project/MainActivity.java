/*
 * Copyright (C) 2016 Ahmed khalil Bejaoui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stellarin.emojis_manager_project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stellarin.emojis_manager.SEmojis;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    TextView output;
    EditText msg_box;
    Button send_btn;

    HashMap<String, Integer> list = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inject view components
        InjectViews();

        // fill the list with some emojis for this example
        list.put(":)",R.drawable.smiley);
        list.put(";)",R.drawable.wink);
        list.put(":poop:",R.drawable.poop);
        list.put(":p ",R.drawable.tongue);


        //create click listener on send button
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = msg_box.getText().toString();
                //call lib to analyse the message
                launch(msg);
            }
        });
    }

    /**
     * Inject components
     */
    private void InjectViews(){
        output = (TextView) findViewById(R.id.result);
        msg_box = (EditText) findViewById(R.id.msg_box);
        send_btn = (Button) findViewById(R.id.send_btn);
    }

    /**
     * call the emojismanager lib
     * @param msg input msg
     */
    private void launch(String msg){
        SEmojis an = new SEmojis(this);
        //must do, to set your emojis list
        an.init(list);
        SpannableStringBuilder res = an.FetchAndReplace(msg);
        //set the result on any text view
        output.setText(res);
    }

}
