package com.study.dev.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.study.dev.model.ServerInfo;
import com.study.dev.model.ServiceInfo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataService {
    
    @Value( "${datapath}" )
    private String datapath;

    private Map<String, String> getServerData(){
        File path = new File(datapath);

        Map<String, String> result = new HashMap<>();

        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                try {
                    FileInputStream fis = new FileInputStream(files[i]);
                    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                    
                    result.put(
                        files[i].getName(), 
                        br.lines().collect(Collectors.joining("\n"))
                    );

                    br.close();

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException io){
                    Logger.getLogger(DataService.class.getName()).log(Level.SEVERE, null, io);
                }
            }
        }

        return result;
    }

    public List<ServerInfo> getServerInfo(){
        List<ServerInfo> result = new ArrayList<>();
        Map<String, String> serverData = getServerData();
        for(Map.Entry<String, String> info : serverData.entrySet()){
            result.add(new ServerInfo(info.getKey(), map(info.getValue())));
        }

        return result;
    }

    private static List<ServiceInfo> map(String data){
        List<ServiceInfo> result = new ArrayList<>();
        if(!data.isEmpty() && !data.startsWith("error")){
            String[] split = data.split(",");
            for(String service : split){
                String[] status = service.split(":");
                Boolean b = false;
                if(Integer.valueOf(status[1]) > 0){
                    b = true;
                }
                result.add(new ServiceInfo(status[0], b));
            }
        }
        return result;
    }
}
