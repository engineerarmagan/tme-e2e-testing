package tme.pages;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.nio.file.Paths;

public class TMNA_S3_import {

    public static void main(String[] args) {
        //public void upload_to_S3_tmna(){
        System.setProperty("aws.accessKeyId", "ASIATAMOP7BA3LYVQTGA");
        System.setProperty("aws.secretAccessKey", "Wr4WMDEEjsuHbx7l5CifZMRsoqbt3euo75nwpSmy");
        System.setProperty("aws.sessionToken","IQoJb3JpZ2luX2VjECEaCWV1LXdlc3QtMSJHMEUCID9m78CG4uUSk/xEtG6YqIcSbiDptvGm8cT5H9k9fMYuAiEAoTkecUwmV/K7qksUwMEZFoaCqSY7tO8XC/6yWrenXxEqrwMI6v//////////ARAEGgwyMDY5OTQxNDMyOTciDMmtzw8RTPWEjrR2oyqDAwIvijhMvAF6p3hpeICFHV0VQa6dNdltKkQbQRTjvBw17GfFH6LDyW4v4EUryrCey9slWn1pRJkYowkoknuWmuZFtzxhRC5IIIQIv8DDMx1sGrKl0/XZNz8OMBUsm5vQLhOKNQz/gxv1RGuazhck2evwNlsdbv6cjJYFKThF7RT2FF89PjrTbmZzUTGDqZeRe9Q9kg/w5DQRgPQQOa/1M8iogPV21ub1G68DeArQ7b8Ra4jYNdy+ZJd+z2Q30U7S84rXDHxqhc6Y7Tf7RLTiBYttrf29jCLpE7b09DMK2oHCY97TrmdYQWu1MeEIH22UwjI0UDrXNJK2qQNWtjgDWsPrRoLbAqlwnV3RAwY1iuO6Q0BpRbza5FDoZO3hjeTy2XwIZSzNS5XPZDr5gXAEiDbOmwxpFb54fk1RAj52+Dj4cmxm1bLKt4Sy/VXu82Xk13Y13Cm1l8hUul0Va55dGEZiccW1j53NMVc+gv7u/fjJgex8n7Iubo1o30qtI0tcyDws9jCFrOOTBjqmAdw05MXWad6JPD28o4fbJUfjtDHn17wKcViTMjPXbLEbhDOc0gmbZ2t3Pus7/cgJBLddqJHdkQtmtn4h92T4wUaGHh1TcEvSlZ1qvTlyxSmxj1Zm8okaott+0K2UNwuf4jzDaJSQ6S4BMyuKDTfefZM+ZbNdLF2dSCuOZ5a6/I/z9PCK2fN/WdjBcdWMMs4kQK/tPna3Hj4xpLDeZA+YHop+n4LwPNQ=");
        System.setProperty("aws.region","eu-west-1");

        String bucketName = "com.viqtordavis.tmna-nge2e.s.import";
        String folder_name="12Prius_BMY020U_EN_12-12-05_UX";
        String filePathzip = "C:/Users/armagan.dursun/Desktop/12Prius_BMY020U_EN_12-12-05_UX.zip";
        String filePathmet="C:/Users/armagan.dursun/Desktop/12Prius_BMY020U_EN_12-12-05_UX.met";
        String keyzip = "upload/"+ folder_name+"/"+ Paths.get(filePathzip).getFileName().toString();
        String keymet = "upload/"+ folder_name+"/"+Paths.get(filePathmet).getFileName().toString();
        S3Client s3Client = S3Client
                .builder()
                .build();
        //upload zip file
        PutObjectRequest putObjectRequestzip;
        putObjectRequestzip = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(keyzip)
                .build();
        PutObjectResponse putObjectResponsezip = s3Client.putObject(putObjectRequestzip, Paths.get(filePathzip));
        System.out.println("zip uploaded="+putObjectResponsezip.sdkHttpResponse().isSuccessful());

        //upload met file
        PutObjectRequest putObjectRequestmet;
        putObjectRequestmet = PutObjectRequest
                .builder()
                .bucket(bucketName)
                .key(keymet)
                .build();
        PutObjectResponse putObjectResponsemet = s3Client.putObject(putObjectRequestmet, Paths.get(filePathmet));
        System.out.println("met uploded="+putObjectResponsemet.sdkHttpResponse().isSuccessful());

    }

}

