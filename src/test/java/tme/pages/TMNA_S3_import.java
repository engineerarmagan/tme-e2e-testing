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

public class TMNA_S3_import {

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
        System.setProperty("aws.accessKeyId", "ASIAZWMA7YDJDZY33RUT");
        System.setProperty("aws.secretAccessKey", "x5Gg+pA2ZOEI5h1R6SRxG8uVpV5Oio6O+eYgFe9e");
        System.setProperty("aws.sessionToken", "IQoJb3JpZ2luX2VjEPP//////////wEaCWV1LXdlc3QtMSJHMEUCICVrjyYIm9w6g8ezkkGu4yrnBUAZNfCKhxOYI3i8UBGAAiEApOkW2OEmQYCvV/ZGKTTDVpdX/pvBSUj+Cu4cliaUWhQqrwMI7P//////////ARACGgw2NjY1MjczMTgyMjYiDH5Q8x5vGiKCMJxL5CqDA3j3NwfsdBklTW6ia6RnJGFx/phxJHhm31vdorfiakCLZMIr89sIo73jIF3qBssR6FyIQf9JWEPvON/C5w8nY6F9Xs7NLI8h81FUzxWI5MnGv/s8WasQyy5GIY4/v/wBDy1d4gBmLIxYgWCDZCLD+X36xn9qMRE7csg2ULXJcG3i1TyrGVfF6+nQVIzHeFHTtNlebVUTKgVSpOi5rObtyqO4AO1yShXH90JCmwnwe9iLhsC0hNrybZS1y7ZqDX9MS25T6vRZ9C4199HNytgoXF7sCM5Y/mJluRQIjDxPImG7t1yN1X2HBt6UTIaK6bdVOHB2ishjiYBuuOXYCStoRNimbGdGsCLpBDd1XuWv3yJtGdKAn2sDD33beFVNHHoM6fBMQ8JS0KEWLKKSL4cqNDOW+yTp72D1IUIekAcigqvBLbmh71FOfVCeiuV2KbPVLTt7Nhtsd9XQn2qVvtA/zgSqIsZ9hEpul1pUL29omwoQnJtz135K4WZDWByOrPOu1apEmjCm/YGVBjqmAfC/V8iNEn9cRHv8cPNkwkIHNS3ANye5PkdW1YunHOgCb0Nhs1t+8z++4XtLeXr4c3CLyKXPGTJQAwW/8Upuw4i/hGDMptFV1shKrXWl1gY4HZTwq3dkQ0epaD/3PM7/B1whxmTjhad4jSc02e5TWIUtNHlpW7gdmYn5jyqtdqn2REGvqCIDJPa68Bz4s3RqKTooKDdlRXv3nIZbGdfFNxCWb8tFTXY=");
        System.setProperty("aws.region", "eu-west-1");

        // String bucketName = "com.entitygroup.nge2e.dev.import";
        String bucketName= ConfigurationReader.get("tmna_bucketname");

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


