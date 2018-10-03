/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    public AndroidMeActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        Bundle b=getIntent().getExtras();

        FragmentManager fragmentManager = getSupportFragmentManager();

        //HeadFragment
        BodyPartFragment headFragment = new BodyPartFragment();
        headFragment.setList(AndroidImageAssets.getHeads());
        if(b != null)
            headFragment.setId(b.getInt("headIndex"));
        fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();

        //Body fragment
        BodyPartFragment bodyFragment = new BodyPartFragment();
        bodyFragment.setList(AndroidImageAssets.getBodies());
        if(b != null)
            bodyFragment.setId(b.getInt("bodyIndex"));
        fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();

        //Legs fragment
        BodyPartFragment legFragment = new BodyPartFragment();
        legFragment.setList(AndroidImageAssets.getLegs());
        if(b != null)
            legFragment.setId(b.getInt("legIndex"));
        fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();

    }
}
