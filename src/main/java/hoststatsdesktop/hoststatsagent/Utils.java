package hoststatsdesktop.hoststatsagent;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Timer;
import java.util.TimerTask;

public class Utils {
    public static void post(String postUrl, HostInfo hostInfo){

        try {
            Gson gson          = new Gson();
            HttpClient httpClient    = HttpClientBuilder.create().build();
            HttpPost post          = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(hostInfo));//gson.tojson() converts your pojo to json
            //System.out.println(gson.toJson(hostInfo));
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            postingString = new StringEntity(gson.toJson(hostInfo));
            HttpResponse response = httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    public static void postHostData(String postUrl, HostData hostData){

        try {
            Gson gson          = new Gson();
            HttpClient httpClient    = HttpClientBuilder.create().build();
            HttpPost post          = new HttpPost(postUrl);
            StringEntity postingString = new StringEntity(gson.toJson(hostData));//gson.tojson() converts your pojo to json
            //System.out.println(gson.toJson(hostData));
            post.setEntity(postingString);
            post.setHeader("Content-type", "application/json");
            postingString = new StringEntity(gson.toJson(hostData));
            HttpResponse response = httpClient.execute(post);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
    }

    public static void runTask(TimerTask task, int delayMinutes){

        Timer time = new Timer();

        int delayTime = 1000 * 60 * delayMinutes; // 1000 milliseconds in a second * 60 per minute * the MINUTES variable.
        time.schedule(task, 2, delayTime);

    }
    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }
    public static String hash(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("MD5");
            String salt = "HostStats.xyz";
            String passWithSalt = password + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passHash.length ;i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
    public static String getDataTimeFromSystem() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp formatDateTime = new Timestamp(System.currentTimeMillis());

        return sdf.format(formatDateTime);
    }
    public static String getHostAddress(){
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String ipAddress = addr.getHostAddress();
        return ipAddress;
    }
    public static String getHostMemory(){
        int mb = 1024 * 1024;

        // get Runtime instance
        Runtime instance = Runtime.getRuntime();
/*
        System.out.println("***** Heap utilization statistics [MB] *****\n");

        // available memory
        System.out.println("Total Memory: " + instance.totalMemory() / mb);

        // free memory
        System.out.println("Free Memory: " + instance.freeMemory() / mb);

        // used memory
        System.out.println("Used Memory: "
                + (instance.totalMemory() - instance.freeMemory()) / mb);

        // Maximum available memory
        System.out.println("Max Memory: " + instance.maxMemory() / mb);
        */
return (instance.maxMemory()/mb) +" MB ";

    }
    public static String getTotalDiskSpace(){
        File file = new File("/");
        long totalSpace = file.getTotalSpace();
        /*
        long totalSpace = file.getTotalSpace(); //total disk space in bytes.
        long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
        long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.

        System.out.println(" === Partition Detail ===");

        System.out.println(" === bytes ===");
        System.out.println("Total size : " + totalSpace + " bytes");
        System.out.println("Space free : " + usableSpace + " bytes");
        System.out.println("Space free : " + freeSpace + " bytes");

        System.out.println(" === mega bytes ===");
        System.out.println("Total size : " + totalSpace /1024 /1024 + " mb");
        System.out.println("Space free : " + usableSpace /1024 /1024 + " mb");
        System.out.println("Space free : " + freeSpace /1024 /1024 + " mb");
        */
        return totalSpace /1024 /1024 + " MB";

    }
    public static String getFreeDiskSpace(){
        File file = new File("/");
        long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.
        /*
        long totalSpace = file.getTotalSpace(); //total disk space in bytes.
        long usableSpace = file.getUsableSpace(); ///unallocated / free disk space in bytes.
        long freeSpace = file.getFreeSpace(); //unallocated / free disk space in bytes.

        System.out.println(" === Partition Detail ===");

        System.out.println(" === bytes ===");
        System.out.println("Total size : " + totalSpace + " bytes");
        System.out.println("Space free : " + usableSpace + " bytes");
        System.out.println("Space free : " + freeSpace + " bytes");

        System.out.println(" === mega bytes ===");
        System.out.println("Total size : " + totalSpace /1024 /1024 + " mb");
        System.out.println("Space free : " + usableSpace /1024 /1024 + " mb");
        System.out.println("Space free : " + freeSpace /1024 /1024 + " mb");
        */
        return freeSpace /1024 /1024 + " MB";

    }


}
