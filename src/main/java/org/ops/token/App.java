package org.ops.token;

import java.util.Map;
import java.util.LinkedHashMap;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.Serializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.undercouch.bson4jackson.BsonFactory;
import de.undercouch.bson4jackson.BsonModule;

import org.apache.commons.codec.binary.Base64;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void Test ()
    {
        Map<String, Object> data = new LinkedHashMap<String, Object>();
    
        data.put("ver", "0.0.3");
        data.put("doc", "1234567890");
        data.put("sig", "abcdefghij");
   
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectMapper om = new ObjectMapper(new BsonFactory());
            om.registerModule(new BsonModule());
            om.writeValue(baos, data);
      
            byte[] r = baos.toByteArray();

            AuthKey authKey = new AuthKey();
            authKey.signature = new byte[0];
            authKey.data = r;

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            DataOutputStream oos = new DataOutputStream(bos);
            AuthKeyProtocolOutputStream aos = new AuthKeyProtocolOutputStream(oos);
 
            aos.writeData(authKey);
            byte[] serializedAuthKey = bos.toByteArray();

            System.out.println(Base64.encodeBase64String(serializedAuthKey));
        }
        catch (java.io.IOException e )
        {
            System.out.println(e);
        }
    }


    public static void main( String[] args )
    {
        Test();
    }
}
