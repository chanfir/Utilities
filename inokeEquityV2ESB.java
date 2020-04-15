package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import netboldatacontract.nets.EquityTradeTicketInfo;
import netboldatacontract.nets.ObjectFactory;
import netbolservicelib.nets.AddEquityTradeTicket;
import netbolservicelib.nets.AddTradesResponse;
import netbolservicelib.nets.EquityTradeTicket;

public class inokeEquityV2ESB {

	public void invokeWS() throws Exception {
	URL wsdlURL2=null;
	String wsdlurl="http://localhost:6066/aaaa?WSDL";
	String wsdlESB= "http://localhost:8081/test/services/SOAP/V2/EquityService?wsdl";
	try {
		wsdlURL2 = new URL(wsdlESB);
	} catch (MalformedURLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
//	QName SERVICE_NAME2 = new QName("http://NETS.NetBolServiceLib", "EquityTradeTicket");
//	Service service2 = Service.create(wsdlURL2, SERVICE_NAME2);
//	EquityTradeTicket equityServ = service2.getPort(EquityTradeTicket.class);
//	AddTradesResponse resp=null;
//
//		AddEquityTradeTicket a=new AddEquityTradeTicket();
//		EquityTradeTicketInfo inf = new EquityTradeTicketInfo();
//		
//		
//		ObjectFactory factory = new ObjectFactory();
//		
//		JAXBElement<String> DSMOT = factory.createEquityTradeTicketInfoDSMOTIVOEXCEXTREJEITADA("sdfsdf");
//				
//		JAXBElement<String> CD_CLIENTE=factory.createEquityTradeTicketInfoCDCLIENTE("CUST1715");
//		inf.setDSMOTIVOEXCEXTREJEITADA(DSMOT);
//		inf.setCDCLIENTE(CD_CLIENTE);
//
//		
//		netbolservicelib.nets.ObjectFactory f = new netbolservicelib.nets.ObjectFactory();
//		
//		List <EquityTradeTicketInfo> L =f.createAddEquityTradeTicket().getEquityTradeTicket();
//		L.add(inf);
//		L.add(inf);
//		a.setEquityTradeTicket(L);
		
//		 Map<String, List<String>> credentials = new HashMap<String,List<String>>();
//
//		 credentials.put("username", Collections.singletonList("admin"));
//		 credentials.put("password", Collections.singletonList("admin"));
//
//		 ((BindingProvider)equityServ).getRequestContext().put(MessageContext.HTTP_REQUEST_HEADERS, credentials);
//		 
//		String loginPassword = "admin"+ ":" + "admin";
//		String encoded = new sun.misc.BASE64Encoder().encode (loginPassword.getBytes());
//		URLConnection conn = null;
//		try {
//			conn = wsdlURL2.openConnection();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		conn.setRequestProperty ("Authorization", "Basic " + encoded);
//		
//		resp=equityServ.addEquityTradeTicket(a);
		
	
	  System.out.println("Start sending  request");
	  URL url = new URL(wsdlESB);
	  HttpURLConnection rc = (HttpURLConnection)url.openConnection();
	  //System.out.println("Connection opened " + rc );

String userCredentials = "admin:admin";
	  String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

	  rc.setRequestMethod("POST");  
	  rc.setRequestProperty ("Authorization", basicAuth);
	  rc.setDoOutput( true );
	  rc.setDoInput( true ); 
	  rc.setRequestProperty( "Content-Type", "text/xml; charset=utf-8" );
	  String reqStr = "<?xml version=\"1.0\" ?><S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\"><S:Body><ns2:AddEquityTradeTicket xmlns=\"http://NETS.NetBolDataContract\" xmlns:ns2=\"http://NETS.NetBolServiceLib\" xmlns:ns3=\"http://schemas.microsoft.com/2003/10/Serialization/\"><ns2:equityTradeTicket><CD_CLIENTE>CUST1715</CD_CLIENTE><DS_MOTIVO_EXC_EXT_REJEITADA>sdfsdf</DS_MOTIVO_EXC_EXT_REJEITADA></ns2:equityTradeTicket><ns2:equityTradeTicket><CD_CLIENTE>CUST1715</CD_CLIENTE><DS_MOTIVO_EXC_EXT_REJEITADA>sdfsdf</DS_MOTIVO_EXC_EXT_REJEITADA></ns2:equityTradeTicket></ns2:AddEquityTradeTicket></S:Body></S:Envelope>"; // the filled out template of SOAP message
	  int len = reqStr.length();
	  rc.setRequestProperty( "Content-Length", Integer.toString( len ) );
	  rc.connect();    
	  OutputStreamWriter out = new OutputStreamWriter( rc.getOutputStream() ); 
	  out.write( reqStr, 0, len );
	  out.flush();
	  System.out.println("Request sent, reading response ");
	  InputStreamReader read = new InputStreamReader( rc.getInputStream() );
	   // dumpHeaders( rc, System.out ); 
	   // note that Content-Length is available at this point
	  StringBuilder sb = new StringBuilder();   
	  int ch = read.read();
	  while( ch != -1 ){
	    sb.append((char)ch);
	    ch = read.read();
	  }
	  String response = sb.toString();
	  read.close();
	  rc.disconnect();
	System.out.println("respone : " +response);
	}
	
}
