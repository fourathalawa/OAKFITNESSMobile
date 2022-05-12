/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import com.codename1.ui.Button;
import com.codename1.ui.Container;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

import java.util.ArrayList;
import com.mycompany.myapp.utils.statics;


/**
 *
 * @author bhk
 */
public class ServiceTask {

    public ArrayList<Task> tasks;
    
    public static ServiceTask instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceTask() {
         req = new ConnectionRequest();
    }

    public static ServiceTask getInstance() {
        if (instance == null) {
            instance = new ServiceTask();
        }
        return instance;
    }
//public void addExercice(Exercice e) {
//        String url = statics.BASE_URL + "exercice/newMobile?TypeExercice=" + e.getTypeExercice()+ "&NomExercice=" + e.getNomExercice()+ "&Muscle=" + e.getMuscle()+ "&video=" + e.getVideo()+ "&DescrExercice=" + e.getDescrExercice()+ "&DiffExercice=" + e.getDiffExercice()+"&JusteSalleExercice="+e.getJusteSalleExercice()+"&DureeExercice="+e.getDureeExercice();
//        req.setUrl(url);
//        req.addResponseListener((a)-> {
//        String str = new String(req.getResponseData());
//        System.out.println("data =="+str);
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(req);
//    }

    public boolean addTask(Task t) {
        System.out.println(t);
        System.out.println("********");
       String url = statics.BASE_URL + "liste/create?publication=" + t.getPublication();
    
       req.setUrl(url);
       
       req.addArgument("publication", t.getPublication());




       req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<Task> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("test"+tasksListJson);
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Container c3 = new Container(BoxLayout.y());

                Task t = new Task();
                float id = Float.parseFloat(obj.get("idpublication").toString());
                t.setIdpublication((int)id);
                float idu = Float.parseFloat(obj.get("iduser").toString());
                t.setIduser((int)id);
                    t.setDatepublication(obj.get("datepublication").toString());
                    t.setPublication(obj.get("publication").toString());
                    t.setUsernamep(obj.get("usernamep").toString());
                                    Button Delete = new Button("Delete");
c3.add(Delete);
                Delete.getAllStyles().setBgColor(0xF36B08);
          //      addAll(c3);


                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Task> getAllTasks(){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = statics.BASE_URL+"liste/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseTasks(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;
    }
    public boolean Delete(int id) {
        String url = statics.BASE_URL + "liste/deleteclub/" + id;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }
    public boolean modifierClub(Task event) {
        String url = statics.BASE_URL + "liste/modifier/" + event.getPublication()+"/"+event.getIdpublication();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }


}
