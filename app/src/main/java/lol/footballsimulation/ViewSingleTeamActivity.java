package lol.footballsimulation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewSingleTeamActivity extends AppCompatActivity {
    private TextView teamName;
    private TextView totalOffenseNumber;
    private TextView passingOffenseNumber;
    private TextView rushingOffenseNumber;
    private TextView totalDefenseNumber;
    private TextView passingDefenseNumber;
    private TextView rushingDefenseNumber;
    private TextView fieldGoalNumber;
    private TextView kickAverageNumber;
    private TextView puntAverageNumber;
    private TextView kickReturnAverageNumber;
    private TextView puntReturnAverageNumber;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_single_team);
        teamName = (TextView) findViewById(R.id.teamNameView);
        totalOffenseNumber = (TextView) findViewById(R.id.totalOffenseNumber);
        passingOffenseNumber = (TextView) findViewById(R.id.passingOffenseNumber);
        rushingOffenseNumber = (TextView) findViewById(R.id.rushingOffenseNumber);
        totalDefenseNumber = (TextView) findViewById(R.id.totalDefenseNumber);
        passingDefenseNumber = (TextView) findViewById(R.id.passingDefenseNumber);
        rushingDefenseNumber = (TextView) findViewById(R.id.rushingDefenseNumber);
        fieldGoalNumber = (TextView) findViewById(R.id.fieldGoalNumber);
        kickAverageNumber = (TextView) findViewById(R.id.kickAverageNumber);
        puntAverageNumber = (TextView) findViewById(R.id.puntAverageNumber);
        kickReturnAverageNumber = (TextView) findViewById(R.id.kickReturnAverageNumber);
        puntReturnAverageNumber = (TextView) findViewById(R.id.puntReturnAverageNumber);



        String totalOffense = statsList.totalOffenseList.get(statsList.selectedSingleTeam).toString();
        String passingOffense = statsList.passingOffenseList.get(statsList.selectedSingleTeam).toString();
        String rushingOffense = statsList.rushingOffenseList.get(statsList.selectedSingleTeam).toString();
        String totalDefense = statsList.totalDefenseList.get(statsList.selectedSingleTeam).toString();
        String passingDefense = statsList.passingDefenseList.get(statsList.selectedSingleTeam).toString();
        String rushingDefense = statsList.rushingDefenseList.get(statsList.selectedSingleTeam).toString();
        String fieldGoal = statsList.fieldGoalList.get(statsList.selectedSingleTeam).toString();
        String kickAverage = statsList.kickAverageList.get(statsList.selectedSingleTeam).toString();
        String puntAverage = statsList.puntAverageList.get(statsList.selectedSingleTeam).toString();
        String kickReturnAverage = statsList.kickReturnAverageList.get(statsList.selectedSingleTeam).toString();
        String puntReturnAverage = statsList.puntReturnAverageList.get(statsList.selectedSingleTeam).toString();


        teamName.setText(statsList.teamNameList.get(statsList.selectedSingleTeam));
        totalOffenseNumber.setText(totalOffense);
        passingOffenseNumber.setText(passingOffense);
        rushingOffenseNumber.setText(rushingOffense);
        totalDefenseNumber.setText(totalDefense);
        passingDefenseNumber.setText(passingDefense);
        rushingDefenseNumber.setText(rushingDefense);
        fieldGoalNumber.setText(fieldGoal);
        kickAverageNumber.setText(kickAverage);
        puntAverageNumber.setText(puntAverage);
        kickReturnAverageNumber.setText(kickReturnAverage);
        puntReturnAverageNumber.setText(puntReturnAverage);



    }
}
