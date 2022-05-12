/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;



/**
 *
 * @author Heni Nechi
 */
public class Home extends Form {

    Form current;
    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public Home() {
    current = this; //Récupération de l'interface(Form) en cours
    int session = 0;
    
        setTitle("Home");
        setLayout(BoxLayout.y());
        add(new Label("Events"));
        Button btnListEvents = new Button("List Events");
        if(session==0){
        btnListEvents.addActionListener(e -> new ListEventsForm(current).show());
        }else{
        btnListEvents.addActionListener(e -> {
            new ListEvents(current).show();
        });
        }
        add(btnListEvents);
        
        
        add(new Label("Meals"));
        
        Button btnListReaps = new Button("List Meals");
        if(session==0){
        btnListReaps.addActionListener(e -> new ListRepasForm(current).show());
        }else{
        btnListReaps.addActionListener(e -> new ListRepas(current).show());
        }
        add(btnListReaps);
        
         add(new Label("Exercices"));
         
        Button btnListExercice = new Button("List Exercice");
        if(session==0){
        btnListExercice.addActionListener(e -> new ListExerciceForm(current).show());
        }
        else{
        btnListExercice.addActionListener(e -> new ListExercice(current).show());
        }
        add(btnListExercice);
        
        
    }
}
