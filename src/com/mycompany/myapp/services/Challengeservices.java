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
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Challenge;
import static com.mycompany.myapp.services.Userservices.instance;
import com.mycompany.myapp.utils.SessionChallenge;
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
public class Challengeservices {

    public boolean resultOK;
    public ArrayList<Challenge> ch;

    //singleton 
    public static Challengeservices instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;

    public static Challengeservices getInstance() {
        if (instance == null) {
            instance = new Challengeservices();
        }
        return instance;
    }

    public void newchallenge(TextField poidinit, TextField poidobj, TextField taille, Picker date1, Picker date2, int id) {

        boolean valide = false;
        boolean etat = true;
        String url = "http://localhost:8000/challenge/newchallenge?iduser=" + id + "&poidinit=" + poidinit.getText().toString() + "&poidob=" + poidobj.getText().toString() + "&taille=" + taille.getText().toString() + "&datedebut=" + date1.getText().toString() + "&datefin=" + date2.getText().toString();
        req = new ConnectionRequest(url, false);

        //Control saisi
        if (poidinit.getText().equals(" ") && poidobj.getText().equals(" ") && taille.getText().equals(" ") && date1.getText().equals(" ") && date2.getText().equals(" ")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            if (responseData == "challenge Exist!!!!") {
                System.out.println("azertyuiop");
            }
            System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void getChallenge(int id) {
        String url = "http://localhost:8000/challenge/getCH/" + id;
        req = new ConnectionRequest(url, false);

        req.addResponseListener((NetworkEvent e) -> {
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData());
            System.out.println("hello" + json);

            Map<String, Object> userlistjson;
            try {
                userlistjson = j.parseJSON(new CharArrayReader(json.toCharArray()));

                if (userlistjson != null) {
                    System.out.println("hi" + userlistjson);

                    //Session 
                    float idch = Float.parseFloat(userlistjson.get("idchallenge").toString());
                    SessionChallenge.setId(round(idch));
                    SessionChallenge.setPoidinit(Float.parseFloat(userlistjson.get("poidint").toString()));
                    if (userlistjson.get("poidnv") != null) {
                        SessionChallenge.setPoidnv(Float.parseFloat(userlistjson.get("poidnv").toString()));
                        SessionChallenge.setImcCur(Float.parseFloat(userlistjson.get("poidnv").toString()) / (Float.parseFloat(userlistjson.get("taille").toString()) * Float.parseFloat(userlistjson.get("taille").toString())));
                        SessionChallenge.setImcob(Float.parseFloat(userlistjson.get("poidob").toString()) / (Float.parseFloat(userlistjson.get("taille").toString()) * Float.parseFloat(userlistjson.get("taille").toString())));
                    } else {
                        SessionChallenge.setPoidnv(0);

                        SessionChallenge.setImcCur(Float.parseFloat(userlistjson.get("poidint").toString()) / (Float.parseFloat(userlistjson.get("taille").toString()) * Float.parseFloat(userlistjson.get("taille").toString())));
                        SessionChallenge.setImcob(Float.parseFloat(userlistjson.get("poidob").toString()) / (Float.parseFloat(userlistjson.get("taille").toString()) * Float.parseFloat(userlistjson.get("taille").toString())));
                    }
                    SessionChallenge.setPoidob(Float.parseFloat(userlistjson.get("poidob").toString()));
                    SessionChallenge.setTaille(Float.parseFloat(userlistjson.get("taille").toString()));
                    float idus = Float.parseFloat(userlistjson.get("iduser").toString());
                    SessionChallenge.setId_user(round(idus));
                    SessionChallenge.setDatedeb(userlistjson.get("datedebut").toString());
                    SessionChallenge.setDatefin(userlistjson.get("datefin").toString());

                    /* String date = sdf.format(userlistjson.get("datenaissanceuser"));
 SessionManager.setDate(date);*/
                } else {
                    SessionChallenge.setId(0);
                    SessionChallenge.setPoidinit(0);

                    SessionChallenge.setPoidnv(0);

                    SessionChallenge.setPoidob(0);
                    SessionChallenge.setTaille(0);

                    SessionChallenge.setId_user(0);
                    SessionChallenge.setDatedeb(null);
                    SessionChallenge.setDatefin(null);
                }
            } catch (IOException ex) {
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean deleteChallenge(int t) {
        System.out.println(t);
        System.out.println("******");

        String url = "http://127.0.0.1:8000/challenge/deletemobile/" + t;
        req = new ConnectionRequest(url, false);
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

    public void updatepoid(TextField poidnv) {
        String url = "http://localhost:8000/challenge/uploadCH?iduser=" + SessionManager.getId() + "&poidnv=" + poidnv.getText().toString();
        req = new ConnectionRequest(url, false);

        //Control saisi
        if (poidnv.getText().equals(" ")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            byte[] data = (byte[]) e.getMetaData();
            String responseData = new String(data);

            System.out.println("data ===>" + responseData);
            SessionChallenge.setPoidnv(Float.parseFloat(poidnv.getText().toString()));
            SessionChallenge.setImcCur(SessionChallenge.getPoidnv() / (SessionChallenge.getTaille() * SessionChallenge.getTaille()));
        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
