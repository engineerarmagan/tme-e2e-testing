package tme.pages;


import software.amazon.awssdk.services.s3.S3Client;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import java.nio.file.Paths;




public class S3_delete {
    public void set_properties(){
        System.setProperty("aws.accessKeyId", "ASIATAMOP7BA54UI7HYJ");
        System.setProperty("aws.secretAccessKey", "d+EB18Kur2DqQkvr4ag6ot+TnTLz60vjIt2KN7fZ");
        System.setProperty("aws.sessionToken", "IQoJb3JpZ2luX2VjEOb//////////wEaCWV1LXdlc3QtMSJIMEYCIQDjyL64w6aGPmHelZ/MTaeYubcYtmBbNQ1QEJnl+fTbWgIhAIR1zWmkDlAGRv/dVOBWbCPqMJztmf/JoY7gXGSBv5rFKq8DCL///////////wEQBBoMMjA2OTk0MTQzMjk3IgxOgO+evZqvfIINKnAqgwPALakrB89Kdmz5ZPDykdGnv4bolrtRITCqMS27yejwTITClOY2K8QmhcQnt7JYfolU43geg2P2fvjDzuwIvLD36I98jcf3CzTuJFT0RT/tOTA6otNQhR2x9WjCK/NTT4J6sVpu7VH12ltEUpr5zzocPE/duPTwJXaz/WNLr0vD6dHw9c00eyD/QfirxAAGNWSrV3GlfZxBsG8WTdmgK0FmJwGZq1/fTtYkf9a1jnOmiyzMDMlGTQTJFYZ7z0hCGHfJFrR2M2e+M+0r3DlXF1ZivFyzOSV0Zez+F71lBBF6N2E+ksQyL8lJeO29usZXd3BbzaYmifYJ8XcLS0QHthywxnOx+DtazLGb12ZsPj3hkzjdRBX6O/J2Z9c7v5wy90P+Z6TBaWuTBVKp5fG2uEiI6Nixk8aUoqHrM91ePfjVZc44ZBB48sSe45Bfi+mJqSBlZsv5qx7F3lrwg+jYxx3yRYDjhlOGGK0vjbPq4CYIOSiRUuRcwHVPR3nHLQRE2xLnkIIw79COlAY6pQEsghZIOlqiIWnXflfTf4UhZ+t+OYWa+vwo0kJWiSFH0DtYh1BGKqa4wULVfnchvCO9sIoNY2Sw9it7ms+7jtq+7XYEknNP86tGnQEkTTq3vEcaV0KFYUQ6cs5ghKqQF7H2kfLRxGDRQ6WM4JCe5ycF7EKq8by4sedIUCq+3wYMir/hzpPqDI5LGMOrPO53EpuDHvVt/yY4VtzPemYjN0aAaE1AHWo=");
        System.setProperty("aws.region", "eu-west-1");
    }
//
//    //create bucket
//        public void create_bucket(){
//        set_properties();
//
//        String bucket_name = "com.automation.test";
//        S3Client client=S3Client.builder().build();
//        CreateBucketRequest request=CreateBucketRequest.builder().bucket(bucket_name).build();
//        client.createBucket(request);}


        public void create_folder_inside_bucket(){
        set_properties();
        String folder_name="test_folder";
        String filePath = "C:/Users/armagan.dursun/Desktop/test_automation.txt";
            String bucket_name = "com.automation.test";
            String key=folder_name+"/"+Paths.get(filePath).getFileName().toString();
        S3Client s3Client=S3Client.builder().build();
        PutObjectRequest  putObjectRequest;
        putObjectRequest=PutObjectRequest.builder().bucket(bucket_name).key(key).build();
        PutObjectResponse putObjectResponse=s3Client.putObject(putObjectRequest,Paths.get(filePath));
            System.out.println("file uploded="+putObjectResponse.sdkHttpResponse().isSuccessful());

}}
////
//        public void delete_folder_inside_bucket(){
//        set_properties();
//        final AmazonS3 s3= AmazonS3ClientBuilder.defaultClient();
//            String bucket_name = "com.automation.test";
//            String folder_name="test_folder/";
//
//            S3Client s3Client=S3Client.builder().build();
//            DeleteObjectRequest deleteObjectRequest= DeleteObjectRequest.builder().bucket(bucket_name).key(folder_name).build();
//
//            s3Client.deleteObject(deleteObjectRequest);
////
////
////}

//    public static void main(String[] args) {
//        S3_delete  s3_delete=new S3_delete();
//        s3_delete.delete_folder_inside_bucket();
//    }}