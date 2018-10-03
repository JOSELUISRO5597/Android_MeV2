package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener{

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    private boolean mTwoPane;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;

            //Eliminar el boton de next
            Button button= (Button) findViewById(R.id.next_button);
            button.setVisibility(View.GONE);

            //Cambiar el grid dado el espacio que hay en la tablet
            GridView gridView = (GridView) findViewById(R.id.images_grid_view);
            gridView.setNumColumns(2);

            //Crear el fragment de os cuerpos
            FragmentManager fragmentManager = getSupportFragmentManager();

            //HeadFragment
            BodyPartFragment headFragment = new BodyPartFragment();
            headFragment.setList(AndroidImageAssets.getHeads());
            fragmentManager.beginTransaction().add(R.id.head_container, headFragment).commit();

            //Body fragment
            BodyPartFragment bodyFragment = new BodyPartFragment();
            bodyFragment.setList(AndroidImageAssets.getBodies());
            fragmentManager.beginTransaction().add(R.id.body_container, bodyFragment).commit();

            //Legs fragment
            BodyPartFragment legFragment = new BodyPartFragment();
            legFragment.setList(AndroidImageAssets.getLegs());
            fragmentManager.beginTransaction().add(R.id.leg_container, legFragment).commit();
        }
    }

    @Override
    public void onImageSelected(int position) {
        //Toast.makeText(this,"Position clicked = " + position, Toast.LENGTH_SHORT).show();

        int bodyPartNumber = position/12;
        int listIndex=position - 12*bodyPartNumber;

        if(mTwoPane){
            BodyPartFragment auxFragment = new BodyPartFragment();
            auxFragment.setId(listIndex);

            switch (bodyPartNumber) {
                case 0:
                    auxFragment.setList(AndroidImageAssets.getHeads());
                    getSupportFragmentManager().beginTransaction().replace(R.id.head_container,auxFragment).commit();
                    break;
                case 1:
                    auxFragment.setList(AndroidImageAssets.getBodies());
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container,auxFragment).commit();
                    break;
                case 2:
                    auxFragment.setList(AndroidImageAssets.getLegs());
                    getSupportFragmentManager().beginTransaction().replace(R.id.leg_container,auxFragment).commit();
                    break;
            }

        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
            }
        }

        Bundle b=new Bundle();
        b.putInt("headIndex",headIndex);
        b.putInt("bodyIndex",bodyIndex);
        b.putInt("legIndex",legIndex);

        final Intent intent = new Intent(this,AndroidMeActivity.class);
        intent.putExtras(b);

        Button button = (Button) findViewById(R.id.next_button);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(intent);
            }
        });
    }
}
