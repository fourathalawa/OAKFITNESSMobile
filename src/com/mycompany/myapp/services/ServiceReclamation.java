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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import com.codename1.ui.Button;
import com.codename1.ui.Container;

import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.entities.Reclamation;

import java.util.ArrayList;
import com.mycompany.myapp.utils.Statics;



/**
 *
 * @author bhk
 */
public class ServiceReclamation {

    public ArrayList<Reclamation> tasks;
    
    public static ServiceReclamation instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ServiceReclamation() {
         req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
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

    public boolean addReclam(Reclamation t) {
        System.out.println(t);
        System.out.println("********");
       String url = Statics.BASE_URL + "liste/addreclam/"+t.getCommentairerec()+"/" + t.getDescrreclam()+"/"+t.getIdcommentreclam();
    
       req.setUrl(url);
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

    public ArrayList<Reclamation> parseTasks(String jsonText){
        try {
            tasks=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = 
               j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println("test"+tasksListJson);
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Container c3 = new Container(BoxLayout.y());

                Reclamation t = new Reclamation();
                float id = Float.parseFloat(obj.get("idreclamation").toString());
                t.setIdreclamation((int)id);
  float idc = Float.parseFloat(obj.get("idcommentreclam").toString());
                t.setIdcommentreclam((int)idc);
               
                    t.setCommentairerec(obj.get("commentairerec").toString());
                    t.setDescrreclam(obj.get("descrreclam").toString());
                               
                tasks.add(t);
            }
            
            
        } catch (IOException ex) {
            
        }
        return tasks;
    }
    
    public ArrayList<Reclamation> getAllReclamations(int id){
        //String url = Statics.BASE_URL+"/tasks/";
        String url = Statics.BASE_URL+"liste/reclam";
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
        String url = Statics.BASE_URL + "liste/deletecomment/" + id;

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
 

}
