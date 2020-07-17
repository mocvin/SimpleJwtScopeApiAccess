package it.mocciavincenzo.enel.wso2.JWTAuthentication.auth.tools;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;

@Component
public class KeysManagement {

    @Value("${keystore.password}")
    private String password;

    @Value("${keystore.file}")
    private String keystore;

    public PublicKey ketPublicKey(String alias) {
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream readKeystoreStream = new FileInputStream(keystore);
            ks.load(readKeystoreStream, password.toCharArray());
            Certificate certificate = ks.getCertificate(alias);
            return certificate.getPublicKey();
        } catch (NoSuchAlgorithmException | KeyStoreException | IOException | CertificateException e) {
            e.printStackTrace();
        }

        return  null;
    }
}
