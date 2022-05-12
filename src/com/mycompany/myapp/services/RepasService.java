/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.text.SimpleDateFormat;
import com.mycompany.myapp.entities.Repas;
import com.mycompany.myapp.utils.statics;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Heni Nechi
 */
public class RepasService {
    public ArrayList<Repas> repas;
    
    public static RepasService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    private RepasService() {
         req = new ConnectionRequest();
    }

    public static RepasService getInstance() {
        if (instance == null) {
            instance = new RepasService();
        }
        return instance;
    }
    public ArrayList<Repas> parseEvents(String jsonText){
        try {
            repas =new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String,Object> reapsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String,Object>> list = (List<Map<String,Object>>)reapsListJson.get("root");
            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Repas r = new Repas();
                float id = Float.parseFloat(obj.get("idrepas").toString());
                float calorie = Float.parseFloat(obj.get("calorie").toString());
                System.out.println(obj);
                r.setIDRepas((int)id);
                r.setPDej(obj.get("pdej").toString());
                r.setDej(obj.get("dej").toString());
                r.setDinn(obj.get("dinn").toString());
                r.setCalorie((int)calorie);
                r.setRestOrActive(obj.get("restoractive").toString());
                r.setImage(obj.get("image").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                repas.add(r);
            }
            
            
        } catch (IOException ex) {
            
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
        return repas;
    }
    public ArrayList<Repas> getAllMeals(){
        String url = statics.BASE_URL+"repas/allMobileJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                repas = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return repas;
    }
    public void addRepas(Repas r)
    {
       String url=statics.BASE_URL+"repas/newMobile?PDej="+r.getPDej()+"&Dej="+r.getDej()+"&Dinn="+r.getDinn()+"&Calorie="+r.getCalorie()+"&RestOrActive="+r.getRestOrActive()+"&image="+r.getImage();
        req.setUrl(url);
        req.addResponseListener((e)-> {
        String str = new String(req.getResponseData());
        System.out.println("data =="+str);
        }
            );
        
        NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    public Repas DetailsRepas(int id, Repas repa)
    {
        String url = statics.BASE_URL+"repas/Mobile/"+id;
        req.setUrl(url);
        String str = new String (req.getResponseData());
        req.addResponseListener((e)-> {
        JSONParser jsonp = new JSONParser();
        try
        {
            Map<String,Object> obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));
            float calorie = Float.parseFloat(obj.get("calorie").toString());
            repa.setPDej(obj.get("pdej").toString());
                repa.setDej(obj.get("dej").toString());
                repa.setDinn(obj.get("dinn").toString());
                repa.setCalorie((int)calorie);
                repa.setRestOrActive(obj.get("restoractive").toString());
                repa.setImage(obj.get("image").toString());
        }
        catch (IOException ex) {
            
        }
        System.out.println("Data ==="+str);
        }
            );
        NetworkManager.getInstance().addToQueueAndWait(req); 
        
        return repa;
    }
    public boolean Deletrepas(Repas r) {
        String url = statics.BASE_URL + "repas/deleteMobile/" + r.getIDRepas();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                req.removeResponseCodeListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    //edit 
    public void editRepas(Repas r) throws ParseException
    {   
        
       
       String url=statics.BASE_URL+"repas/editMobile/"+r.getIDRepas()+"?PDej="+r.getPDej()+"&Dej="+r.getDej()+"&Dinn="+r.getDinn()+"&Calorie="+r.getCalorie()+"&RestOrActive="+r.getRestOrActive()+"&image="+r.getImage();
        req.setUrl(url);
        req.addResponseListener((ex)-> {
        String str = new String(req.getResponseData());
        System.out.println("data =="+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req); 
    }
    
}
