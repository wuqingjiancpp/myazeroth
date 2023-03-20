package com.accendl.web.test;

import org.apache.commons.codec.binary.Base32;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.encrypt.AesBytesEncryptor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@SpringBootTest
public class TestSecurity {
    // to sync your phone with the Google Authenticator secret, hand enter the value
//		// in base32Key
//		// String base32Key = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
//		// Base32 base32 = new Base32();
//		// byte[] b = base32.decode(base32Key);
//		// String secret = Hex.encodeHexString(b);

    //		String hexSecret = "80ed266dd80bcd32564f0f4aaa8d9b149a2b1eaa";
//		String encrypted = new String(Hex.encode(encryptor.encrypt(hexSecret.getBytes())));
//
    @Test
    public void testSecret(){
        String base32Key = "QDWSM3OYBPGTEVSPB5FKVDM3CSNCWHVK";
		 Base32 base32 = new Base32();
		 byte[] b = base32.decode(base32Key);

		 String secret = org.apache.commons.codec.binary.Hex.encodeHexString(b);
         Assert.isTrue("80ed266dd80bcd32564f0f4aaa8d9b149a2b1eaa".equals(secret), "secret不相等");
    }

    //		// the hashed password was calculated using the following code
//		// the hash should be done up front, so malicious users cannot discover the
//		// password
//		// PasswordEncoder encoder =
//		// PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		// String encodedPassword = encoder.encode("password");
//
//		// the raw password is "password"
//		String encodedPassword = "{bcrypt}$2a$10$h/AJueu7Xt9yh3qYuAXtk.WZJ544Uc2kdOKlHu2qQzCh/A3rq46qm";

    //		// the raw security answer is "smith"
//		String encodedSecurityAnswer = "{bcrypt}$2a$10$JIXMjAszy3RUu8y5T0zH0enGJCGumI8YE.K7w3wsM5xXDfeVIsJhq";
//
    @Test
    public void testPassword(){
		 PasswordEncoder encoder =
		 PasswordEncoderFactories.createDelegatingPasswordEncoder();
		 String encodedPassword = encoder.encode("password");
         System.out.println(encodedPassword);
//        {bcrypt}$2a$10$z5fDGh/BWDcOkZQZswVCS.uVaqmj6j8Eo6p1yvAEcMfyJ.YViJPT6

    }

    @Test
    public void testCreateUser(){
        User user = (User) User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        System.out.println(user.getPassword());
// {bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG
    }

    @Test
    public void testPasswordSaltEncrypted(){
        String hexSecret = "80ed266dd80bcd32564f0f4aaa8d9b149a2b1eaa";
        BytesEncryptor encryptor = new AesBytesEncryptor("password", hexSecret);


        String encrypted = new String(Hex.encode(encryptor.encrypt(hexSecret.getBytes())));
        System.out.println(encrypted);
//        ede4a2261923c9d62ae8cfd6556ba89cc6c2ea8e4e9f25b59b5877b6c0ca0591473377186379e7bae24c32c8d5de0a41
    }

    @Test
    public void testTODOEncrypted(){
//        BytesEncryptor encryptor = new AesBytesEncryptor()
    }
}
