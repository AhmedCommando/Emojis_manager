
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
package com.stellarin.emojis_manager;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Ahmed commando on 21/09/16.
 */
public class SEmojis {

    HashMap<String,Integer> emojis_codes = new HashMap<>();
    ArrayList<Character> lst = new ArrayList<>();
    Context ctx;
    /**
     * init emojis list
     * @param emj_codes
     */
    public  void init(HashMap<String, Integer> emj_codes){
        this.emojis_codes = emj_codes;


    }

    public SEmojis(Context ctx){
        this.ctx = ctx;
    }




    /**
     * Search if input contains an emoji from the list
     * @param text input
     * @return true if exist
     */
    private boolean ContainEmojis(String text){
        Iterator it = emojis_codes.keySet().iterator();
        while(it.hasNext()) {
            String key = (String) it.next();
            if (text.contains(key))
                return true;
        }
        return false;
    }


    /**
     * Fetch for all available emojis and replace it with its image
     * @param text input
     * @return SpannableStringBuilder output
     */
    public SpannableStringBuilder FetchAndReplace(String text){
        setupInitials();
        SpannableStringBuilder builder = new SpannableStringBuilder(text);
        if (emojis_codes.size() > 0) {
            int index;
            for (index = 0; index < builder.length(); index++) {

                if (lst.contains(builder.charAt(index))) {

                    for (Map.Entry<String, Integer> entry : emojis_codes.entrySet()) {
                        int length = entry.getKey().length();
                        if (index + length > builder.length())
                            continue;

                        if (builder.subSequence(index, index + length).toString().equals(entry.getKey())) {
                            builder.setSpan(new ImageSpan(ctx, entry.getValue()), index, index + length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                            index += length - 1;
                            break;
                        }
                    }
                }
            }
        }else
            throw new RuntimeException("Error, Empty emojis list!");
        return builder;

    }


    /**
     * add all first caracters of input to a list
     */
    private void setupInitials(){
        Iterator it = emojis_codes.keySet().iterator();
        while(it.hasNext()) {
            String key = (String) it.next();
            if(!lst.contains(key.charAt(0)))
                lst.add(key.charAt(0));
        }

    }

}
