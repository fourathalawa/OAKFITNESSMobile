/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Challenge;
import com.mycompany.services.Challengeservices;
import com.mycompany.services.Userservices;
import com.mycompany.utils.SessionChallenge;
import com.mycompany.utils.SessionManager;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class showChallenge extends BaseForm {
    BaseForm current;

    public showChallenge() {
       setTitle("Profile User");
         SpanLabel sp = new SpanLabel();
           setLayout(BoxLayout.y());
           Container c3 = new Container(BoxLayout.y());
      
            SpanLabel spl = new SpanLabel("Init weight: " + "  " + SessionChallenge.getPoidinit());
            SpanLabel sp2 = new SpanLabel("Object weight: " + "  " + SessionChallenge.getPoidob());

            SpanLabel sp3 = new SpanLabel("Current weight: " + "  " + SessionChallenge.getPoidnv());

            SpanLabel sp4 = new SpanLabel("Height: " + "  " + SessionChallenge.getTaille());
            SpanLabel sp5 = new SpanLabel("Start Date: " + "  " + SessionChallenge.getDatedeb());
            SpanLabel sp6 = new SpanLabel("End Date: " + "  " + SessionChallenge.getDatefin());

            addAll(spl, sp2, sp3, sp4, sp5, sp6);
        }           Button back = new Button();
           
        }
    

