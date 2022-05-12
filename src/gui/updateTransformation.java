/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Dialog;
import com.codename1.components.SpanLabel;
import entities.Transformation;
import services.servicetransformationfront;
import gui.listtransformationfront;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.CheckBox;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.ui.Image;


/**
 *
 * @author kriaa
 */
public class updateTransformation  extends Form{
Form previous;

    public updateTransformation(Transformation ev) {
        super("Newsfeed", BoxLayout.y());

        setTitle("Transformation to Update ");

        TextField TitreImage = new TextField(ev.getTitreImage(), "Image Title", 20, TextField.ANY);
        TitreImage.setUIID("TextFieldBlack");

        TextField DescreptionImage = new TextField(ev.getDescreptionImage(), "DescreptionImage", 20, TextField.ANY);
        DescreptionImage.setUIID("TextFieldBlack");

        TextField ImageAvant = new TextField(ev.getImageAvant(), "ImageAvant ", 20, TextField.ANY);
        ImageAvant.setUIID("TextFieldBlack");

        TextField ImageApres = new TextField(ev.getImageApres(), "ImageApres", 20, TextField.ANY);
        ImageApres.setUIID("TextFieldBlack");
        
        TextField PoidAvant = new TextField(((Float)ev.getPoidAvant()).toString(), "PoidAvant", 20, TextField.ANY);
        PoidAvant.setUIID("TextFieldBlack");
        
        TextField PoidApres = new TextField(((Float)ev.getPoidApres()).toString(), "PoidApres", 20, TextField.ANY);
        PoidApres.setUIID("TextFieldBlack");
    
        TextField TailleAvant = new TextField(((Float)ev.getTailleAvant()).toString(), "TailleAvant", 20, TextField.ANY);
        TailleAvant.setUIID("TextFieldBlack");
        
        TextField TailleApres = new TextField(((Float)ev.getTailleApres()).toString(), "Tailleapres", 20, TextField.ANY);
        TailleAvant.setUIID("TextFieldBlack");

        Button btnModifier = new Button("Update");
        btnModifier.setUIID("Button");
//
        CheckBox multiSelect = new CheckBox("Multi-select");
        Button testImage = new Button("Browse Image Transformation Before");
        testImage.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        String pathLogo=file;
                        String exx=file.substring(file.lastIndexOf("."));
                        
                        ImageAvant.setText(pathLogo);
                        System.out.println("path :"+pathLogo+ " extension :" +exx);
                        
                        try {
                            Image img2 = Image.createImage(file);
                            this.add(new Label(img2));
                            this.revalidate();
                        } catch (Exception ex) {
                        }


                    }
               });
            }
        });
  CheckBox multiSelect2 = new CheckBox("Multi-select");
        Button testImage2 = new Button("Browse Image Transformation After");
        testImage2.addActionListener(e->{
            if (FileChooser.isAvailable()) {
                
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".pdf,application/pdf,.gif,image/gif,.png,image/png,.jpg,image/jpg,.tif,image/tif,.jpeg,.bmp", e2-> {
                    if(e2!=null && e2.getSource()!=null) {

                        String file = (String)e2.getSource();
                        String pathLogo=file;
                        String exx=file.substring(file.lastIndexOf("."));
                        
                        ImageApres.setText(pathLogo);
                        System.out.println("path :"+pathLogo+ " extension :" +exx);
                        
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
 this.add(testImage);
 this.add(testImage2);

        //OnClick Button
        btnModifier.addPointerPressedListener(l -> {

            ev.setTitreImage(TitreImage.getText());
            ev.setDescreptionImage(DescreptionImage.getText());
            ev.setImageAvant(ImageAvant.getText());
            ev.setImageApres(ImageApres.getText());
            ev.setPoidAvant(Float.parseFloat(PoidAvant.getText()));
            ev.setPoidApres(Float.parseFloat(PoidApres.getText()));
            ev.setTailleAvant(Float.parseFloat(TailleAvant.getText()));
            ev.setTailleApres(Float.parseFloat(TailleApres.getText()));
  
            //Appel a la methode UPDATE
            if (servicetransformationfront.getInstance().modifierTransformation(ev)) {
                //If True
                                   Dialog alert = new Dialog("Success");
                    SpanLabel message = new SpanLabel("Transformation modified !");
                    alert.add(message);
                    Button ok = new Button("Confirm");
                    ok.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                        new listtransformationfront(previous).show();
                        refreshTheme();
                        }
                    }
                    );
                    alert.add(ok);
                    alert.showDialog();
                 //new Listproduit(res).show();
            }
        });

        Label l2 = new Label("");
        Label l3 = new Label("");
        Label l4 = new Label("");
        Label l5 = new Label("");
        Label l6 = new Label("");
        Label l7 = new Label("");
        Label l8 = new Label("");
        Label l9 = new Label("");

        Label l1 = new Label();

        Container content = BoxLayout.encloseY(
                l1, l2,
             
                new FloatingHint(TitreImage),
                new FloatingHint(DescreptionImage),
                new FloatingHint(ImageAvant),
                new FloatingHint(ImageApres),
                new FloatingHint(PoidAvant),
                new FloatingHint(PoidApres),
                new FloatingHint(TailleAvant),
                new FloatingHint(TailleApres),
                btnModifier
             
        );

        add(content);
        show();
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> new listtransformationfront(this).show());
    }

}
