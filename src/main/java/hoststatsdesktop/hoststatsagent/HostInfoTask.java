package hoststatsdesktop.hoststatsagent;

import java.util.TimerTask;

public class HostInfoTask extends TimerTask {
    public String apikey;

    public HostInfoTask(String apikey) {
        //constructor

        this.apikey = apikey;
    }

    public void run() {
        try {
            //hostname

            HostInfo hostInfo = new HostInfo();
//            String       postUrl       = "http://localhost:5051/rest/hostinfo/apikey/bfc6c0979f536fe36745f96f5818d268/hostkey/bfks873hdkhbdkah242/";// put in your url
            String postUrl = Config.API_URL + "/rest/hostinfo/apikey/" + this.apikey + "/hostkey/" + hostInfo.getHostId() + "/"; // put in your url
//            String       postUrl       = "http://localhost:5051/"+Config.API_HOSTINFO_URL+hostInfo.getHostId()+"/";
            // send hostInfo update
            Utils.post(postUrl, hostInfo);

            Thread.sleep(1000);
            //HostData
            HostData hostData = new HostData();
            String postUrlHostData = Config.API_URL +
            "/rest/hostdata/apikey/" + this.apikey + "/hostkey/" + hostInfo.getHostId() + "/"; // put in your url

            Utils.postHostData(postUrlHostData, hostData);


        } catch (Exception ex) {

            System.out.println("error running thread " + ex.getMessage());
        }
    }

}
