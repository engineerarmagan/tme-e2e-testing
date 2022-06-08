package tme.pages;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import tme.utilities.BrowserUtils;
import tme.utilities.ConfigurationReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Import_publications {
static String zip_path;
static String met_path;
static String folder_name;
static String new_zip_path;
static String new_met_path;



    public void get_files_inside_the_folder(String type){
    Path path= Paths.get("C:\\Users\\armagan.dursun\\Desktop\\tme-e2e-testing\\"+type);
    try (Stream<Path> subPaths= Files.walk(path,1)){
        List<String> files=subPaths.filter(Files::isRegularFile).map(Objects::toString).collect(Collectors.toList());
        zip_path= files.get(1);
        met_path = files.get(0);

    } catch (IOException e) {
        e.printStackTrace();
    }
}


    public void rename_file(String type) {
        //get current date and time
        Date currenDate=new Date();
        System.out.println(currenDate);
        SimpleDateFormat dateFormat=new SimpleDateFormat("mmddhhmmss");
        String date=dateFormat.format(currenDate);

        //get zip file name and path
        String separator = "\\";
        String zip_old_file_name = zip_path.replaceAll(Pattern.quote(separator), "\\\\").split(type+"\\\\")[1].split("ad")[0];
        String zip_new_file_name=zip_old_file_name+"ad"+date+".zip";

        new_zip_path=zip_path.split(type)[0]+type+"\\"+zip_new_file_name;


        //change zip file name
        File oldFile=new File(zip_path);
        File newFile=new File(new_zip_path);
        if(oldFile.renameTo(newFile)){
        System.out.println("zip file renamed");

        }else{
        System.out.println("not renamed");
         }
        //get met file name and path
        String met_old_file_name = met_path.replaceAll(Pattern.quote(separator), "\\\\").split(type+"\\\\")[1].split("ad")[0];
        String met_new_file_name=met_old_file_name+"ad"+date+".met";
        System.out.println("met new file name="+met_new_file_name);

        new_met_path=met_path.split(type)[0]+type+"\\"+met_new_file_name;

        folder_name=met_old_file_name+"ad"+date;

        //change met file name
        File old_met=new File(met_path);
        File new_met=new File(new_met_path);
        if(old_met.renameTo(new_met)){
            System.out.println("met file renamed");

        }else{
            System.out.println("not renamed");
        }

}
    public void upload_to_S3(){
          System.setProperty("aws.accessKeyId", "ASIATAMOP7BA36KSVO6U");
          System.setProperty("aws.secretAccessKey", "r62wDBzKGBH9NWTrnsd+x/6VHRN/gIOGL5Yw1pSm");
          System.setProperty("aws.sessionToken", "IQoJb3JpZ2luX2VjENj//////////wEaCWV1LXdlc3QtMSJHMEUCIDvo2mkKesKnnuZviL5adV5xQO7X3mYTxBOc37UCkGIDAiEA18KS1HXxlxHNJ3WCJu+BRXpBEtRglCb60TChQ3E+7N8qrwMI0f//////////ARAEGgwyMDY5OTQxNDMyOTciDEu8aWaB9+BocygkpSqDA2iciLNqf8uuj/+3TID0FoDJZCBh2RNgTgXNWeq/a/AgAxg2itnTBLqTFxMeL8NZRqRTyMZNqYhspDwnneA+uUP9RFe8UOr+Kfc64MMeFfER8qmjWKDHI8LfxMKPNPeeP0TUhKENMWY2bhcBGrZPtKa7EELvtebkZBfvcMrsadgO8yNKI++u8GfPT1gksUhJiOYPVUEQN1OsJhDwfyTSa3LxeiWljs0Pt6yjdtdgFuK+ABglS+u2VAlLDh2ywtfs7JLQNLGG2O2QXEz76qlKSkmhwWXbBrWD6fkhNbvGgOI3YPHAdTYRJdx/1gfyoRDCtZP8r3nTjrNGBzcdghED9eAoWqT/qBj7whL7DwSEOE9MgHF8Fq6BJfyYO6gOWODRouztepw2n4Y6dcJq5m1+V4C4gbyX8zNFcB3eWqbo9NNqqRIi38nh/jDVGjaiRIjyOk9kY9RzONv7gbt+h5oJzn93GBCgxrzewuumBqwcu3mHbZzXou6Sx2GMA5LqMc9F88E75DCCjPyUBjqmAWYxyX3aLe+Qp9rxvNMp9J1DeBlqhWXe58pV0Vp2blH+UUPXFbL71DnCgNGu2BUPVsmz8jwCZAAPOUjPc2tXTQ86t0fNUIqiqVVu8Xz5+VZvJXI90d7kfmb1VgZbrn0xoSjZNvUQc11msE184OKY0iaCsVWedqFHD7k5xg6wiSBYnXmdRRA/+05tsPJube6EIwwIbqUSUC1mNNqW3ResuDZfqvngIMY=");
          System.setProperty("aws.region", "eu-west-1");

   // String bucketName = "com.entitygroup.nge2e.dev.import";
    String bucketName= ConfigurationReader.get("bucketname");

    String keyzip = "upload/" + folder_name + "/" + Paths.get(new_zip_path).getFileName().toString();
    String keymet = "upload/" + folder_name + "/" + Paths.get(new_met_path).getFileName().toString();
    S3Client s3Client = S3Client.builder().build();
    //upload zip file
    PutObjectRequest putObjectRequestzip;
    putObjectRequestzip = PutObjectRequest
            .builder()
            .bucket(bucketName)
                  .key(keyzip)
                  .build();
    PutObjectResponse putObjectResponsezip = s3Client.putObject(putObjectRequestzip, Paths.get(new_zip_path));
          System.out.println("zip uploaded=" + putObjectResponsezip.sdkHttpResponse().isSuccessful());

    //upload met file
    PutObjectRequest putObjectRequestmet;
    putObjectRequestmet = PutObjectRequest
            .builder()
            .bucket(bucketName)
                  .key(keymet)
                  .build();
    PutObjectResponse putObjectResponsemet = s3Client.putObject(putObjectRequestmet, Paths.get(new_met_path));
          System.out.println("met uploded=" + putObjectResponsemet.sdkHttpResponse().isSuccessful());
        BrowserUtils.waitFor(120);
}


}
