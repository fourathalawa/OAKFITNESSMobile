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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.mycompany.myapp.gui.ShowGyms;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.SalleDeSport;
import com.mycompany.myapp.utils.SessionManager;
import java.io.IOException;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author User
 */
public class Salledesportservices {
  public static Salledesportservices instance = null;
        public ArrayList<SalleDeSport> salles;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static Salledesportservices getInstance() {
        if (instance == null) {
            instance = new Salledesportservices();
        }
        return instance;
    }
    public void newgym(TextField Name, TextField adress, TextField price, int id) {
  String url = "http://localhost:8000/salledesport/newgym?idmanager=" + id + "&nomsalle=" + Name.getText().toString() + "&adresse=" + adress.getText().toString() + "&prixseance=" + price.getText().toString();
        req = new ConnectionRequest(url, false);

System.out.println(url);
        if (Name.getText().equals(" ") && adress.getText().equals(" ") && price.getText().equals(" ") ) {
            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);
        }
        req.addResponseListener((e) -> {

            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            if (responseData == "challenge Exist!!!!") {
                System.out.println("azertyuiop");
            }
            System.out.println("data ===>" + responseData);
        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);


    }
     public SalleDeSport getSalle(int id) {
         
        String url = "http://localhost:8000/salledesport/getSalle/" + id;
        req = new ConnectionRequest(url, false);
 SalleDeSport salle = new SalleDeSport();
 
        req.addResponseListener((NetworkEvent e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData());
            System.out.println("hello" + json);

            Map<String, Object> userlistjson;
            try {
                userlistjson = j.parseJSON(new CharArrayReader(json.toCharArray()));

                if (userlistjson != null) {
                    System.out.println("hi" + userlistjson);
          
           
           salle.setAdresse(userlistjson.get("adresse").toString());
             salle.setNom(userlistjson.get("nomsalle").toString());
                  salle.setId(id);  
                  salle.setPrixSeance(Float.parseFloat(userlistjson.get("prixseance").toString()));
                
                } 
            } catch (IOException ex) {
            }
        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        return salle;
    }

    public boolean deleteGYM(TextField t ) {
        System.out.println(t.getText().toString());
        System.out.println("******");
        String url = "http://127.0.0.1:8000/salledesport/deletemobile/" + t.getText().toString();
        req = new ConnectionRequest(url, false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }
    public ArrayList<SalleDeSport> getMygyms(int t){
        String url = "http://localhost:8000/salledesport/allmygyms/"+t;
                req = new ConnectionRequest(url,false); 

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    salles = parseGyms(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 //   Logger.getLogger(Userservices.class.getName()).log(Level.SEVERE, null, ex);
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return salles;
    }
      public ArrayList<SalleDeSport> parseGyms(String jsonText) throws ParseException {
        try {
            salles = new ArrayList<>();
            JSONParser j = new JSONParser();

         
          
            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

 
        
            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
            for (Map<String, Object> obj : list) {
                SalleDeSport e = new SalleDeSport();
                float id = Float.parseFloat(obj.get("idSalle").toString());
                e.setId((int) id);
                e.setNom(obj.get("nomsalle").toString());
                               e.setAdresse(obj.get("adresse").toString());
                e.setPrixSeance(Float.parseFloat(obj.get("prixseance").toString()));
                salles.add(e);
            }

        } catch (IOException ex) {

        }
      
        return salles;
    }
public void editSalle(TextField nom, TextField adresse, TextField price) {
 boolean valide=false; 
        boolean etat=true; 
        String url = "http://localhost:8000/salledesport/updategym/"+SessionManager.getId()+"?nomsalle="+nom.getText().toString()+"&adresse="+adresse.getText().toString()+"&prixseance="+price.getText().toString();
        req = new ConnectionRequest(url,false); 

          System.out.println(url);
        //Control saisi
        if(nom.getText().equals(" ")&& adresse.getText().equals(" ")&& price.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
          
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
            new ShowGyms().show();
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
}
