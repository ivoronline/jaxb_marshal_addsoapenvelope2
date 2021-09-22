import org.w3c.dom.Document;

import javax.xml.soap.SOAPMessage;

public class AddSoapEnvelope {

  static String fileXML  = "Person.xml";
  static String fileSOAP = "src/main/resources/PersonSOAP.xml";

  //=======================================================================================
  // MAIN
  //=======================================================================================
  public static void main(String[] args) throws Exception {

    //MAIN CODE TO CREATE SOAP FILE
    Document    xmlDocument  = UtilXML.fileToDocument(fileXML);
    SOAPMessage soapMessage  = UtilSOAP.createSOAP(xmlDocument);
                               UtilSOAP.SOAPToFile(fileSOAP, soapMessage);

    //JUST TO DEMONSTRATE CONVERSIONS
    Document    soapDocument = UtilSOAP.SOAPToDocument(soapMessage);
    String      soapString   = UtilSOAP.SOAPToString(soapMessage);

    //DISPLAY XML & SOAP FILES
    Document xmlDocument2 = UtilXML.fileToDocument(fileXML);
    String   xmlString    = UtilXML.documentToString(xmlDocument2);
    System.out.println("\n ======== Person.xml ========     \n" + xmlString);
    System.out.println("\n ======== PersonSOOP.xml ======== \n" + soapString);

  }

}
