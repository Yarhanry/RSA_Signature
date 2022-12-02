package RSA;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.*;

public class SignatureData {

    public static final String generateSignedMessage(String Message,
                                                     String prikeyvalue) {  // 输入：字符串信息 输出：包含签名的字符串信息
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                    BitByte.hexStrToBytes(prikeyvalue));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey myprikey = keyf.generatePrivate(priPKCS8);

            Signature signet = Signature.getInstance("SHA512withRSA");
            signet.initSign(myprikey);
            signet.update(Message.getBytes("ISO-8859-1"));
            byte[] signed = signet.sign(); // 对信息的数字签名
            String signedmessage = BitByte.bytesToHexStr(signed);
            System.out.println("original message:" + Message);
            System.out.println("signed message  :" + signedmessage);
            System.out.println("签名并生成文件成功");
            return signedmessage;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("签名并生成文件失败");
        }
        return null;
    }

}
