package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {

    public static final String IMAGE_ID="image_id";
    public static final String IMAGE_LIST="image_list";
    private List<Integer> list;
    private int id;

    public BodyPartFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        if(savedInstanceState != null){
            id=savedInstanceState.getInt(IMAGE_ID);
            list=savedInstanceState.getIntegerArrayList(IMAGE_LIST);
        }

        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if(imageView != null && list!= null)
            imageView.setImageResource(list.get(id));

        imageView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                if(id < list.size() - 1)
                    id++;
                else
                    id=0;
                imageView.setImageResource(list.get(id));
            }
        });
        return rootView;
    }

    public void setList(List<Integer> list){
        this.list=list;
    }

    public void setId(int id){
        this.id=id;
    }

    public void onSaveInstanceState(Bundle currentState){
        currentState.putIntegerArrayList(IMAGE_LIST,(ArrayList<Integer>) list);
        currentState.putInt(IMAGE_ID,id);
    }
}
