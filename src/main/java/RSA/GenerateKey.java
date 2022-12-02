package RSA;

import java.security.*;

public class GenerateKey {
    private String priKey;
    private String pubKey;
    public String getPriKey() {
        return priKey;
    }
    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }
    public String getPubKey() {
        return pubKey;
    }
    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public GenerateKey() {
        generateKeys();// 在构造函数中就将密钥对算出并赋值给对象的priKey和pubKey属性
    }

    public void generateKeys() {  // 产生私钥和公钥对,并赋值给对象的priKey和pubKey属性
        try {
            KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
            KeyPair keys = keygen.genKeyPair();
            PublicKey pubkey = keys.getPublic();
            PrivateKey prikey = keys.getPrivate();
            pubKey = BitByte.bytesToHexStr(pubkey.getEncoded());
            priKey = BitByte.bytesToHexStr(prikey.getEncoded());
            System.out.println("pubKey=" + pubKey);
            System.out.println("priKey=" + priKey);
            System.out.println("写入对象 pubkeys ok");
            System.out.println("生成密钥对成功");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("生成密钥对失败");
        }
    }

}
