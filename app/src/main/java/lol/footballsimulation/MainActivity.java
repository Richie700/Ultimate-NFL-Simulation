package lol.footballsimulation;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private Button mNewTeam;
    private Button mViewTeams;
    private Button mSimulateGame;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //hardcoded teams
        if(statsList.hardcodedTeamsInitialized == false)
        {
            statInput();
            statsList.hardcodedTeamsInitialized = true;
        }


        mNewTeam = (Button) findViewById(R.id.newTeam);
        mViewTeams = (Button) findViewById(R.id.viewTeam);
        mSimulateGame = (Button) findViewById(R.id.simulateGame);

        mNewTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddTeamActivity.class);
                startActivity(intent);
            }
        });

        mViewTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewTeamActivity.class);
                statsList.gameTeamSelect = false;
                startActivity(intent);
            }
        });

        mSimulateGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewTeamActivity.class);
                statsList.gameTeamSelect = true;
                startActivity(intent);
            }
        });
    }

    public void statInput()
    {
        AssetManager am = getAssets();
        BufferedReader statsReader = null;
        BufferedReader nameReader = null;
        BufferedReader cityReader = null;
        try {
            statsReader = new BufferedReader(new InputStreamReader( am.open("stats.txt")));
            String mLine;

            while ((mLine = statsReader.readLine()) != null)
            {
                String[] words = mLine.split(" ");
                int[] ints = new int[words.length];
                for(int i = 0; i < words.length; i++)
                {
                    ints[i] = Integer.parseInt(words[i]);
                }
                statsList.totalOffenseList.add((ints[0] + ints[1]));
                statsList.passingOffenseList.add(ints[0]);
                statsList.rushingOffenseList.add(ints[1]);
                statsList.totalDefenseList.add(ints[2] + ints[3]);
                statsList.passingDefenseList.add(ints[2]);
                statsList.rushingDefenseList.add(ints[3]);
                statsList.fieldGoalList.add(ints[4]);
                statsList.kickAverageList.add(ints[5]);
                statsList.puntAverageList.add(ints[6]);
                statsList.kickReturnAverageList.add(ints[7]);
                statsList.puntReturnAverageList.add(ints[8]);

            }

            nameReader = new BufferedReader(new InputStreamReader(am.open("names.txt")));
            while ((mLine = nameReader.readLine()) != null)
            {
                statsList.teamNameList.add(mLine);

            }

            cityReader = new BufferedReader(new InputStreamReader(am.open("team_cities.txt")));
            while ((mLine = cityReader.readLine()) != null)
            {
                statsList.cityList.add(mLine);

            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            if(statsReader != null)
            {
                try {
                    statsReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
