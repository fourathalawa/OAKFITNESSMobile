/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.services.ExerciceService;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Heni Nechi
 */
public class ListExerciceForm extends Form{
    Form current;
    private EncodedImage enc;
    Image image;
    ImageViewer imgv;
    private String url = "http://127.0.0.1:8000/public/uploads/images";
     public ListExerciceForm(Form previous) {
          super(BoxLayout.y());
          current = this;
        setTitle("List Exercices");
        Button btnAdd = new Button("Add Exercice");
        add(btnAdd);
        btnAdd.addActionListener(e -> new AjoutExercice(current).show());
        ArrayList<Exercice> list = ExerciceService.getInstance().getAllMeals();
        for(Exercice e:list)
        {
            Label Type = new Label("Type :" +e.getTypeExercice());
            add(Type);
            Label Name = new Label("Name :" +e.getNomExercice());
            add(Name);
            Label Muscle = new Label("Muscle :" +e.getMuscle());
            add(Muscle);
            Label Video = new Label("Video :" +e.getVideo());
            add(Video);
            Label Description = new Label("Description :" +e.getDescrExercice());
            add(Description);
            Label Difficulty  = new Label("Difficulty :" +e.getDiffExercice());
            add(Difficulty);
            Label JustGym  = new Label("JustGym :" +e.getJusteSalleExercice());
            add(JustGym);
            Label Length  = new Label("Length :" +e.getDureeExercice());
            add(Length);
            Button btnDelete = new Button("Delete Event"+ e.getNomExercice());
            Button btnEdit = new Button("Edit "+ e.getNomExercice());
        btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ExerciceService.getInstance().Deletexercice(e);
                }
        });
        //video player
            
            Display display = Display.getInstance();

            BrowserComponent browser = new BrowserComponent();
            //WebBrowser browser = new WebBrowser();
            String videoUrl = e.getVideo();
            int videoWidth = (int) ((double) display.getDisplayWidth());
            int videoHeight = (int) ((double) videoWidth * 0.5625);
            String integrationCode = "<iframe src=\"" + videoUrl + "\" frameborder=\"0\"  width=\"" + 210 + "\" height=\"" + 110 + "\" encrypted-media\" allowfullscreen frameborder=\"0\"></iframe>";
            browser.setPage(integrationCode, null);
            Container browserContainer = new Container(new BorderLayout());
            browserContainer.setPreferredH(videoHeight);
            browserContainer.setPreferredW(videoWidth);
            browserContainer.add(CENTER, browser);
            
            add(browserContainer);
        add(btnDelete);
        btnEdit.addActionListener(ex -> new editExercice(e,current).show());
        add(btnEdit);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
    
}
