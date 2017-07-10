package Actors;

import Components.Graphics.Sprite;
import Core.GameObject;
import Core.ResourceLoader;
import Terrain.Cell;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by dimaer on 21/06/17.
 */
public abstract class Piece extends GameObject{

    //private Sprite sprite;
    private Sprite spriteOutline;

    //private boolean selected;

    private Cell currentCell;

    //private Color color;
    private Team.TEAMTYPE team;

    public Piece(){}
    public Piece(Cell cell, Team.TEAMTYPE team){
        //selected = false;
        super(cell.getPosition());
        this.team = team;
        name = "PIECE";
        //sprite = new Sprite(cell.getPosition());
        spriteOutline = new Sprite(cell.getPosition());
        spriteOutline.setVisibility(false);
        currentCell = cell;

        getSprite().setDepth(getCurrentCell().getSprite().getDepth());
        getSpriteOutline().setDepth(getCurrentCell().getSprite().getDepth());
        //mouse = new Point();
        //color = new Color(255,255,255);
    }

    /**
     * Metodo che sposta la piedina
     * @param cell cella della nuova posizione
     */
    public void Move(Cell cell){

        setCurrentCell(cell);
        Point position = new Point(getSprite().getOrigin().x + cell.getSprite().getCenter().x,
                getSprite().getOrigin().y + cell.getSprite().getCenter().y);
        getSprite().setPosition(position);
        getSpriteOutline().setPosition(position);

        getSprite().setDepth(getCurrentCell().getSprite().getDepth());
        getSpriteOutline().setDepth(getCurrentCell().getSprite().getDepth());

    }
    public abstract void Die();

    public void setCurrentCell(Cell currentCell) {
        this.currentCell = currentCell;
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public Team.TEAMTYPE getTeam() {
        return team;
    }
    /*public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }
*/
    /*public Sprite getSprite()
    {
        return sprite;
    }
*/
  /*  protected void setSprite(String Team,String id){
        sprite.setImage(ResourceLoader.getInstance().LoadSprite(Team,id));
    }*/

    @Override
    public void setPosition(Point position) {
        super.setPosition(position);
        spriteOutline.setPosition(position);
    }

    protected void setSpriteOutline(String Team, String id){
        spriteOutline.setImage(ResourceLoader.getInstance().LoadSprite(Team,id));
    }
    public Sprite getSpriteOutline(){
        return spriteOutline;
    }

    @Override
    public void draw(Graphics graphics) {
        //sprite.draw(graphics);
        if(isVisibility()){
            super.draw(graphics);
            spriteOutline.draw(graphics);
        }
         //graphics.drawString(""+getSprite().getDepth(),getSprite().getPosition().x,getSprite().getPosition().y-50);
        /*if(perPixelCollision(new Point(mouse.x,mouse.y)))
            graphics.setColor(new Color(100,0,0));
        else
            graphics.setColor(new Color(0,0,0,0));

        graphics.fillRect(getSprite().getPosition().x,getSprite().getPosition().y,
                getSprite().getImage().getWidth(),
                getSprite().getImage().getHeight());*/
        //graphics.drawString(" " + (mouse.y - getSprite().getPosition().y),getSprite().getPosition().x,getSprite().getPosition().y);
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        spriteOutline.setVisibility(selected);
    }

    @Override
    public void update(MouseEvent mouseEvent) {
        super.update(mouseEvent);
        if(isSelected()){
            spriteOutline.setVisibility(true);
        }
        else {
            spriteOutline.setVisibility(false);
        }
    }
}
