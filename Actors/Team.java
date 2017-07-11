package Actors;

import Components.Event.MouseObserver;
import Components.Graphics.Drawable;
import Terrain.Board;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * Created by dimaer on 31/03/17.
 */
public class Team implements Drawable,MouseObserver {

    private ArrayList<Piece> members;
    TEAMTYPE teamtype;

    /**
     *
     * @param teamtype
     * @param board
     */
    public Team(TEAMTYPE teamtype,Board board)
    {
        members = new ArrayList<>();
        this.teamtype = teamtype;
        //generateTeam(board);
    }

    public enum TEAMTYPE{
        Red, Blue
    }
    /*private void generateTeam(Board board){
        if(teamtype == TEAMTYPE.Red){

        }
        if(teamtype == TEAMTYPE.Blue){
            for (int i = 0 ;i<8;i++){
                Pawn pawn = new Pawn(board.getCell(6,(char)('a'+i)),TEAMTYPE.Blue);
                addMember(pawn);
            }
                addMember(new Rook(board.getCell(7,'a'),TEAMTYPE.Blue,board));
                addMember(new Knight(board.getCell(7,'b'),TEAMTYPE.Blue,board));
                addMember(new Bishop(board.getCell(7,'c'),TEAMTYPE.Blue,board));
                addMember(new Queen(board.getCell(7,'d'),TEAMTYPE.Blue,board));
                addMember(new Bishop(board.getCell(7,'f'),TEAMTYPE.Blue,board));
                addMember(new Knight(board.getCell(7,'g'),TEAMTYPE.Blue,board));

                addMember(new King(board.getCell(7,'e'),TEAMTYPE.Blue));



                addMember(new Rook(board.getCell(7,'h'),TEAMTYPE.Blue));


        }
    }
*/
    /**
     *
     * @return
     */
    public int getTeamSize(){
        return members.size();
    }

    /**
     *
     * @param member
     */
    public void deleteMember(Piece member){
        members.remove(member);
    }

    /**
     *
     * @param member
     */
    public void addMember(Piece member){
        members.add(member);
    }

    /**
     *
     * @return
     */
    public ArrayList<Piece> getMembers() {
        return members;
    }

    /**
     *
     * @return
     */
    public TEAMTYPE getTeamtype() {
        return teamtype;
    }

    /**
     *
     * @param graphics instanza di Graphics
     */
    @Override
    public void draw(Graphics graphics) {
        for (Piece piece : members){
            piece.draw(graphics);
        }
    }

    /**
     *
     * @param mouseEvent evento di Mouse
     */
    @Override
    public void update(MouseEvent mouseEvent) {
        for(Piece p : members) {
            p.update(mouseEvent);
        }
    }
}
