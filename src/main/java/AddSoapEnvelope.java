import org.w3c.dom.Document;

import javax.xml.soap.SOAPMessage;

public class AddSoapEnvelope {

  //PROPERTIES
  static String fileXML  = "Person.xml";
  static String fileSOAP = "src/main/resources/PersonSOAP.xml";

  //=======================================================================================
  // MAIN
  //=======================================================================================
  public static void main(String[] args) throws Exception {

    //MAIN CODE TO CREATE SOAP FILE
    Document    xmlDocument  = UtilXML.fileToDocument(fileXML);
    SOAPMessage soapMessage  = UtilSOAP.XMLDocumentToSOAPMessage(xmlDocument);
                               UtilSOAP.SOAPMessageToSOAPFile(fileSOAP, soapMessage);

    //JUST TO DEMONSTRATE CONVERSIONS
    Document    soapDocument = UtilSOAP.SOAPMessageToSOAPDocument(soapMessage);
    String      soapString   = UtilSOAP.SOAPMessageToSOAPString(soapMessage);

    //DISPLAY XML & SOAP FILES
    Document xmlDocument2 = UtilXML.fileToDocument(fileXML);
    String   xmlString    = UtilXML.documentToString(xmlDocument2);
    System.out.println("\n ======== Person.xml ========     \n" + xmlString);
    System.out.println("\n ======== PersonSOOP.xml ======== \n" + soapString);

  }

}
