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
    private File xmlFile;
    private DocumentBuilderFactory dbFactory;
    private DocumentBuilder dBuilder;
    private Document document;

    private ResourceLoader(){
        xmlFile = new File("src/Resources.xml");
        dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            document = dBuilder.parse(xmlFile);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private static final ResourceLoader resourceLoader = new ResourceLoader();

    /**
     * Metodo che torna l'istanza della classe
     * @return
     */
    public static ResourceLoader getInstance(){
        return resourceLoader;
    }

    /**
     *
     * @param type
     * @return
     */
    public String LoadResource(String id,String type)
    {
        NodeList nodeList = document.getElementsByTagName(type);
        for(int i = 0; i < nodeList.getLength();i++){
            Element element = (Element) nodeList.item(i);
            if(element.getAttribute("id").equals(id))
                return element.getElementsByTagName("path").item(0).getTextContent();

        }
            return "Error for load resource\n";
    }

}
