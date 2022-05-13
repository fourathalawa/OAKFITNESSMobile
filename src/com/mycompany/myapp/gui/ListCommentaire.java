/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
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
import com.mycompany.myapp.entities.Task;
import com.mycompany.myapp.services.ServiceTask;
import com.codename1.ui.events.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.ImageIO;
import com.mycompany.myapp.entities.Commentaire;
import com.mycompany.myapp.services.ServiceCommentaire;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author Heni Nechi
 */
public class ListCommentaire extends Form {

    Form current;
    TextField tf_creator, tf_titre, tf_descr, tf_adresse, tf_type;

    public ListCommentaire(Task e, Commentaire c, Form previous) {
        super(BoxLayout.y());

        current = this;

        setTitle("comments");
        getContentPane().setScrollVisible(false);
        Label Type = new Label("Publication :" + e.getPublication());
        add(Type);
        SpanLabel sp = new SpanLabel();
Form hi = new Form("ShareButton");
ShareButton sb = new ShareButton();
sb.setText("Share Screenshot");
add(sb);

Image screenshot = Image.createImage(hi.getWidth(), hi.getHeight());
hi.revalidate();
hi.setVisible(true);
hi.paintComponent(screenshot.getGraphics(), true);

String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
try(OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
    ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
} catch(IOException err) {
    Log.e(err);
}
sb.setImageToShare(imageFile, "image/png");

        ArrayList<Commentaire> pub = (ServiceCommentaire.getInstance().getAllTasks(e.getIdpublication()));
        for (Commentaire b : pub) {
            Label name = new Label("name :" +b.getUsernamep());
            Label Type1 = new Label("comment :" +b.getCommentaire());
            Label date = new Label("date :" +b.getDatecommentaire());
            Button btnDelete = new Button("Delete");
        Button btnadd =new Button("Edit");
        Button rep =new Button("report");


            add(name);
            add(Type1);
            add(date);
            add(btnDelete);
            add(btnadd);
            add(rep);



   btnDelete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    ServiceCommentaire.getInstance().Delete(b.getIdcommentaire());
                    btnDelete.addActionListener(e -> new ListTasksForm(current).show());
new ListCommentaire(e,c,previous).show();
                        refreshTheme();

                }
            });

          btnadd.addActionListener(ex -> new updateComm(b,current).show());
          rep.addActionListener(ex -> new AddReport(b,current).show());


//
        }
  TextField tfName = new TextField("", "comment");
        Button btnValider = new Button("Add comment");

        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfName.getText().length() == 0) || (tfName.getText().length() == 0)) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));

                } else {
                    try {
                        Commentaire t = new Commentaire(tfName.getText().toString(),e.getIdpublication());
                        if (ServiceCommentaire.getInstance().addCommentaire(t)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
new ListCommentaire(e,c,previous).show();
                        refreshTheme();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }

                }

            }
        });

        addAll(tfName, btnValider);

        getToolbar().addMaterialCommandToLeftBar("BACK", FontImage.MATERIAL_ARROW_BACK, ex -> previous.showBack());

    }

}