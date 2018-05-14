package lol.footballsimulation;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

public class ViewTeamActivity extends AppCompatActivity {

    public int teamsSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_team);
        final List<Button> mNewTeamButtons = new ArrayList<Button>();
        teamsSelected = 0;

        for(int count = 0; count < statsList.teamNameList.size(); count++)
        {
            int backgroundColor = 0;
            final int buttonName = count;
            Button newButton = new Button(this);

            newButton.setBackgroundColor(0xAD020202);
            newButton.setTextColor(Color.WHITE);
            //newButton.setPadding(0,0,0,0);
            mNewTeamButtons.add(newButton);
            mNewTeamButtons.get(count).setText(statsList.teamNameList.get(count));
            LinearLayout ll = (LinearLayout)findViewById(R.id.listLayout);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT);
            ll.addView(mNewTeamButtons.get(count), lp);
            mNewTeamButtons.get(count).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(statsList.gameTeamSelect == true)
                    {
                        if(teamsSelected == 0)
                        {
                            mNewTeamButtons.get(buttonName).setEnabled(false);
                            statsList.mTeam1 = buttonName;
                        }
                        if(teamsSelected == 1)
                        {
                            mNewTeamButtons.get(buttonName).setEnabled(false);
                            statsList.mTeam2 = buttonName;
                            Intent intent = new Intent(v.getContext(), SingleSimulation.class);
                            startActivity(intent);
                        }
                        teamsSelected++;
                        mNewTeamButtons.get(buttonName).setBackgroundColor(0xcfff6f00);

                    }
                    else{
                        statsList.selectedSingleTeam = buttonName;
                        Intent intent = new Intent(v.getContext(), ViewSingleTeamActivity.class);
                        startActivity(intent);
                    }
                }
            });
        }
    }
}
