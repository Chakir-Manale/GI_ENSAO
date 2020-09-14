import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.Options;

import javax.xml.rpc.ParameterMode;

public class CalcClient {
   public static void main(String [] args) throws Exception {
       Options options = new Options(args);

       String endpoint = "http://localhost:8080/axis/Calculator.jws";

      // Do argument checking
       args = options.getRemainingArgs();

       if (args == null || args.length != 3) {
           
           return;
       }

       String method = args[0];
       if (!(method.equals("add") || method.equals("substract"))) {
          
           return;
       }

      // Make the call
       Integer i1 = new Integer(args[1]);
       Integer i2 = new Integer(args[2]);

       Service  service = new Service();
       Call     call    = (Call) service.createCall();

       call.setTargetEndpointAddress(new java.net.URL(endpoint));
       call.setOperationName( method );
       call.addParameter("op1", XMLType.XSD_INT, ParameterMode.IN);
       call.addParameter("op2", XMLType.XSD_INT, ParameterMode.IN);
       call.setReturnType(XMLType.XSD_INT);

       Integer ret = (Integer) call.invoke( new Object [] { i1, i2 });

       System.out.println("Got result : " + ret);
   }
}