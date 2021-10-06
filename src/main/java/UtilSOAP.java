import org.w3c.dom.Document;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UtilSOAP {

  //#########################################################################################################
  //                                XML DOCUMENT <==> SOAP MESSAGE
  //#########################################################################################################

  //=======================================================================================
  // XML DOCUMENT TO SOAP MESSAGE
  //=======================================================================================
  public static SOAPMessage XMLDocumentToSOAPMessage(Document document) throws Exception {

    //ADD SOAP ENVELOPE
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage();
                soapMessage.getSOAPBody().addDocument(document);

    //RETURN SOAP MESSAGE
    return soapMessage;

  }

  //=======================================================================================
  // SOAP MESSAGE TO XML DOCUMENT
  //=======================================================================================
  public static Document SOAPMessageToXMLDocument(SOAPMessage soapMessage) throws Exception {

    //EXTRACT XML DOCUMENT
    Document responseDocument = soapMessage.getSOAPBody().extractContentAsDocument();

    //RETURN XML DOCUMENT
    return   responseDocument;

  }

  //#########################################################################################################
  //                     SOAP Document / String / File => SOAP Message
  //#########################################################################################################

  //=======================================================================================
  // SOAP DOCUMENT TO SOAP MESSAGE
  //=======================================================================================
  public static SOAPMessage SOAPDocumentToSOAPMessage(Document soapDocument) throws Exception {

    // SOAP DOCUMENT TO SOAP MESSAGE
    String      soapString  = UtilXML.documentToString(soapDocument);
    SOAPMessage soapMessage = SOAPStringToSOAPMessage(soapString);

    //RETURN SOAP MESSAGE
    return soapMessage;

  }

  //=======================================================================================
  // SOAP STRING TO SOAP MESSAGE
  //=======================================================================================
  public static SOAPMessage SOAPStringToSOAPMessage(String soapString) throws Exception {

    //CONVERT SOAP STRING TO SOAP MESSAGE
    InputStream inputStream = new ByteArrayInputStream(soapString.getBytes());
    SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null, inputStream);

    //RETURN STRING
    return soapMessage;

  }

  //=======================================================================================
  // SOAP FILE TO SOAP MESSAGE
  //=======================================================================================
  public static SOAPMessage SOAPFileToSOAPMessage(String soapFile) throws Exception {

    //CONVERT SOAP FILE TO SOAP MESSAGE
    Document    soapDocument = UtilXML.fileToDocument(soapFile);
    SOAPMessage soapMessage  = SOAPDocumentToSOAPMessage(soapDocument);

    //RETURN SOAP MESSAGE
    return soapMessage;

  }

  //#########################################################################################################
  //                         SOAP Message => SOAP Document / String / File
  //#########################################################################################################

  //=======================================================================================
  // SOAP MESSAGE TO SOAP DOCUMENT
  //=======================================================================================
  public static Document SOAPMessageToSOAPDocument(SOAPMessage soapMessage) throws Exception {

    //SOAP MESSAGE TO SOAP DOCUMENT
    Source             src         = soapMessage.getSOAPPart().getContent();
    TransformerFactory tf          = TransformerFactory.newInstance();
    Transformer        transformer = tf.newTransformer();
    DOMResult          result      = new DOMResult();
                       transformer.transform(src, result);
    Document           document    = (Document) result.getNode();

    //RETURN SOAP DOCUMENT
    return document;

  }

  //=======================================================================================
  // SOAP MESSAGE TO SOAP STRING
  //=======================================================================================
  public static String SOAPMessageToSOAPString(SOAPMessage soapMessage) throws Exception {

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO STRING
    String output = new String(outputStream.toByteArray());

    //RETURN STRING
    return output;

  }

  //=======================================================================================
  // SOAP MESSAGE TO SOAP FILE
  //=======================================================================================
  public static void SOAPMessageToSOAPFile(String fileName, SOAPMessage soapMessage) throws Exception {

    //CONVERT SOAP MESSAGE TO OUTPUT STREAM
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                          soapMessage.writeTo(outputStream);

    //STORE RESULT INTO FILE
    File             file = new File(fileName);
    FileOutputStream fos  = new FileOutputStream(file);
                     fos.write(outputStream.toByteArray());
                     fos.flush();

  }

}
