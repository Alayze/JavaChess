package Core;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
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

    //Da proteggere
    public static ResourceLoader getInstance(){
        return resourceLoader;
    }

    public String LoadResource(String type)
    {
        //System.out.println(document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("sprite");
        for(int i = 0; i < nodeList.getLength();i++){
            Element element = (Element) nodeList.item(i);
            if(element.getAttribute("id").equals(type))
                return element.getElementsByTagName("path").item(0).getTextContent();

        }
        /*Element element = (Element) document.getElementsByTagName("background").item(0);
        System.out.println(nodeList.getLength());
        if(element!=null)
            return element.getTextContent();
        else*/
            return "Error for load resource\n";
    }

}
