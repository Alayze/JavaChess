package Core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Classe che carica le resource facendo parsing dei file resource.xml e levels.xml
 * Created by dimaer on 27/03/17.
 */
public final class ResourceLoader {
    private File resourceFile;
    private File levelFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document documentRes;
    private Document documentLevel;


    private ResourceLoader(){
        resourceFile = new File("src/Resources.xml");
        levelFile = new File("src/Levels.xml");
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            documentRes = dBuilder.parse(resourceFile);
            documentLevel = dBuilder.parse(levelFile);
        } catch (Exception e){
            e.printStackTrace();
        }
        System.out.print(documentRes.getDocumentURI() + "\n");
    }

    private static final ResourceLoader resourceLoader = new ResourceLoader();

    /**
     * Metodo che torna l'istanza della classe
     * @return ResourceLoader
     */
    public static ResourceLoader getInstance(){
        return resourceLoader;
    }

    /**
     * Metodo che cerca il path nella cartela di applicazione di immagini definiti nella cartela resource.xml
     * @param weatherType stagione
     * @param id nome di sprite
     * @return path del file
     */
    public String LoadTile(String weatherType,String id)
    {
        NodeList tiles = documentRes.getElementsByTagName("Tiles");
        Element tilesRoot = (Element) tiles.item(0);
        NodeList groups = tilesRoot.getElementsByTagName("group");

        for(int i = 0; i < groups.getLength();i++)
        {

            Element group = (Element) groups.item(i);

            if(group.getAttribute("id").equals(weatherType)){

                NodeList sprites = group.getElementsByTagName("sprite");

                for(int sprite_count = 0;sprite_count<sprites.getLength();sprite_count++){

                    Element sprite = (Element) sprites.item(sprite_count);

                    if(sprite.getAttribute("id").equals(id)){
                        System.out.print("Load_Tile: [OK : " + sprite.getElementsByTagName("path").item(0).getTextContent() + "]" + "\n");
                        String template = sprite.getElementsByTagName("path").item(0).getTextContent();
                        //return sprite.getElementsByTagName("path").item(0).getTextContent();
                        //System.out.print(template + "\n");
                        return  template;
                    }
                }
            }
        }
        System.out.print("Error for load tile: " + weatherType + " " + id + "\n");
        return "";
    }

    /**
     *  Metodo che cerca path dei sprite considerati
     * @param Team tipo di squadra
     * @param id nome di sprite
     * @return path del file
     */
    public String LoadSprite(String Team,String id)
    {
        //<Actors>
        NodeList actors = documentRes.getElementsByTagName("Actors");
        for(int i = 0; i < actors.getLength();i++)
        {
            Element actorsRoot = (Element) actors.item(i);
            //<team>
            NodeList team = actorsRoot.getElementsByTagName("team");

            for(int teamcount = 0;teamcount<team.getLength();teamcount++)
            {
                Element groupElement = (Element) team.item(teamcount);

                if(groupElement.getAttribute("id").equals(Team))
                {
                    //<sprites>
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
        System.out.print("Error for load resource: " + Team + " " + id + "\n");
        return "";
    }

    /**
     *  Metodo che decodifica la stringa che contiene il livelo nel file levels.xml
     * @param text simbolo
     * @return nome del sprite decodificato nel simbolo
     */
    public String checkAnnotation(char text)
    {
        //<Annotations>
        NodeList annotations = documentRes.getElementsByTagName("Annotations");

            Element annotationsRoot = (Element) annotations.item(0);
            //<annotation>
            NodeList annotationList = annotationsRoot.getElementsByTagName("annotation");

            for(int annotation_count = 0;annotation_count<annotationList.getLength();annotation_count++)
            {
                Element annElement = (Element) annotationList.item(annotation_count);
                //Da cambiare
                if(annElement.getAttribute("id").charAt(0) == (text))
                {
                    return annElement.getElementsByTagName("tile").item(0).getTextContent();
                }
            }


        System.out.print("Not found annotation: " + text + "\n");
        return "";
    }

    /**
     * Carica la stringa con livello dal file levels.xml
     * @param id identificativo di livello
     * @return stringa contenente il livello codificato
     */
    public String loadLevel(String id){
            //<Levels>
            Element root = (Element) documentLevel.getElementsByTagName("Levels").item(0);
            //<level>
            NodeList levels = root.getElementsByTagName("level");

            for(int i = 0;i< levels.getLength();i++){
                Element level = (Element) levels.item(i);

                if(level.getAttribute("id").equals(id)){
                    String levelTemplate = level.getTextContent();
                    System.out.print(formatString(levelTemplate));
                    return formatString(levelTemplate);
                }
            }

        System.out.print("Not found level:" + id + "\n");
        return "";
    }

    /**
     * Metodo che formatta la string eliminando gli spazi vuoti
     * @param string testo da formattare
     * @return stringa formattata
     */
    private String formatString(String string){
        String temp = "";
        for(int i = 0;i<string.length();i++){
            if(string.charAt(i) != ' ' &&  string.charAt(i) !='\n'){
                temp+=string.charAt(i);
            }
        }
        return temp;
    }

}
