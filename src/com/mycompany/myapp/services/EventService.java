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
import com.mycompany.myapp.entities.events;
import com.mycompany.myapp.utils.statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Heni Nechi
 */
public class EventService {

    public ArrayList<events> events;

    public static EventService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EventService() {
        req = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public ArrayList<events> parseEvents(String jsonText) {
        try {
            events = new ArrayList<>();
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
            Map<String, Object> eventsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche.               
            
            Le format Json impose que l'objet soit définit sous forme
            de clé valeur avec la valeur elle même peut être un objet Json.
            Pour cela on utilise la structure Map comme elle est la structure la
            plus adéquate en Java pour stocker des couples Key/Value.
            
            Pour le cas d'un tableau (Json Array) contenant plusieurs objets
            sa valeur est une liste d'objets Json, donc une liste de Map
             */
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventsListJson.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                events e = new events();
                float id = Float.parseFloat(obj.get("idevenement").toString());
                e.setIDEvenement((int) id);
                e.setIDCreatorEvenement(obj.get("idcreatorevenement").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    System.out.println(sdf.parse(obj.get("dateevenement").toString()));
                    e.setDateEvenement(sdf.parse(obj.get("dateevenement").toString()));
                } catch (ParseException ex) {

                }
                e.setTitreEvenement(obj.get("titreevenement").toString());
                e.setDescrEvenement(obj.get("descrevenement").toString());
                e.setAdresseEvenement(obj.get("adresseevenement").toString());
                e.setTypeEvenement(obj.get("typeevenement").toString());
                e.setImage(obj.get("image").toString());
                //Ajouter la tâche extraite de la réponse Json à la liste
                events.add(e);
            }

        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return events;
    }

    public ArrayList<events> getAllEvents() {
        String url = statics.BASE_URL + "event/allMobileJson";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                events = parseEvents(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return events;
    }

    // delete
    public boolean Deleteevent(events e) {
        String url = statics.BASE_URL + "event/deleteMobile/" + e.getIDEvenement();
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

    //add 
    public void addEvent(events e) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datetoinsert = sdf.format(e.getDateEvenement());

        String url = statics.BASE_URL + "event/newMobile?IDCreatorEvenement=" + e.getIDCreatorEvenement() + "&TitreEvenement=" + e.getTitreEvenement() + "&DescrEvenement=" + e.getDescrEvenement() + "&AdresseEvenement=" + e.getAdresseEvenement() + "&TypeEvenement=" + e.getTypeEvenement() + "&DateEvenement=" + datetoinsert + "&image=" + e.getImage();

        System.out.println(url);
        req.setUrl(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    //edit

    public void editEvent(events e) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datetoinsert = sdf.format(e.getDateEvenement());
        String url = statics.BASE_URL + "event/editMobile/" + e.getIDEvenement() + "?IDCreatorEvenement=" + e.getIDCreatorEvenement() + "&TitreEvenement=" + e.getTitreEvenement() + "&DescrEvenement=" + e.getDescrEvenement() + "&AdresseEvenement=" + e.getAdresseEvenement() + "&TypeEvenement=" + e.getTypeEvenement() + "&DateEvenement=" + datetoinsert + "&image=" + e.getImage();
        req.setUrl(url);
        req.addResponseListener((ex) -> {
            String str = new String(req.getResponseData());
            System.out.println("data ==" + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
