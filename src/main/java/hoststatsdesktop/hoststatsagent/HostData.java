package hoststatsdesktop.hoststatsagent;


import hoststatsdesktop.hoststatsagent.javasysmon.CpuTimes;
import hoststatsdesktop.hoststatsagent.javasysmon.JavaSysMon;
import lombok.Data;

@Data
public class HostData {
    private String hostId;
    private String memory;
    private String freeMemory;
    private String disk;
    private String freeDisk;
    private String time;
    private float cpuUsage;

    public HostData() {
        try {
            String hostname = Utils.executeCommand("hostname");

            JavaSysMon javaSysMon = new JavaSysMon();
            CpuTimes initialTimes = javaSysMon.cpuTimes();
            this.hostId = Utils.hash(hostname.replace("\n", "") + "@" + javaSysMon.osName());
            this.memory = Long.toString(javaSysMon.physical().getTotalBytes() / 1000000) + " MB";
            this.freeMemory = Long.toString(javaSysMon.physical().getFreeBytes() / 1000000) + " MB";
            this.disk = Utils.getTotalDiskSpace();
            this.freeDisk = Utils.getFreeDiskSpace();


            this.time = Utils.getDataTimeFromSystem();
            Thread.sleep(1000);
            this.cpuUsage = javaSysMon.cpuTimes().getCpuUsage(initialTimes);

        }catch (Exception ex) {

            System.out.println("error running HostInfo " + ex.getMessage());
        }
    }
}
