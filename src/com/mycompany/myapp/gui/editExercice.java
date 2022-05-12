/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Exercice;
import com.mycompany.myapp.services.ExerciceService;
import java.text.ParseException;

/**
 *
 * @author Heni Nechi
 */
public class editExercice extends Form {

    Form current;
    TextField tf_nom, tf_muscle, tf_video, tf_description, tf_type,tf_diff,tf_gym,tf_length,image;

    public editExercice(Exercice e,Form previous) {
        super(BoxLayout.y());
        current = this;
        setTitle("Edit Exercice");
        getContentPane().setScrollVisible(false);
        tf_nom=new TextField(e.getNomExercice(),"Name");
        tf_muscle=new TextField(e.getMuscle(),"Muscle"); 
         tf_video=new TextField(e.getVideo(),"video");
         tf_description=new TextField(e.getDescrExercice(),"Description");
        tf_type=new TextField(e.getTypeExercice(),"Type");
        tf_diff=new TextField(e.getDiffExercice(),"Diffculty");
         tf_gym=new TextField(e.getJusteSalleExercice(),"Only gym");
        tf_length=new TextField(e.getDureeExercice(),"length");
        image =new TextField("", "image");
        Button btnadd=new Button("Edit Exercice");
        //choose file
        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Images");

        testImage.addActionListener(et -> {
            if (FileChooser.isAvailable()) {

                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2 -> {
                    if (e2 != null && e2.getSource() != null) {

                        String file = (String) e2.getSource();
                        String pathLogo = file;
                        String exx = file.substring(file.lastIndexOf("."));
                        System.out.println(pathLogo);
                        image.setText(pathLogo);
                        System.out.println("path :" + pathLogo + " extension :" + exx);

                        try {
                            Image img = Image.createImage(file);
                            this.add(new Label(img));
                            this.revalidate();
                        } catch (Exception ex) {

                        }

                    }
                });
            }
        });
        //endchoosefile
        btnadd.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                e.setNomExercice(tf_nom.getText().toString());
                e.setMuscle(tf_muscle.getText().toString());
                e.setVideo(tf_video.getText().toString());
                e.setDescrExercice(tf_description.getText().toString());
                e.setTypeExercice(tf_type.getText().toString());
                e.setDiffExercice(tf_diff.getText().toString());
                e.setJusteSalleExercice(tf_gym.getText().toString());
                e.setDureeExercice(tf_length.getText().toString());    
                e.setImage(image.getText().toString());
                    try {
                        ExerciceService.getInstance().editExercice(e);
                        new ListExerciceForm(previous).show();
                        refreshTheme();
                    } catch (ParseException ex) {
                        
                    }
                }
        });
        addAll(tf_nom, tf_muscle, tf_video, tf_description, tf_type,tf_diff,tf_gym,tf_length,testImage,btnadd);
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, ex-> previous.showBack());
    }
}

