/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.events;
import com.mycompany.myapp.services.EventService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Heni Nechi
 */
public class ListEventsForm extends Form{
Form current;
private EncodedImage enc;
    Image image;
    ImageViewer imgv;
    private String url = "http://127.0.0.1:8000/public/uploads/images";
    public ListEventsForm(Form previous) {
        super(BoxLayout.y());
        current = this;
        setTitle("List Events");
        Button btnAdd = new Button("Add event");
        add(btnAdd);
        btnAdd.addActionListener(e -> new AjoutEvent(current).show());
        ArrayList<events> list = EventService.getInstance().getAllEvents();
        for(events e:list)
        {
            Label creator = new Label("Creator :" +e.getIDCreatorEvenement());
            add(creator);
            Label Date = new Label("Date :" +e.getDateEvenement());
            add(Date);
            Label Titre = new Label("Titre :" +e.getTitreEvenement());
            add(Titre);
            Label Description = new Label("Description :" +e.getDescrEvenement());
            add(Description);
            Label Address = new Label("Address :" +e.getAdresseEvenement());
            add(Address);
            Label Type  = new Label("Type :" +e.getTypeEvenement());
            add(Type);
            Button btnDelete = new Button("Delete : "+ e.getTitreEvenement());
            Button btnEdit = new Button("Edit : "+ e.getTitreEvenement());
        btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    EventService.getInstance().Deleteevent(e);
                }
        });
        btnEdit.addActionListener(ex -> new editEvent(e,current).show());
        //image
            try {              
                enc=EncodedImage.create("/hhh.png");
            } catch (IOException ex) {
            }
            image=URLImage.createToStorage(enc,e.getImage(),url+e.getImage(),URLImage.RESIZE_SCALE).scaled(700, 300);
            imgv=new ImageViewer(image);
            add(imgv);
            //endimage
        add(btnDelete);
        add(btnEdit);
        }
        
        
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
    
}
