package Actors;

import java.util.ArrayList;

/**
 * Created by dimaer on 31/03/17.
 */
public class Team {
    private ArrayList<Pawn> members;
    TEAMTYPE teamtype;
    public Team(TEAMTYPE teamtype)
    {
        members = new ArrayList<>();
        this.teamtype = teamtype;
    }
    enum TEAMTYPE{
        RED,BLUE
    }
    private void generateTeam(){
        if(teamtype == TEAMTYPE.RED){
            
        }
    }
    public int getTeamSize(){
        return members.size();
    }
    public void deleteMember(Pawn member){
        members.remove(member);
    }
    public void addMember(Pawn member){
        members.add(member);
    }

}
