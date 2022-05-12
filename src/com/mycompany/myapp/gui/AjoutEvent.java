/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.events;
import com.mycompany.myapp.services.EventService;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Heni Nechi
 */
public class AjoutEvent extends Form {

    Form current;
    TextField tf_creator, tf_titre, tf_descr, tf_adresse, tf_type, image;

    public AjoutEvent(Form previous) {
        super(BoxLayout.y());
        current = this;
        setTitle("Add event");
        getContentPane().setScrollVisible(false);

        tf_creator = new TextField("", "creator");
        tf_titre = new TextField("", "title");
        tf_descr = new TextField("", "description");
        tf_adresse = new TextField("", "Address");
        tf_type = new TextField("", "Type");
        image =new TextField("", "image");
        Picker tf_date = new Picker();
        tf_date.setType(Display.PICKER_TYPE_DATE);
        Button btnadd = new Button("Add event");
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
                events e = new events();
                e.setIDCreatorEvenement(tf_creator.getText().toString());

                e.setDateEvenement(tf_date.getDate());
                e.setTitreEvenement(tf_titre.getText().toString());
                e.setDescrEvenement(tf_descr.getText().toString());
                e.setAdresseEvenement(tf_adresse.getText().toString());
                e.setTypeEvenement(tf_type.getText().toString());
                e.setImage(image.getText().toString());
                try {
                    EventService.getInstance().addEvent(e);
                    new ListEventsForm(previous).show();
                    refreshTheme();
                } catch (ParseException ex) {

                }
            }
        });
        addAll(tf_creator, tf_titre, tf_descr, tf_adresse, tf_type, tf_date,testImage, btnadd);
        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

}
