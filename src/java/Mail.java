
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import java.util.ArrayList;
import java.util.Arrays;
import javax.ws.rs.core.MediaType;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esmaeil
 */
public class Mail {
    public static ClientResponse SendSimpleMessage() {
          Client client = Client.create();
          client.addFilter(new HTTPBasicAuthFilter("api",
                          "key-dcaed55f77eceee186db3535222cffd8"));
          WebResource webResource =
                  client.resource("https://api.mailgun.net/v3/sandboxb6e6d88025f040e7a219ca38aa018d57.mailgun.org" +
                                  "/messages");
          MultivaluedMapImpl formData = new MultivaluedMapImpl();
          formData.add("from", "<esmaeil414@gmail.com>");
          formData.add("to", "bar@example.com");
          formData.add("to", "gholami.esmaeil@yahoo.com");
          formData.add("subject", "OK?!");
          formData.add("text", "Testing some Mailgun awesomness!");
          return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                  post(ClientResponse.class, formData);
   }
   public static ClientResponse SendSimpleMessage(String senderName, String sender, String receivers, String subject,String text) {
          Client client = Client.create();
          client.addFilter(new HTTPBasicAuthFilter("api",
                          "key-dcaed55f77eceee186db3535222cffd8"));
          WebResource webResource =
                  client.resource("https://api.mailgun.net/v3/sandboxb6e6d88025f040e7a219ca38aa018d57.mailgun.org" +
                                  "/messages");
          MultivaluedMapImpl formData = new MultivaluedMapImpl();
          
          formData.add("from", senderName+" <"+sender+">");
          ArrayList<String> receiversList = new ArrayList<String>(Arrays.asList(receivers.split(",")));
          for(String r:receiversList){
            formData.add("to", r);
          }
          //formData.add("to", "gholami.esmaeil@yahoo.com");
          formData.add("subject", subject);
          formData.add("text", text);
          return webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
                  post(ClientResponse.class, formData);
   }
    public static void main(String[] args) {
         ClientResponse clientResponse=Mail.SendSimpleMessage();
         System.out.println("EEEEE"+clientResponse.toString());
    }
}
