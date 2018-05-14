package lol.footballsimulation;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.Random;

public class SingleSimulation extends AppCompatActivity {
    Random rand = new Random();
    int offense;
    int defense;
    int fieldPosition;
    int down = 1;
    int distance = 10;
    public static int team1Score = 0;
    public static int team2Score = 0;
    public static int team1PassYards = 0;
    public static int team2PassYards = 0;
    public static int team1RushYards = 0;
    public static int team2RushYards = 0;
    public static int team1Turnovers = 0;
    public static int team2Turnovers = 0;

    int time = 900;
    int quarter = 1;
    boolean gameGoing = true;
    int repeatStopper = 0;
    private LinearLayout ll;
    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.WRAP_CONTENT);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_simulation);
        ll = (LinearLayout)findViewById(R.id.listLayout);
        team1Score = 0;
        team2Score = 0;
        team1PassYards = 0;
        team2PassYards = 0;
        team1RushYards = 0;
        team2RushYards = 0;
        team1Turnovers = 0;
        team2Turnovers = 0;
        initialToss();
    }

    public void simEnd()
    {

        if(repeatStopper == 0)
        {
            Button endButton = new Button(this);
            endButton.setText("Simulation Over. Press to move on");
            ll.addView(endButton, lp);
            repeatStopper = 1;
            endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SimulationResult.class);
                    startActivity(intent);
                }
            });
        }


    }

    public void initialToss() {

        int coinToss = rand.nextInt(2) + 1;
        int initialSelection = rand.nextInt(2) + 1;
        TextView tossWin = new TextView(this);
        TextView tossSelection = new TextView(this);
        if(coinToss == 1)
        {
            tossWin.setText("The " + statsList.teamNameList.get(statsList.mTeam1) + " have won the toss.");

            if(initialSelection == 1)
            {
                tossSelection.setText("The " + statsList.teamNameList.get(statsList.mTeam1) + " have elected to recieve.");
                offense = statsList.mTeam1;
                defense = statsList.mTeam2;
            }
            if(initialSelection == 2)
            {
                tossSelection.setText("The " + statsList.teamNameList.get(statsList.mTeam1) + " have elected to kickoff.");
                defense = statsList.mTeam1;
                offense = statsList.mTeam2;
            }
        }

        if(coinToss == 2)
        {
            tossWin.setText("The " + statsList.teamNameList.get(statsList.mTeam2) + " have won the toss.");

            if(initialSelection == 1)
            {
                tossSelection.setText("The " + statsList.teamNameList.get(statsList.mTeam2) + " have elected to recieve.");
                offense = statsList.mTeam2;
                defense = statsList.mTeam1;
            }
            if(initialSelection == 2)
            {
                tossSelection.setText("The " + statsList.teamNameList.get(statsList.mTeam2) + " have elected to kickoff.");
                defense = statsList.mTeam2;
                offense = statsList.mTeam1;
            }
        }
        tossWin.setTextColor(Color.WHITE);
        tossSelection.setTextColor(Color.WHITE);
        ll.addView(tossWin, lp);
        ll.addView(tossSelection, lp);
        kickOff();
    }

    public void kickOff()
    {
        int kickModifier = rand.nextInt(10) -10;
        int kickNumber = statsList.kickAverageList.get(defense) + kickModifier;
        TextView kickoff = new TextView(this);
        if(kickNumber >= 65)
        {
            fieldPosition = 20;
            kickoff.setText("The " + statsList.teamNameList.get(defense) + " kickoff for a touchback.");
            kickoff.setTextColor(Color.WHITE);
            ll.addView(kickoff, lp);
        }
        else{
            fieldPosition = 65 - kickNumber;
            kickoff.setText("The " + statsList.teamNameList.get(defense) + " kickoff to the " + fieldPosition + " yard line of the " + statsList.teamNameList.get(offense));
            kickoff.setTextColor(Color.WHITE);
            ll.addView(kickoff, lp);
            kickReturn();
        }
        Drive();
    }

    public void kickReturn()
    {
        TextView kReturn = new TextView(this);
        clockManager();
        int returnModifier = 0;
        int returnRandomizer = rand.nextInt(100);
        if(returnRandomizer <= 50)
        {
            returnModifier = rand.nextInt(5) - 5;
        }
        else if(returnRandomizer <= 60)
        {
            returnModifier = rand.nextInt(10) + 6;
        }
        else if(returnRandomizer <= 70)
        {
            returnModifier = rand.nextInt(15) + 11;
        }
        else if(returnRandomizer <= 80)
        {
            returnModifier = rand.nextInt(20) + 16;
        }
        else if(returnRandomizer <= 95)
        {
            returnModifier = rand.nextInt(25) + 21;
        }
        else if(returnRandomizer <= 100)
        {
            returnModifier = rand.nextInt(60) + 26;
        }

        int kickNumber = statsList.kickReturnAverageList.get(offense) + returnModifier;
        fieldPosition = fieldPosition + kickNumber;

        if(fieldPosition >= 100)
        {
            kReturn.setText("The " + statsList.teamNameList.get(offense) + " return the kick for a TOUCHDOWN");
            touchDown();
        }
        if (fieldPosition > 50 && fieldPosition < 100)
        {
            kReturn.setText("The " + statsList.teamNameList.get(offense) + " return the kick to the " + (100-fieldPosition) + " yard line of the " + statsList.teamNameList.get(defense));
        }
        else{
            kReturn.setText("The " + statsList.teamNameList.get(offense) + " return the kick to the " + fieldPosition + " yard line.");
        }
        kReturn.setTextColor(Color.WHITE);
        ll.addView(kReturn, lp);
    }

    public void Drive()
    {
        down = 1;
        distance = 10;

        while(down <= 4 && gameGoing == true)
        {
            clockManager();

            if(distance <= 0)
            {
                distance = 10;
                down = 1;
            }
            TextView downAndDistance = new TextView(this);
            TextView scoreOutput = new TextView(this);
            downAndDistance.setText("down: " + down + " Distance: " + distance);
            scoreOutput.setText(statsList.teamNameList.get(statsList.mTeam1) + " score: " + team1Score + " || " + statsList.teamNameList.get(statsList.mTeam2) + " score: " + team2Score);
            downAndDistance.setTextColor(Color.WHITE);
            scoreOutput.setTextColor(Color.WHITE);
            ll.addView(downAndDistance, lp);
            ll.addView(scoreOutput, lp);

            int playPicker = rand.nextInt(100);
            if(down == 1)
            {
                if(playPicker <= 50)
                {
                    pass();
                }
                else
                {
                    run();
                }

            }
            else if(down == 2)
            {
                if(distance > 11)
                {
                    if (playPicker <= 72)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance == 10)
                {
                    if (playPicker <= 48)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance <= 9 && distance >= 7)
                {
                    if (playPicker <= 63)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance <= 6 && distance >= 4)
                {
                    if (playPicker <= 45)
                    {
                        pass();
                    }
                    else{
                        run();
                    }

                }
                else if(distance == 3 || distance == 2)
                {
                    if (playPicker <= 29)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance == 1)
                {
                    if (playPicker <= 21)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
            }
           else if(down == 3)
            {
                if(distance > 11)
                {
                    if (playPicker <= 92)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance == 10)
                {
                    if (playPicker <= 93)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
              else if(distance <= 9 && distance >= 7)
                {
                    if (playPicker <= 92)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance <= 6 && distance >= 4)
                {
                    if (playPicker <= 88)
                    {
                        pass();
                    }
                    else{
                        run();
                    }

                }
               else if(distance == 3 || distance == 2)
                {
                    if (playPicker <= 72)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
               else if(distance == 1)
                {
                    if (playPicker <= 21)
                    {
                        pass();
                    }
                    else{
                        run();
                    }
                }
            }
           else if(down == 4)
            {
                if(fieldPosition > 60)
                {
                    fieldGoal();
                }
                else{
                    punt();
                }
            }
            down++;
        }
        simEnd();
    }

    public void clockManager()
    {
        int timelost = rand.nextInt(35) + 20;
        time = time - timelost;
        if(time <= 0 && quarter < 4)
        {
            quarter++;
            time = 900;
        }
        if(time <= 0 && quarter == 4)
        {
            gameGoing = false;
        }
    }

    public void pass()
    {   int result = rand.nextInt(100) + 1;
        TextView passOutput = new TextView(this);
        if(result == 1)
        {
            interception();
        }
        else if(result <= 9)
        {
            int sack = rand.nextInt(10)+1;
            fieldPosition = fieldPosition - sack;
            distance = distance + sack;
            passOutput.setText("The " + statsList.teamNameList.get(offense) + " are sacked for a loss of " + sack +".");
            passOutput.setTextColor(Color.WHITE);
            ll.addView(passOutput, lp);
        }
        else{
            int completion = rand.nextInt(100);
            if(completion <= 70)
            {
                int passRandomizer = rand.nextInt(100);
                int passModifier = 0;

                if(passRandomizer > 99)
                {
                    passModifier = rand.nextInt(90) + 31;
                }
                if(passRandomizer > 85 && passRandomizer <= 99)
                {
                    passModifier = rand.nextInt(30) + 16;
                }
                if(passRandomizer > 60 && passRandomizer <= 85)
                {
                    passModifier = rand.nextInt(15) + 11;
                }
                if(passRandomizer <= 60 && passRandomizer > 10)
                {
                    passModifier = rand.nextInt(10) + 5;
                }
                if(passRandomizer <= 10)
                {
                    passModifier = rand.nextInt(5) -5;
                }

                int pass = (statsList.passingDefenseList.get(defense)/64) - (statsList.passingOffenseList.get(offense)/64) + passModifier;
                if(offense == statsList.mTeam1)
                {
                    team1PassYards = team1PassYards + pass;
                }
                else{
                    team2PassYards = team2PassYards + pass;
                }

                int previousFieldPosition = fieldPosition;
                fieldPosition = fieldPosition + pass;
                distance = distance - pass;
                if(fieldPosition <= 50)
                {
                    passOutput.setText("The " + statsList.teamNameList.get(offense) + " pass for " + pass + " yards to their own " + fieldPosition + " yard line.");
                    passOutput.setTextColor(Color.WHITE);
                    ll.addView(passOutput, lp);
                }
                if(fieldPosition > 50 && fieldPosition < 100)
                {
                    passOutput.setText("The " + statsList.teamNameList.get(offense) + " pass for " + pass + " yards to the " + statsList.teamNameList.get(defense) + " " + (100 - fieldPosition) + " yard line.");
                    passOutput.setTextColor(Color.WHITE);
                    ll.addView(passOutput, lp);
                }
                if(fieldPosition > 100)
                {
                    passOutput.setText("The " + statsList.teamNameList.get(offense) + " pass for " + (100 - previousFieldPosition) + " yards for a TOUCHDOWN." );
                    passOutput.setTextColor(Color.WHITE);
                    ll.addView(passOutput, lp);
                    touchDown();
                }
            }
            else{
                passOutput.setText("The " + statsList.teamNameList.get(offense) + " Throw an incomplete pass." );
                passOutput.setTextColor(Color.WHITE);
                ll.addView(passOutput, lp);
            }

        }

    }

    public void interception()
    {
        TextView interceptOutput = new TextView(this);
        interceptOutput.setText("The pass is intercepted!");
        interceptOutput.setTextColor(Color.WHITE);
        ll.addView(interceptOutput, lp);
        if(offense == statsList.mTeam1)
        {
            team2Turnovers = team2Turnovers + 1;
        }
        else{
            team1Turnovers = team1Turnovers + 1;
        }
        possessionChange();
        down = 1;
        distance = 10;
        fieldPosition = 100-fieldPosition;

    }

    public void run()
    {
        TextView runOutput = new TextView(this);
        int runRandomizer = rand.nextInt(100);
        int runModifier = 0;

        if(runRandomizer > 99)
        {
            runModifier = rand.nextInt(90) + 21;
        }
        if(runRandomizer > 85 && runRandomizer <= 99)
        {
            runModifier = rand.nextInt(20) + 11;
        }
        if(runRandomizer > 60 && runRandomizer <= 85)
        {
            runModifier = rand.nextInt(10) + 6;
        }
        if(runRandomizer <= 60 && runRandomizer > 10)
        {
            runModifier = rand.nextInt(5) + 2;
        }
        if(runRandomizer <= 10)
        {
            runModifier = rand.nextInt(1) -5;
        }

        int run = (statsList.rushingDefenseList.get(defense)/64) - (statsList.rushingOffenseList.get(offense)/64) + runModifier;
        if(offense == statsList.mTeam1)
        {
            team1RushYards = team1RushYards + run;
        }
        else{
            team2RushYards = team2RushYards + run;
        }

        int previousFieldPosition = fieldPosition;
        fieldPosition = fieldPosition + run;
        distance = distance - run;
        if(fieldPosition <= 50)
        {
            runOutput.setText("The " + statsList.teamNameList.get(offense) + " rush for " + run + " yards to their own " + fieldPosition + " yard line.");
            runOutput.setTextColor(Color.WHITE);
            ll.addView(runOutput, lp);
        }
        if(fieldPosition > 50 && fieldPosition < 100)
        {
            runOutput.setText("The " + statsList.teamNameList.get(offense) + " rush for " + run + " yards to the " + statsList.teamNameList.get(defense) + " " + (100 - fieldPosition) + " yard line.");
            runOutput.setTextColor(Color.WHITE);
            ll.addView(runOutput, lp);
        }
        if(fieldPosition > 100)
        {
            runOutput.setText("The " + statsList.teamNameList.get(offense) + " rush for " + (100 - previousFieldPosition) + " yards for a TOUCHDOWN.");
            runOutput.setTextColor(Color.WHITE);
            ll.addView(runOutput, lp);
            touchDown();
        }
    }

    public void touchDown()
    {
        if(statsList.mTeam1 == offense)
        {
            team1Score = team1Score + 6;
        }
        if(statsList.mTeam2 == offense)
        {
            team2Score = team2Score + 6;
        }
        PAT();
        possessionChange();
        kickOff();
    }

    public void fieldGoal()
    {
        TextView fgOutput = new TextView(this);
        int FG = rand.nextInt(100);
        if(FG < statsList.fieldGoalList.get(offense))
        {
            if(statsList.mTeam1 == offense)
            {
                team1Score = team1Score + 3;
            }
            if(statsList.mTeam2 == offense)
            {
                team2Score = team2Score + 3;
            }
            fgOutput.setText("The field goal is good!");
            fgOutput.setTextColor(Color.WHITE);
            ll.addView(fgOutput, lp);
            possessionChange();
            kickOff();
        }
        else{
            fgOutput.setText("The field goal is no good!");
            fgOutput.setTextColor(Color.WHITE);
            ll.addView(fgOutput, lp);
            possessionChange();
            fieldPosition = 100 - fieldPosition;
            Drive();
        }
    }

    public void PAT()
    {
        TextView patOutput = new TextView(this);
        int PAT = rand.nextInt(100);
        if(PAT < 94)
        {
            if(statsList.mTeam1 == offense)
            {
                team1Score = team1Score + 1;
            }
            if(statsList.mTeam2 == offense)
            {
                team2Score = team2Score + 1;
            }
            patOutput.setText("The PAT is good!");
        }
        else{
            patOutput.setText("The PAT is no good!");
        }
        patOutput.setTextColor(Color.WHITE);
        ll.addView(patOutput, lp);

    }

    public void possessionChange()
    {
        if(statsList.mTeam1 == offense)
        {
            offense = statsList.mTeam2;
            defense = statsList.mTeam1;
        }
        else if(statsList.mTeam2 == offense)
        {
            offense = statsList.mTeam1;
            defense = statsList.mTeam2;
        }
    }

    public void punt()
    {
        TextView puntOutput = new TextView(this);
        fieldPosition = fieldPosition + statsList.puntAverageList.get(offense);
        if(fieldPosition > 100)
        {
            fieldPosition = 20;
            puntOutput.setText("The " + statsList.teamNameList.get(offense) + " punt for a touchback.");
        }

        else if (fieldPosition > 50)
        {
            fieldPosition = 100-fieldPosition;
            puntOutput.setText("The " + statsList.teamNameList.get(offense) + " punt to the " + fieldPosition + " yard line of the " + statsList.teamNameList.get(defense));
        }
        else {
            puntOutput.setText("The " + statsList.teamNameList.get(offense) + " punt to their own " + fieldPosition + " yard line.");
        }
        puntOutput.setTextColor(Color.WHITE);
        ll.addView(puntOutput, lp);

        possessionChange();
        Drive();

    }
}
