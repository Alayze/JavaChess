package Actors;

import Components.Graphics.Drawable;
import Terrain.Board;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by dimaer on 31/03/17.
 */
public class Team implements Drawable {

    private ArrayList<Piece> members;
    TEAMTYPE teamtype;

    public Team(TEAMTYPE teamtype,Board board)
    {
        members = new ArrayList<>();
        this.teamtype = teamtype;
        generateTeam(board);
    }

    public enum TEAMTYPE{
        Red, Blue
    }
    private void generateTeam(Board board){
        if(teamtype == TEAMTYPE.Red){

        }
        if(teamtype == TEAMTYPE.Blue){
            for (int i = 0 ;i<8;i++){
                Pawn pawn = new Pawn(board.getCell(6,(char)('a'+i)),TEAMTYPE.Blue);
                addMember(pawn);
            }
                addMember(new Rook(board.getCell(7,'a'),TEAMTYPE.Blue));
                addMember(new Knight(board.getCell(7,'b'),TEAMTYPE.Blue));
                addMember(new Bishop(board.getCell(7,'c'),TEAMTYPE.Blue));
                addMember(new Queen(board.getCell(7,'d'),TEAMTYPE.Blue));
                addMember(new Bishop(board.getCell(7,'f'),TEAMTYPE.Blue));
                addMember(new Knight(board.getCell(7,'g'),TEAMTYPE.Blue));

                addMember(new King(board.getCell(7,'e'),TEAMTYPE.Blue));



                addMember(new Rook(board.getCell(7,'h'),TEAMTYPE.Blue));


        }
    }
    public int getTeamSize(){
        return members.size();
    }
    public void deleteMember(Piece member){
        members.remove(member);
    }
    public void addMember(Piece member){
        members.add(member);
    }

    @Override
    public void draw(Graphics graphics) {
        for (Piece piece : members){
            piece.draw(graphics);
        }
    }
}
