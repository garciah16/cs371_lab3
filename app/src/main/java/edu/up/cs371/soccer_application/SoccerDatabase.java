package edu.up.cs371.soccer_application;

import android.support.annotation.NonNull;
import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {


    Hashtable<String, SoccerPlayer> table = new Hashtable<String, SoccerPlayer>();

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {

        if (table.containsKey(firstName + "_" + lastName)) {
            //player already exists in the hash table
            return false;
        } else {
            SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            table.put(firstName + "_" + lastName, newPlayer);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.remove(firstName + "_" + lastName);
            return true;
        }

        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {

        if (table.containsKey(firstName + "_" + lastName)) {
            return table.get(firstName + "_" + lastName);
        }

        return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {

        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpGoals();
            return true;
        }
        return false;
    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpAssists();
            return true;
        }
        return false;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpShots();
            return true;
        }
        return false;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpSaves();
            return true;
        }
        return false;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpFouls();
            return true;
        }
        return false;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpYellowCards();
            return true;
        }
        return false;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        if (table.containsKey(firstName + "_" + lastName)) {
            table.get(firstName + "_" + lastName).bumpRedCards();
            return true;
        }
        return false;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {

        int PlayerNum = 0;
        if (teamName == null) {
            return table.size();
        } else {
            Set<String> playerSet = table.keySet();
            String[] playerArray = playerSet.toArray(new String[table.size()]);


            for (int i = 0; i < playerArray.length; i++) {

                if (table.get(playerArray[i]).getTeamName().equals(teamName)) {
                    PlayerNum = PlayerNum + 1;
                }

            }
            return PlayerNum;
        }

    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerNum(int idx, String teamName) {

        Set<String> playerSet = table.keySet();
        String[] playerArray = playerSet.toArray(new String[table.size()]);

        if(idx >= table.size()){

            return null;
        }
        if (teamName == null) {
            return table.get(playerArray[idx]);

        } else {

            int index = 0;

            for (int i = 0; i < playerArray.length; i++) {

                if (table.get(playerArray[i]).getTeamName().equals(teamName)) {

                    if(index ==idx){
                        return table.get(playerArray[i]);
                    }
                    index++;
                }


            }


        }

        return null;
    }



    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file){


        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        Set<String> playerSet = table.keySet();
        String[] playerArray = playerSet.toArray(new String[table.size()]);

        for(int i=0; i<table.size();i++){
            pw.println("First Name: "+logString(table.get(playerArray[i]).getFirstName()));
            pw.println("Last Name: "+logString(table.get(playerArray[i]).getLastName()));
            pw.println("Team Name: "+logString(table.get(playerArray[i]).getTeamName()));
            pw.println("Uniform #: "+(table.get(playerArray[i]).getUniform()));
            pw.println("Goals: "+(table.get(playerArray[i]).getGoals()));
            pw.println("Assists:" +(table.get(playerArray[i]).getAssists()));
            pw.println("Shots: "+(table.get(playerArray[i]).getShots()));
            pw.println("Saves: "+(table.get(playerArray[i]).getSaves()));
            pw.println("Fouls: "+(table.get(playerArray[i]).getFouls()));
            pw.println("Yellow Cards: "+(table.get(playerArray[i]).getYellowCards()));
            pw.println("Red Cards: "+(table.get(playerArray[i]).getRedCards()));
            pw.println();

        }
        pw.close();
        return true;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
