package lol.footballsimulation;

import java.util.ArrayList;
import java.util.List;

public class statsList {
    public static int mTeam1;
    public static int mTeam2;
    public static int selectedSingleTeam;

    public static boolean hardcodedTeamsInitialized = false;

    public static boolean gameTeamSelect = false;

    public static List<String> cityList = new ArrayList<String>();
    public static List<String> teamNameList = new ArrayList<String>();
    public static List<Integer> totalOffenseList = new ArrayList<Integer>();
    public static List<Integer> passingOffenseList = new ArrayList<Integer>();
    public static List<Integer> rushingOffenseList = new ArrayList<Integer>();
    public static List<Integer> totalDefenseList = new ArrayList<Integer>();
    public static List<Integer> passingDefenseList = new ArrayList<Integer>();
    public static List<Integer> rushingDefenseList = new ArrayList<Integer>();
    public static List<Integer> fieldGoalList = new ArrayList<Integer>();
    public static List<Integer> puntAverageList = new ArrayList<Integer>();
    public static List<Integer> kickAverageList = new ArrayList<Integer>();
    public static List<Integer> puntReturnAverageList = new ArrayList<Integer>();
    public static List<Integer> kickReturnAverageList = new ArrayList<Integer>();
}
