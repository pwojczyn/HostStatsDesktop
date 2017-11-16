package hoststatsdesktop.controllers;

import hoststatsdesktop.hoststatsagent.HostInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    Label hostTitleInfo,hostOsNameInfo,hostMemoryInfo,hostDiskInfo,hostCpuInfo;

    HostInfo hostInfo = new HostInfo();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        hostTitleInfo.setText("Host Title: "+hostInfo.getHostTitle());
        hostOsNameInfo.setText("OS name: "+hostInfo.getHostOs());
        hostMemoryInfo.setText("Memory: "+hostInfo.getHostMemory());
        hostDiskInfo.setText("Disk: "+hostInfo.getHostDisk());
        hostCpuInfo.setText("CPU: "+hostInfo.getHostCpu());


    }
}
