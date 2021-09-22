import org.w3c.dom.Document;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UtilSOAP {

  //=======================================================================================
  // CREATE SOAP
  //=======================================================================================
  public static SOAPMessage createSOAP(Document document) throws Exception {

    //ADD SOAP ENVELOPE
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
                soapMessage.getSOAPBody().addDocument(document);

    //RETURN SOAP MESSAGE
    return soapMessage;

  }

  //=======================================================================================
  // SOAP TO FILE
  //=======================================================================================
  public static void SOAPToFile(String fileName, SOAPMessage soapMessage) throws Exception {

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO FILE
    File             file = new File(fileName);
    FileOutputStream fos  = new FileOutputStream(file);
                     fos.write(outputStream.toByteArray());
                     fos.flush();

  }

  //=======================================================================================
  // SOAP TO DOCUMENT
  //=======================================================================================
  public static Document SOAPToDocument(SOAPMessage soapMessage) throws Exception {

    Source             src         = soapMessage.getSOAPPart().getContent();
    TransformerFactory tf          = TransformerFactory.newInstance();
    Transformer        transformer = tf.newTransformer();
    DOMResult          result      = new DOMResult();
                       transformer.transform(src, result);
    Document           document    = (Document) result.getNode();

    return document;

  }

  //=======================================================================================
  // SOAP TO STRING
  //=======================================================================================
  public static String SOAPToString(SOAPMessage soapMessage) throws Exception {

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO STRING
    String output = new String(outputStream.toByteArray());

    //RETURN STRING
    return output;

  }

}
