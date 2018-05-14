package lol.footballsimulation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SimulationResult extends AppCompatActivity {
    private TextView teamName1;
    private TextView teamName2;
    private TextView teamScore1;
    private TextView teamScore2;
    private TextView team1TotalO;
    private TextView team2TotalO;
    private TextView team1PO;
    private TextView team2PO;
    private TextView team1RO;
    private TextView team2RO;
    private TextView team1TA;
    private TextView team2TA;
    private Button mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulation_result);
        teamName1 = (TextView) findViewById(R.id.team1Name);
        teamName2 = (TextView) findViewById(R.id.team2Name);
        teamScore1 = (TextView) findViewById(R.id.team1Score);
        teamScore2 = (TextView) findViewById(R.id.team2Score);
        mainMenu = (Button) findViewById(R.id.mainMenu);
        team1TotalO = (TextView) findViewById(R.id.t1to);
        team2TotalO = (TextView) findViewById(R.id.t2to);
        team1PO = (TextView) findViewById(R.id.t1po);
        team2PO = (TextView) findViewById(R.id.t2po);
        team1RO = (TextView) findViewById(R.id.t1ro);
        team2RO = (TextView) findViewById(R.id.t2ro);
        team1TA = (TextView) findViewById(R.id.t1ta);
        team2TA = (TextView) findViewById(R.id.t2ta);

        String scoreString1 = Integer.toString(SingleSimulation.team1Score);
        String scoreString2 = Integer.toString(SingleSimulation.team2Score);
        String totalOffense1Str = Integer.toString(SingleSimulation.team1PassYards + SingleSimulation.team1RushYards);
        String totalOffense2Str = Integer.toString(SingleSimulation.team2PassYards + SingleSimulation.team2RushYards);
        String passOffense1Str = Integer.toString(SingleSimulation.team1PassYards);
        String passOffense2Str = Integer.toString(SingleSimulation.team2PassYards);
        String rushOffense1Str = Integer.toString(SingleSimulation.team1RushYards);
        String rushOffense2Str = Integer.toString(SingleSimulation.team2RushYards);
        String turnOver1Str = Integer.toString(SingleSimulation.team1Turnovers);
        String turnOver2Str = Integer.toString(SingleSimulation.team2Turnovers);


        teamName1.setText(statsList.cityList.get(statsList.mTeam1));
        teamName2.setText(statsList.cityList.get(statsList.mTeam2));
        teamScore1.setText(scoreString1);
        teamScore2.setText(scoreString2);
        team1TotalO.setText(totalOffense1Str);
        team2TotalO.setText(totalOffense2Str);
        team1PO.setText(passOffense1Str);
        team2PO.setText(passOffense2Str);
        team1RO.setText(rushOffense1Str);
        team2RO.setText(rushOffense2Str);
        team1TA.setText(turnOver1Str);
        team2TA.setText(turnOver2Str);



        mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
