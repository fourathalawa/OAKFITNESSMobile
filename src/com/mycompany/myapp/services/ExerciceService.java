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
import com.mycompany.myapp.entities.Exercice;
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
public class ExerciceService {

    public ArrayList<Exercice> exercices;

    public static ExerciceService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private ExerciceService() {
        req = new ConnectionRequest();
    }

    public static ExerciceService getInstance() {
        if (instance == null) {
            instance = new ExerciceService();
        }
        return instance;
    }

    public ArrayList<Exercice> parseExercice(String jsonText) {
        
        try {
            exercices = new ArrayList<>();
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
            Map<String, Object> reapsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            /* Ici on récupère l'objet contenant notre liste dans une liste
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) reapsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Exercice ex = new Exercice();
                float id = Float.parseFloat(obj.get("idexercice").toString());
                ex.setIDExercice((int) id);
                ex.setTypeExercice(obj.get("typeexercice").toString());
                ex.setNomExercice(obj.get("nomexercice").toString());
                ex.setMuscle(obj.get("muscle").toString());
                ex.setVideo(obj.get("video").toString());
                ex.setDescrExercice(obj.get("descrexercice").toString());
                ex.setDiffExercice(obj.get("diffexercice").toString());
                ex.setJusteSalleExercice(obj.get("justesalleexercice").toString());
                ex.setDureeExercice(obj.get("dureeexercice").toString());
                ex.setImage(obj.get("image").toString());
                System.out.println(ex);
                //Ajouter la tâche extraite de la réponse Json à la liste
                exercices.add(ex);
            }
            
            
            /*
            A ce niveau on a pu récupérer une liste des tâches à partir
            de la base de données à travers un service web
            
            */
            
        } catch (IOException ex) {
            System.out.println(ex);
        }
        return exercices;
    }

    public ArrayList<Exercice> getAllMeals() {
        String url = statics.BASE_URL + "exercice/allMobileJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                exercices = parseExercice(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return exercices;
    }

    public boolean Deletexercice(Exercice e) {
        String url = statics.BASE_URL + "exercice/deleteMobile/" + e.getIDExercice();
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

    public void addExercice(Exercice e) {
        String url = statics.BASE_URL + "exercice/newMobile?TypeExercice=" + e.getTypeExercice()+ "&NomExercice=" + e.getNomExercice()+ "&Muscle=" + e.getMuscle()+ "&video=" + e.getVideo()+ "&DescrExercice=" + e.getDescrExercice()+ "&DiffExercice=" + e.getDiffExercice()+"&JusteSalleExercice="+e.getJusteSalleExercice()+"&DureeExercice="+e.getDureeExercice()+"&image="+e.getImage();
        req.setUrl(url);
        req.addResponseListener((a)-> {
        String str = new String(req.getResponseData());
        System.out.println("data =="+str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //edit 
    public void editExercice(Exercice e) throws ParseException
    {   
        
       
       String url=statics.BASE_URL+"exercice/editMobile/"+e.getIDExercice()+"?TypeExercice=" + e.getTypeExercice()+ "&NomExercice=" + e.getNomExercice()+ "&Muscle=" + e.getMuscle()+ "&video=" + e.getVideo()+ "&DescrExercice=" + e.getDescrExercice()+ "&DiffExercice=" + e.getDiffExercice()+"&JusteSalleExercice="+e.getJusteSalleExercice()+"&DureeExercice="+e.getDureeExercice()+"&image="+e.getImage();
        req.setUrl(url);
        req.addResponseListener((ex)-> {
        String str = new String(req.getResponseData());
        System.out.println("data =="+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req); 
    }
}
