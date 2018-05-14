package lol.footballsimulation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTeamActivity extends AppCompatActivity {

    private Button mConfirm;
    private EditText mTeamName;
    private EditText mPassingOffense;
    private EditText mRushingOffense;
    private EditText mPassingDefense;
    private EditText mRushingDefense;
    private EditText mFieldGoalPercentage;
    private EditText mPuntAverage;
    private EditText mKickAverage;
    private EditText mPuntReturnAverage;
    private EditText mKickReturnAverage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_team);

        //Button and EditText variables
        mConfirm= (Button) findViewById(R.id.confirm);
        mTeamName= (EditText) findViewById(R.id.teamNameView);
        mPassingOffense= (EditText) findViewById(R.id.passingOffense);
        mRushingOffense= (EditText) findViewById(R.id.rushingOffense);
        mPassingDefense= (EditText) findViewById(R.id.passingDefense);
        mRushingDefense= (EditText) findViewById(R.id.rushingDefense);
        mFieldGoalPercentage= (EditText) findViewById(R.id.fieldGoalPercentage);
        mPuntAverage= (EditText) findViewById(R.id.yardsPerPunt);
        mKickAverage= (EditText) findViewById(R.id.yardsPerKickoff);
        mPuntReturnAverage= (EditText) findViewById(R.id.puntReturnAverage);
        mKickReturnAverage= (EditText) findViewById(R.id.kickReturnAverage);

        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mPassingOffense.getText().toString().trim().length() == 0 || mRushingOffense.getText().toString().trim().length() == 0 || mPassingDefense.getText().toString().trim().length() == 0 ||
                        mRushingDefense.getText().toString().trim().length() == 0 || mFieldGoalPercentage.getText().toString().trim().length() == 0 || mPuntAverage.getText().toString().trim().length() == 0 ||
                        mKickAverage.getText().toString().trim().length() == 0 || mPuntReturnAverage.getText().toString().trim().length() == 0 || mKickReturnAverage.getText().toString().trim().length() == 0)
                {
                    Toast noInfo = Toast.makeText(getApplicationContext(), "fill all stat fields!", Toast.LENGTH_SHORT );
                    noInfo.show();
                }
                else{
                    String teamName = mTeamName.getText().toString();
                    statsList.teamNameList.add(teamName);

                    String passingOffenseString = mPassingOffense.getText().toString();
                    int passingOffenseInt = Integer.parseInt(passingOffenseString);
                     statsList.passingOffenseList.add(passingOffenseInt);

                    String rushingOffenseString = mRushingOffense.getText().toString();
                    int rushingOffenseInt = Integer.parseInt(rushingOffenseString);
                    statsList.rushingOffenseList.add(rushingOffenseInt);

                    int totalOffense = passingOffenseInt + rushingOffenseInt;
                    statsList.totalOffenseList.add(totalOffense);

                    String passingDefenseString = mPassingDefense.getText().toString();
                    int passingDefenseInt = Integer.parseInt(passingDefenseString);
                    statsList.passingDefenseList.add(passingDefenseInt);

                    String rushingDefenseString = mRushingDefense.getText().toString();
                    int rushingDefenseInt = Integer.parseInt(rushingDefenseString);
                    statsList.rushingDefenseList.add(rushingDefenseInt);

                    String fieldGoalString = mFieldGoalPercentage.getText().toString();
                    int fieldGoalInt = Integer.parseInt(fieldGoalString);
                    statsList.fieldGoalList.add(fieldGoalInt);

                    String puntAverageString = mPuntAverage.getText().toString();
                    int puntAverageInt = Integer.parseInt(puntAverageString);
                    statsList.puntAverageList.add(puntAverageInt);

                    String kickAverageString = mKickAverage.getText().toString();
                    int kickAverageInt = Integer.parseInt(kickAverageString);
                    statsList.kickAverageList.add(kickAverageInt);

                    String puntReturnAverageString = mPuntReturnAverage.getText().toString();
                    int puntReturnAverageInt = Integer.parseInt(puntReturnAverageString);
                    statsList.puntReturnAverageList.add(puntReturnAverageInt);

                    String kickReturnAverageString = mKickReturnAverage.getText().toString();
                    int kickReturnAverageInt = Integer.parseInt(kickReturnAverageString);
                    statsList.kickReturnAverageList.add(kickReturnAverageInt);

                    int totalDefense = passingDefenseInt + rushingDefenseInt;
                    statsList.totalDefenseList.add(totalDefense);

                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

