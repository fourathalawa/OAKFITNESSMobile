/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.myapp.services;
import com.codename1.components.ImageViewer;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Transformation;

import com.mycompany.myapp.utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kriaa
 */
public class servicetransformationfront {

 public ArrayList<Transformation> Transformation;

    public static servicetransformationfront instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public servicetransformationfront() {
        req = new ConnectionRequest();
    }

    public static servicetransformationfront getInstance() {
        if (instance == null) {
            instance = new servicetransformationfront();
        }
        return instance;
    }

    public ArrayList<Transformation> parseCat(String jsonText) {
        try {
            Transformation = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> TransformationtListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) TransformationtListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Transformation e = new Transformation();
               
                 float id = Float.parseFloat(obj.get("idimage").toString());
                 e.setIdImage((int)id);
                 e.setTitreImage(obj.get("titreimage").toString());
                 e.setDescreptionImage(obj.get("descreptionimage").toString());
                 e.setImageAvant(obj.get("imageavant").toString());
                 e.setImageApres(obj.get("imageapres").toString());
                 float PoidAvant = Float.parseFloat(obj.get("poidavant").toString());
                 e.setPoidAvant(PoidAvant);
                 float PoidApres = Float.parseFloat(obj.get("poidapres").toString());
                 e.setPoidApres(PoidApres);
                 float av = Float.parseFloat(obj.get("tailleavant").toString());
                 e.setTailleAvant(av);
                 float ap = Float.parseFloat(obj.get("tailleapres").toString());
                 e.setTailleApres(ap);
                 float pd = Float.parseFloat(obj.get("tlike").toString());
                 e.setTlikes((int)pd);
                 float us = Float.parseFloat(obj.get("iduser").toString());
                 e.setIdUser((int)us);
                 Transformation.add(e);

        } }catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return Transformation;
    }

    public ArrayList<Transformation> getAllTransformations(){
        String url = statics.BASE_URL + "transformation/codename/viewt";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Transformation = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Transformation;
    }

     public boolean Delete(int id){
        String url = statics.BASE_URL + "transformation/codename/deletet/"+id;

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
 public boolean resultok;
    public void addTransformation(Transformation c) {
        //c.getIdUser()=session.getIdUser
        String url = statics.BASE_URL + "transformation/codename/addt?titreimage=" + c.getTitreImage() + "&descreptionimage=" + c.getDescreptionImage() + "&imageavant="
                + c.getImageAvant() + "&imageapres=" + c.getImageApres()+ "&poidavant=" + c.getPoidAvant()+ "&poidapres=" + c.getPoidApres()+ "&tailleavant=" + c.getTailleAvant()+ "&tailleapres=" + c.getTailleApres()+ "&tlike=" + c.getTlikes()+ "&iduser=" + c.getIdUser();
         
    
         req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

 //c.getIdUser()=session.getIdUser
    public boolean modifierTransformation(Transformation c) {

        String url = statics.BASE_URL + "transformation/codename/updatet/"+c.getIdImage()+"?titreimage=" + c.getTitreImage() + "&descreptionimage=" + c.getDescreptionImage() + "&imageavant="
                + c.getImageAvant() + "&imageapres=" + c.getImageApres()+ "&poidavant=" + c.getPoidAvant()+ "&poidapres=" + c.getPoidApres()+ "&tailleavant=" + c.getTailleAvant()+ "&tailleapres=" + c.getTailleApres()/*+ "&tlike=" + c.getTlikes()+ "&iduser=" + c.getIdUser()*/;
         
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent c) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;

    }

    public void upvoteTransformation(Transformation c) {
        //c.getIdUser()=session.getIdUser
        String url = statics.BASE_URL + "transformation/"+ c.getIdImage()+"/votejason";
         
    
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

public void downvoteTransformation(Transformation c){
        //c.getIdUser()=session.getIdUser
        String url = statics.BASE_URL + "transformation/"+ c.getIdImage()+"/downvotejason";
         
    
        req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
