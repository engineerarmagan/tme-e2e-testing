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
        SimpleDateFormat dateFormat=new SimpleDateFormat("ddMMhhmmss");
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
          System.setProperty("aws.accessKeyId", "ASIATAMOP7BA6KXHDRNS");
          System.setProperty("aws.secretAccessKey", "QG2tN1H2EI5G7d64xLEgxf3DXwCKTfxDfEWpghVa");
          System.setProperty("aws.sessionToken", "IQoJb3JpZ2luX2VjEGsaCWV1LXdlc3QtMSJGMEQCICmgyS+5XMkSpnftc7sH5lIa5Nssy/nXDYKRs2zrc0lHAiAIO1jZrjQ4g80JCYwgRLaUCt5C0MVaDzqdX53cKlM2jCqmAwhzEAQaDDIwNjk5NDE0MzI5NyIMRArrE159KIzNQBAMKoMDBz3XkyuXz3Ed6q3AazsjtVtSDXYOVftX+7pJe3C/A7y61WePy3VhptFg38xBjcIFTxrFCMsIvXGQXNQO3WL1JzhsSR3HaC0IAFItwQ02+O04cOIfyDuORDqPy414MbA7YRjjAUxrOZmYTZspjYQ9EfsecME2NJAMrskUt4xL3Nij4ZxONAYAPp9rAIBdEnJL9kVb5o8evONTOyIVLhZhM71cu5vUyTm83EFw4Hy6aiVArdnHcxuMsxcbs+krV2UgmtZseJ/tY1Q3e3vODIMNmzKD8si/dsAp7CvuDZvav949arLii5BQlT8s8y4NG0gmIHvtOm/JHNzdZd0gYSHHasn4ek93UO9Bo4kshHlp/t9pHar/3Pzff8iopsc2ckZ1o1m+1+1HZDdqX4K+GlZNED5AL5vVTuHVU8CUinKcbTdw0kYANwE8TLRa2wkkAoWDv3USFXQsW+GhothDIbclusWPXxqw9cZVAoqCQsi8dTTPeEaWxb4SID5efU92i6rN712bML6dnJUGOqcBCqTBDiRqhHzxjflGA4mkhFeuzFqOy8DlUmbZAGeLtATAkDQMcMQMULp8Hh/Ifkw2YQMMBuBXmXB1lSON2bpnwHJD6t0HwfTIqtlfWqaKd2Bvfm9NfUl1ebuYGNDAdfTpAcC/f2jea/fBqsBdY5CB0ff1Va9L0ab+TPQswYg1jLpWqAvT70pADPyUAxTRz4yR/4x/wrszxtXprZdSaTZezKGap5PB49U=");
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
