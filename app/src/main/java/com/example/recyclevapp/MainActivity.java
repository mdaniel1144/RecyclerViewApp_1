package com.example.recyclevapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.text.Editable;
import android.os.Bundle;
import android.widget.EditText;
import android.text.TextWatcher;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Actor> dataSet;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private CustomeAdapter adapter;

    private MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        EditText textSearch = findViewById(R.id.TextSearchInput);
        textSearch.addTextChangedListener(new TextWatcher() {

            private String previusInput = "";
            private boolean firstTime = true;
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3)
            {
                String inputName = cs.toString();
                if(firstTime)
                {
                    onChangeTextSearch(inputName);
                    firstTime = false;
                    previusInput = inputName;
                }
                else {
                    if(inputName.length() > 0) {
                        if(previusInput.length()>inputName.length())
                        {
                            initializeDataSet();
                            onChangeTextSearch(inputName);
                        }
                        else {
                            onChangeTextSearch(inputName);
                        }
                    }
                    else {
                        initializeDataSet();
                        firstTime = true;
                    }
                }
                setDataSetOnAdapter();
            }

            @Override
            public void afterTextChanged(Editable editable) {
                previusInput = editable.toString();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }
        });

        dataSet = new ArrayList<>();
        recyclerView =  findViewById(R.id.resView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        initializeDataSet();
        setDataSetOnAdapter();

        music = MediaPlayer.create(this, R.raw.song_pizmot);
        music.start();
        music.setLooping(true);
    }

    private void initializeDataSet()
    {
        dataSet.clear();
        for (int i = 0; i < MyData.s_ID.length ; i++){
            dataSet.add(new Actor(
                    MyData.s_Name[i],
                    MyData.s_Description[i],
                    MyData.s_ImageSrc[i],
                    MyData.s_ID[i]
            ));
        }
    }
    private void setDataSetOnAdapter()
    {
        adapter = new CustomeAdapter(dataSet);
        recyclerView.setAdapter(adapter);
    }

    private void onChangeTextSearch(String input)
    {
        for(int i=0 ; i<dataSet.size() ; i++)
        {
            String nameOnDataSet = dataSet.get(i).getM_Name().toUpperCase();
            if(!nameOnDataSet.contains(input.toUpperCase()))
            {
                dataSet.remove(i);
                i--;
            }
        }
    }

    public void onClickShowPopup(View v)
    {
        TextView actorTitle =  v.findViewById(R.id.textView) ;
        Toast.makeText(v.getContext(),"בחרת ב"+ actorTitle.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    public void onclickToggleMusic(View v)
    {
        if(music.isPlaying() == true)
        {
            ImageButton bt = v.findViewById(R.id.ButtonSound);
            bt.setImageResource(R.drawable.sound_off);
            music.pause();
        }
        else {
            ImageButton bt = v.findViewById(R.id.ButtonSound);
            bt.setImageResource(R.drawable.sound_on);
            music.start();
        }
    }
}








