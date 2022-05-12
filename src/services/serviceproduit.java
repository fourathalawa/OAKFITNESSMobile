/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package services;
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
import entities.Produit;

import utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kriaa
 */
public class serviceproduit {
 public ArrayList<Produit> Produit;

    public static serviceproduit instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public serviceproduit() {
        req = new ConnectionRequest();
    }

    public static serviceproduit getInstance() {
        if (instance == null) {
            instance = new serviceproduit();
        }
        return instance;
    }

    public ArrayList<Produit> parseCat(String jsonText) {
        try {
            Produit = new ArrayList<>();
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            Map<String, Object> ProduitListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ProduitListJson.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données

                Produit e = new Produit();
               
                 float id = Float.parseFloat(obj.get("idproduit").toString());
                 e.setIdProduit((int)id);
                 e.setNomProduit(obj.get("nomproduit").toString());
                 e.setCategProduit(obj.get("categproduit").toString());
                 e.setDescrProduit(obj.get("descrproduit").toString());
                 float prix = Float.parseFloat(obj.get("prixproduit").toString());
                 e.setPrixProduit(prix);
                 float av = Float.parseFloat(obj.get("isavailable").toString());
                 e.setIsAvailable((int)av);
                 e.setImageProduit(obj.get("imageproduit").toString());
                 float pd = Float.parseFloat(obj.get("stockproduit").toString());
                 e.setStockProduit((int)pd);
                 Produit.add(e);

}
        } catch (IOException ex) {

        }
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        return Produit;
    }

    public ArrayList<Produit> getAllProducts() {
        String url = Statics.BASE_URL + "/produit/codename/viewp";

        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produit = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produit;
    }

    public boolean Delete(int id) {
        String url = Statics.BASE_URL + "/produit/codename/deletep/" + id;

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
    public void addProduit(Produit c) {
        
        String url = Statics.BASE_URL + "/produit/codename/addp?nomproduit=" + c.getNomProduit() + "&categproduit=" + c.getCategProduit() + "&descrproduit="
                + c.getDescrProduit() + "&prixproduit=" + c.getPrixProduit()+ "&isavailable=" + c.getIsAvailable()+ "&imageproduit=" + c.getImageProduit()+ "&stockproduit=" + c.getStockProduit();
         
    
         req.setUrl(url);
        req.addResponseListener((e) -> {

            String str = new String(req.getResponseData());
            System.out.println("data == " + str);

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public boolean modifierProduit(Produit event) {
        String url = Statics.BASE_URL + "/produit/codename/updatep/" +event.getIdProduit()+"?nomproduit=" + event.getNomProduit() + "&categproduit=" + event.getCategProduit() + "&descrproduit="
                + event.getDescrProduit() + "&prixproduit=" + event.getPrixProduit()+ "&isavailable=" + event.getIsAvailable()+ "&imageproduit=" + event.getImageProduit()+ "&stockproduit=" + event.getStockProduit();

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
