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


/**
 *
 * @author Heni Nechi
 */
public class AjoutExercice extends Form {

    Form current;
    TextField tf_nom, tf_muscle, tf_video, tf_description, tf_type,tf_diff,tf_gym,tf_length,image;

    public AjoutExercice(Form previous) {
        super(BoxLayout.y());
        current = this;
        setTitle("Add Exercice");
        getContentPane().setScrollVisible(false);
        tf_nom=new TextField("","Name");
        tf_muscle=new TextField("","Muscle"); 
         tf_video=new TextField("","video");
         tf_description=new TextField("","Description");
        tf_type=new TextField("","Type");
        tf_diff=new TextField("","Diffculty");
         tf_gym=new TextField("","Only gym");
        tf_length=new TextField("","length");
        image =new TextField("", "image");
        Button btnadd=new Button("Add exercice");
        //choose file
        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Images");

        testImage.addActionListener(e -> {
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
                    Exercice e = new Exercice();
                e.setNomExercice(tf_nom.getText().toString());
                e.setMuscle(tf_muscle.getText().toString());
                e.setVideo(tf_video.getText().toString());
                e.setDescrExercice(tf_description.getText().toString());
                e.setTypeExercice(tf_type.getText().toString());
                e.setDiffExercice(tf_diff.getText().toString());
                e.setJusteSalleExercice(tf_gym.getText().toString());
                e.setDureeExercice(tf_length.getText().toString());    
                e.setImage(image.getText().toString());
                ExerciceService.getInstance().addExercice(e);
                new ListExerciceForm(previous).show();
                        refreshTheme();
                }
        });
        addAll(tf_nom, tf_muscle, tf_video, tf_description, tf_type,tf_diff,tf_gym,tf_length,testImage,btnadd);
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());
    }
}
