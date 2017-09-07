package calaru.util.pager;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class AESHelperEncriptor 
{
    
    private static byte[] raw = {-31,   16,   7,  -34,  59, -61, -60,  -16, 
                                  26,   87, -35,  114,   0, -53,  99, -116, 
                                 -82, -122,  68,   47,  -3, -17, -21,  -82, 
                                 -50,  126, 119, -106, -119, -5, 109,   98};

    private SecretKeySpec skeySpec;
    private Cipher ecipher;
    private Cipher dcipher;

    public AESHelperEncriptor() 
    {
        try 
        {
            skeySpec = new SecretKeySpec(raw, "AES");
            // Instantiate the cipher
            ecipher = Cipher.getInstance("AES");
            dcipher = Cipher.getInstance("AES");
            ecipher.init(Cipher.ENCRYPT_MODE, skeySpec);         
            dcipher.init(Cipher.DECRYPT_MODE, skeySpec);
        } 
        catch (NoSuchAlgorithmException e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        } 
        catch (NoSuchPaddingException e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
   
    private String llenarCon16BlancosAlFinal(String str) 
    {
        if ((str.length() % 16 ) != 0)
        {
            int blancos = 16 - (str.length() % 16);
            String blancosdelfinal = new String();
            for (int i = 0; i < blancos; i++) 
            {
                blancosdelfinal = blancosdelfinal + " ";
            }
            str = str.concat(blancosdelfinal);
        }
        return str;
    }

    public String encrip(String msg)
    {
        try 
        {           
            String msglleno = llenarCon16BlancosAlFinal(msg);
            // Encode the string into bytes using utf-8
            byte[] utf8 = msglleno.getBytes("UTF8");
            byte[] encrypted = ecipher.doFinal(utf8);
            // Encode bytes to base64 to get a string
            return (new sun.misc.BASE64Encoder().encode(encrypted));
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public String decrypt(String msg)
    {
        try 
        {
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(msg);
            // Decrypt
            byte[] utf8 = dcipher.doFinal(dec);
            // Decode using utf-8
            return (new String(utf8, "UTF8")).trim();
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
