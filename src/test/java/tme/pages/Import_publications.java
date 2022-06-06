package tme.pages;

import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import tme.utilities.BrowserUtils;

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
        System.out.println("zip_path="+zip_path);
        System.out.println("met_path="+met_path);
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
        System.out.println("zip new file name="+zip_new_file_name);
        new_zip_path=zip_path.split(type)[0]+type+"\\"+zip_new_file_name;
        System.out.println("new_zip_path = " + new_zip_path);

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
        System.out.println("new_met_path = " + new_met_path);
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
          System.setProperty("aws.accessKeyId", "ASIATAMOP7BAS7SRYQJT");
          System.setProperty("aws.secretAccessKey", "usNoS5UNbgJSHGQdYJQWI+w2FAhKWIgv5plsrIMo");
          System.setProperty("aws.sessionToken", "IQoJb3JpZ2luX2VjEMH//////////wEaCWV1LXdlc3QtMSJGMEQCIFWcNkYRy2rwBzreDhFFW6rn94XHlS0noqQ+yI3ccH4vAiA1hh/5RLTQ4JPc94NezllpULEjLK89E6sXHPLCPJ+NHyqvAwi5//////////8BEAQaDDIwNjk5NDE0MzI5NyIMk/stSvofjPXXaCieKoMDAL6qN/bKWIhvLUJvPdikqC9kPaFHai+pAkjcXMgYFlIjgkjLxQhTDojyuD3B9nHmnqYl4KxqFTL+t6Yv0cYSB4oiUMDI5PyT9orX42XYbLmu7XYoTDVDjtBYy8njvCiA1CqXfMx0lIH20g+IIr4zwQ9vfbegnU0973CmVr60u+Z0k6LnuR2UgHcwdMvbVWbE0cJgQTo5Z44v3V3v2wvy25sLM5ZE6QKgfRKDuux4D7sm4onCNgNPO7QH8iYUx2d3HWgw6mRTb3m+TAXV0y6lyAtzcJD2gzQVEIjYbpeXf3+aLmE8fijctww0nuEdnwmLNcNXGiPyvxfn2ACkZNA703yCH5qM5gMryzK3nu7TvCsLn3VauVNmssIgoVWZooPZZo4Qk3qgiTRsl+uIMzCJ4Yg9FsOc04bZIboLkNI31jOWBGow4vNfTNGY3WZYCF32Hx55hUI6ORlX7uA1hKdLaAKNxsO7s7AU6Did5yvtF7/hAuPCb4DvUt1tOjUgHRzmfKQ+MPvw9pQGOqcB4S7J2neLA201KnsPzCHIUkjOviARhaLto6tEmx1itBCtKhGxK4qnylNhTz0/AFytv9X0Ra2DvvnsOzzCCbYmVqftGlqnD+EnFEsxGyi1wDSTkUI5rfN3BP23qNAAa+97V+c0f1GuRHGc6yFEkPxsINmNg0OSvgm5aCN4VVetvTnCbMVyxfzLl10zrZ0+7UckGSzAIAfYMu2vnsW70QVRC827+Dnob0c=");
          System.setProperty("aws.region", "eu-west-1");

    String bucketName = "com.entitygroup.nge2e.dev.import";

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
        BrowserUtils.waitFor(60);
}


}
