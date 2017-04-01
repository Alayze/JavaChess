package Core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Classe che carica le resource
 * Created by dimaer on 27/03/17.
 */
public final class ResourceLoader {
    private File resourceFile;
    private File levelFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document document;

    private ResourceLoader(){
        resourceFile = new File("src/Resources.xml");
        levelFile = new File("src/Levels.xml");
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(resourceFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.print(document.getDocumentURI() + "\n");
    }

    private static final ResourceLoader resourceLoader = new ResourceLoader();

    /**
     * Metodo che torna l'istanza della classe
     * @return
     */
    public static ResourceLoader getInstance(){
        return resourceLoader;
    }


    /*public String LoadResource(String id,String type)
    {
        NodeList nodeList = document.getElementsByTagName(type);
        for(int i = 0; i < nodeList.getLength();i++){
            Element element = (Element) nodeList.item(i);
            if(element.getAttribute("id").equals(id))
                return element.getElementsByTagName("path").item(0).getTextContent();

        }
            return "Error for load resource\n";
    }*/
    public String LoadTile(String weatherType,String id)
    {
        NodeList tiles = document.getElementsByTagName("Tiles");
        for(int i = 0; i < tiles.getLength();i++)
        {
            Element tilesRoot = (Element) tiles.item(i);
            NodeList group = tilesRoot.getElementsByTagName("group");

            for(int groupcount = 0;groupcount<group.getLength();groupcount++)
            {
                Element groupElement = (Element) group.item(groupcount);

                if(groupElement.getAttribute("id").equals(weatherType))
                {
                    NodeList sprites = groupElement.getElementsByTagName("sprite");

                    for(int spritecount = 0;spritecount<sprites.getLength();spritecount++)
                    {
                        Element sprite = (Element) sprites.item(spritecount);

                        if(sprite.getAttribute("id").equals(id))
                        {
                            return sprite.getElementsByTagName("path").item(0).getTextContent();
                        }
                    }
                }
            }

        }
            return "Error for load resource: " + weatherType + " " + id + "\n";
    }
    /*private boolean checkDocument(){
        document.getDocumentURI();
    }*/
    public String LoadSprite(String Team,String id)
    {
        NodeList actors = document.getElementsByTagName("Actors");
        for(int i = 0; i < actors.getLength();i++)
        {
            Element actorsRoot = (Element) actors.item(i);
            NodeList team = actorsRoot.getElementsByTagName("team");

            for(int teamcount = 0;teamcount<team.getLength();teamcount++)
            {
                Element groupElement = (Element) team.item(teamcount);

                if(groupElement.getAttribute("id").equals(Team))
                {
                    NodeList sprites = groupElement.getElementsByTagName("sprite");

                    for(int spritecount = 0;spritecount<sprites.getLength();spritecount++)
                    {
                        Element sprite = (Element) sprites.item(spritecount);

                        if(sprite.getAttribute("id").equals(id))
                        {
                            return sprite.getElementsByTagName("path").item(0).getTextContent();
                        }
                    }
                }
            }

        }
        return "Error for load resource: " + Team + " " + id + "\n";
    }
    /*public String LoadLevel(String id)
    {
        NodeList actors = document.getElementsByTagName("Actors");
        for(int i = 0; i < actors.getLength();i++)
        {
            Element actorsRoot = (Element) actors.item(i);
            NodeList team = actorsRoot.getElementsByTagName("team");

            for(int teamcount = 0;teamcount<team.getLength();teamcount++)
            {
                Element groupElement = (Element) team.item(teamcount);

                if(groupElement.getAttribute("id").equals(Team))
                {
                    NodeList sprites = groupElement.getElementsByTagName("sprite");

                    for(int spritecount = 0;spritecount<sprites.getLength();spritecount++)
                    {
                        Element sprite = (Element) sprites.item(spritecount);

                        if(sprite.getAttribute("id").equals(id))
                        {
                            return sprite.getElementsByTagName("path").item(0).getTextContent();

                        }
                    }
                }
            }

        }
        return "Error for load resource: " + Team + " " + id + "\n";
    }*/
    private String formatString(String string){
        String temp = "";
        for(int i = 0;i<string.length();i++){
            if(string.charAt(i) != ' '){
                temp+=string.charAt(i);
            }
        }
        return temp;
    }

}
