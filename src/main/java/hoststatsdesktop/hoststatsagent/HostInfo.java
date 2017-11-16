package hoststatsdesktop.hoststatsagent;


import hoststatsdesktop.hoststatsagent.javasysmon.JavaSysMon;
import lombok.Data;

/*
id
hostId
hostTitle
hostMemory
hostDisk
hostCpu
hostUpdate
hostIp
hostUptime
 */
@Data
public class HostInfo {
    private String hostId;
    private String hostTitle;
    private String hostOs;
    private String hostMemory;
    private String hostDisk;
    private String hostCpu;
    private String hostUpdate;
    private String hostIp;
    private String hostUptime;
    private int userId;

    public HostInfo() {
        try {
            String hostname = Utils.executeCommand("hostname");

            JavaSysMon javaSysMon = new JavaSysMon();

            this.hostId = Utils.hash(hostname.replace("\n", "") + "@" + javaSysMon.osName());
            this.hostTitle = hostname.replace("\n", "");
            this.hostOs = javaSysMon.osName();
            this.hostMemory = Long.toString(javaSysMon.physical().getTotalBytes() / 1000000) + " MB";
            this.hostDisk = Utils.getTotalDiskSpace();


            this.hostCpu = javaSysMon.numCpus() + "x" + (javaSysMon.cpuFrequencyInHz() / (1000 * 1000) + " MHz");
            this.hostUpdate = Utils.getDataTimeFromSystem();
            this.hostUptime = JavaSysMon.secsInDaysAndHours(javaSysMon.uptimeInSeconds());
            this.hostIp = Utils.getHostAddress();
        }catch (Exception ex) {

            System.out.println("error running HostInfo " + ex.getMessage());
        }
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public String getHostTitle() {
        return hostTitle;
    }

    public void setHostTitle(String hostTitle) {
        this.hostTitle = hostTitle;
    }

    public String getHostMemory() {
        return hostMemory;
    }

    public void setHostMemory(String hostMemory) {
        this.hostMemory = hostMemory;
    }

    public String getHostDisk() {
        return hostDisk;
    }

    public void setHostDisk(String hostDisk) {
        this.hostDisk = hostDisk;
    }

    public String getHostCpu() {
        return hostCpu;
    }

    public void setHostCpu(String hostCpu) {
        this.hostCpu = hostCpu;
    }

    public String getHostUpdate() {
        return hostUpdate;
    }

    public void setHostUpdate(String hostUpdate) {
        this.hostUpdate = hostUpdate;
    }

    public String getHostIp() {
        return hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
