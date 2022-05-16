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
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.gui.Admin;
import com.mycompany.myapp.gui.Login;
import com.mycompany.myapp.gui.HomeForm;
import com.mycompany.myapp.gui.showuser;
import com.mycompany.myapp.utils.SessionManager;
import com.mycompany.myapp.utils.statics;
import java.io.IOException;
import static java.lang.Math.round;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author User
 */
public class Userservices {
         public static Userservices instance=null;
 private ConnectionRequest req;
        public ArrayList<User> users;

    public static Userservices getInstance() {
        if (instance == null) {
            instance = new Userservices();
        }
        return instance;
    }
    public boolean resultOK;
    
      public void addUser(TextField name,TextField lastname,TextField email,TextField phone,Picker date,TextField password,TextField image) {
   
           boolean valide=false; 
        boolean etat=true; 
        String url = "http://localhost:8000/user/inscription?nomuser="+name.getText().toString()+"&prenomuser="+lastname.getText().toString()+"&mailuser="+email.getText().toString()+"&password="+password.getText().toString()+"&roleuser="+0+"&telephonenumberuser="+phone.getText().toString()+"&datenaissanceuser="+date.getText().toString()+"&imageuser="+image.getText().toString();
        req = new ConnectionRequest(url,false); 

          System.out.println(url);
        //Control saisi
        if(name.getText().equals(" ")&& lastname.getText().equals(" ")&& password.getText().equals(" ") && email.getText().equals(" ")&&phone.getText().equals(" ")&&image.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
             new HomeForm().show();
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
      }

    public void signin(TextField email, TextField password) {

         String url = "http://localhost:8000/user/loginuser?mailuser="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url,false); 
         
        
        req.addResponseListener((NetworkEvent e) -> {
            JSONParser j=new JSONParser();
            String json = new String(req.getResponseData());
            System.out.println("hello"+json);
            
            
            
                
                Map<String,Object> userlistjson;
             try {
                 userlistjson = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
                            System.out.println("hi"+userlistjson);

                int role;
               
                
              

                  //Session 
                  float id = Float.parseFloat(userlistjson.get("iduser").toString());
              SessionManager.setId(round(id));
              float role2 = Float.parseFloat(userlistjson.get("roleuser").toString());
              role =round(role2);
               SessionManager.setPassword(userlistjson.get("password").toString());
               SessionManager.setName(userlistjson.get("nomuser").toString());
                SessionManager.setLastname(userlistjson.get("prenomuser").toString());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       /* String date = sdf.format(userlistjson.get("datenaissanceuser"));
 SessionManager.setDate(date);*/
                SessionManager.setEmail(userlistjson.get("mailuser").toString());
                SessionManager.setTelephone(Long.parseLong(userlistjson.get("telephonenumberuser").toString()));
 SessionManager.setRole(role);
                //photo *
             
                    System.out.println(SessionManager.getEmail());
                Dialog.show("authentification avec succees ","session plein","OK",null);
                if(userlistjson.get("imageuser") != null)
                    SessionManager.setProfilepicture(userlistjson.get("imageuser").toString());
                    System.out.println(SessionManager.getEmail());
                   if(role!=3)
                {
new HomeForm().show();
                        }
                   else{new HomeForm().show();  } 
             
                     } catch (IOException ex) {
             }
             
                
                
                
           
         });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    
     public boolean deleteUser(int t) {
        System.out.println(t);
        System.out.println("******");
     
       String url = "http://127.0.0.1:8000/user/deletemobile/"+t;
       //String url = Statics.BASE_URL + "addTournoij";
        req = new ConnectionRequest(url,false); 
       // req.setPost(false);
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

   
    
    
    public ArrayList<User> getAllUsers(){
        String url = "http://localhost:8000/user/allusers";
                req = new ConnectionRequest(url,false); 

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    users = parseUsers(new String(req.getResponseData()));
                } catch (ParseException ex) {
                 //   Logger.getLogger(Userservices.class.getName()).log(Level.SEVERE, null, ex);
                }
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return users;
    }
      public ArrayList<User> parseUsers(String jsonText) throws ParseException {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();

         
          
            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

 
        
            List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");
            for (Map<String, Object> obj : list) {
                User e = new User();
                float id = Float.parseFloat(obj.get("iduser").toString());
                e.setId((int) id);
                e.setNom(obj.get("nomuser").toString());
                                e.setPrenom(obj.get("prenomuser").toString());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                try {
                    System.out.println(sdf.parse(obj.get("datenaissanceuser").toString()));
                    e.setDate_Naissance(String.valueOf(obj.get("datenaissanceuser")));
                } catch (ParseException ex) {

                }
                e.setMail(obj.get("mailuser").toString());
                e.setTelephone_Number(Long.parseLong(obj.get("telephonenumberuser").toString()));
                users.add(e);
            }

        } catch (IOException ex) {

        }
      
        return users;
    }
   private ArrayList<User> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(req.getResponseData()).toCharArray()
                    
            ));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                User u = new User();
                        int id= (int) Float.parseFloat(obj.get("iduser").toString());
                        String nom= (String) obj.get("nomuser");
                         String prenom=obj.get("prenomuser").toString();
                         String email= obj.get("mailuser").toString();
                         String password=obj.get("password").toString();
                         Long numtel=Long.parseLong(obj.get("telephonenumberuser").toString());
                         String date=obj.get("datenaissanceuser").toString();
                         u.setNom(nom);
                         u.setPrenom(prenom);
                         u.setMail(email);
                         u.setDate_Naissance(date);
                         u.setTelephone_Number(numtel);

                users.add(u);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return users;
    }

    public void editUser(TextField name, TextField lastname, TextField email, TextField phone, TextField pass,TextField image) {
 boolean valide=false; 
        boolean etat=true; 
        String url = "http://localhost:8000/user/editUser?iduser="+SessionManager.getId()+"&nomuser="+name.getText().toString()+"&prenomuser="+lastname.getText().toString()+"&mailuser="+email.getText().toString()+"&roleuser="+0+"&telephonenumberuser="+phone.getText().toString()+"&password="+pass.getText().toString()+"&imageuser="+image.getText().toString();
        req = new ConnectionRequest(url,false); 

          System.out.println(url);
        //Control saisi
        if(name.getText().equals(" ")&& lastname.getText().equals(" ")&& email.getText().equals(" ")&&phone.getText().equals(" ")&&image.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
           SessionManager.setName(name.getText().toString());
                SessionManager.setLastname(lastname.getText().toString());
               
                SessionManager.setEmail(email.getText().toString());
                SessionManager.setTelephone(Long.parseLong(phone.getText().toString()));
                            SessionManager.setPassword(pass.getText().toString());
      SessionManager.setProfilepicture(image.getText().toString());
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
            new showuser().show();
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}
