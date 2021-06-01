import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringWriter;

public class AddSoapEnvelope {

  //=======================================================================================
  // MAIN
  //=======================================================================================
  public static void main(String[] args) throws Exception {

    //READ XML FROM FILE
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
                           documentBuilderFactory.setNamespaceAware(true); //To avoid "local part cannot be "null""
    Document document    = documentBuilderFactory.newDocumentBuilder().parse("src/main/resources/Person.xml");

    //ADD SOAP ENVELOPE
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
                soapMessage.getSOAPBody().addDocument(document);

    //CONVERT SOAP MESSAGE TO DOCUMENT
    Document documentSOAP = SOAPMessagetoDocument(soapMessage); //Not used. Just to demonstrate.

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO STRING
    String output = new String(outputStream.toByteArray());
    System.out.println(output); //Display converted XML

    //STORE RESULT INTO FILE
    File              file = new File("src/main/resources/PersonSOAP.xml");
    FileOutputStream  fos = new FileOutputStream(file);
                      fos.write(outputStream.toByteArray());
                      fos.flush();

  }

  //=======================================================================================
  // SOAP MESSAGE TO DOCUMENT
  //=======================================================================================
  public static Document SOAPMessagetoDocument(SOAPMessage soapMsg) throws Exception {
  Source             src         = soapMsg.getSOAPPart().getContent();
  TransformerFactory tf          = TransformerFactory.newInstance();
  Transformer        transformer = tf.newTransformer();
  DOMResult          result      = new DOMResult();
                     transformer.transform(src, result);
  Document           document    = (Document) result.getNode();
  return document;
}

  //=======================================================================================
  // DOCUMENT TO STRING
  //=======================================================================================
  private static String documentToString(final Document responseDoc) throws Exception {

    DOMSource          domSource = new DOMSource(responseDoc);
    StringWriter       stringWriter = new StringWriter();
    StreamResult       streamResult = new StreamResult(stringWriter);
    TransformerFactory transformerFactory = TransformerFactory.newInstance();
    Transformer        transformer;
                       transformer = transformerFactory.newTransformer();
                       transformer.transform(domSource, streamResult);

    return stringWriter.toString();

  }

}
