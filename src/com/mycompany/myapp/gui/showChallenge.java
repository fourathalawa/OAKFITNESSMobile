/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.views.PieChart;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Challenge;
import com.mycompany.myapp.services.Challengeservices;
import com.mycompany.myapp.services.Userservices;
import com.mycompany.myapp.utils.SessionChallenge;
import com.mycompany.myapp.utils.SessionManager;
import static java.lang.Math.round;
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

             SpanLabel sp7 = new SpanLabel("Current IMC: " + "  " + SessionChallenge.getImcCur()+"%");
            SpanLabel sp8 = new SpanLabel("Object IMC: " + "  " + SessionChallenge.getImcob()+"%");
            addAll(spl, sp2, sp3, sp4, sp5, sp6,sp7, sp8);
                  Button back = new Button();
           TextField poidnv = new TextField("","Update your weight");

           Button update= new Button ("Update");
        
update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Challengeservices us=new Challengeservices();
                us.updatepoid(poidnv);
               new showChallenge().show();
            }
        });
           Button Delete1 = new Button("Delete");
            Delete1.addActionListener(e -> {
                Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer votre challenge!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                //User clicks on ok to delete account
                ok.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent evt) {
                     Challengeservices ch =new Challengeservices();
                             ch.deleteChallenge(SessionChallenge.getId());
                        alert.dispose();
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
                        status.setShowProgressIndicator(true);
                        //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                        status.setMessage("Challenge SUPPRIME AVEC SUCCES");
                         SessionChallenge.setId(0);
               SessionChallenge.setPoidinit(0);
              
               SessionChallenge.setPoidnv(0);
              
               SessionChallenge.setPoidob(0);
               SessionChallenge.setTaille(0);
              
               SessionChallenge.setId_user(0);
               SessionChallenge.setDatedeb(null);
               SessionChallenge.setDatefin(null);
                        status.setExpires(100);
                        new showuser().show();

                        refreshTheme();
             //            new ListUserForm(previous).show();
                    }

                }
                        );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();

            });
            add(poidnv);
            add(update);
            add(Delete1);
              createPieChartForm();
}

    
 public DefaultRenderer buildCatRendrer(int []colors) {
       
        DefaultRenderer renderer = new DefaultRenderer();
        renderer.setLabelsTextSize(15);
        renderer.setLegendTextSize(15);
        renderer.setMargins(new int[] {20, 30, 15, 0});
       
        for(int color : colors) {
            SimpleSeriesRenderer simpleSeriesRenderer = new SimpleSeriesRenderer();
           
            simpleSeriesRenderer.setColor(color);
            renderer.addSeriesRenderer(simpleSeriesRenderer);
        }
        return renderer;
     }  
   
   
    public void createPieChartForm() {
       double prcntFeed = 100;
       
        double prcntRec = 0;
       
       
if(SessionChallenge.getPoidnv()!=0)
{
    prcntFeed = ((SessionChallenge.getPoidinit() - SessionChallenge.getPoidnv()) * 100) / (SessionChallenge.getPoidinit() - SessionChallenge.getPoidob());
        prcntRec = 100 - prcntFeed;
}
        //colors set:
        int[]colors = new int[]{0xf4b342, 0x52b29a};
       
        DefaultRenderer renderer = buildCatRendrer(colors);
        renderer.setLabelsColor(0x000000); // black color for labels.
       
        renderer.setZoomButtonsVisible(true);//zoom
        renderer.setLabelsTextSize(40);
        renderer.setZoomEnabled(true);
        renderer.setChartTitleTextSize(20);
        renderer.setDisplayValues(true);
        renderer.setShowLabels(true);
        SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
        r.setHighlighted(true);
       
        //CREATe the chart ...
        PieChart chart = new PieChart(buildDataset("title",Math.round(prcntFeed),Math.round(prcntRec)), renderer);
       
        // n7oto chart fi component
        ChartComponent c  = new ChartComponent(chart);
       
        String []messages = {
            "My Progress"
        };
       
        SpanLabel message = new SpanLabel(messages[0], "WelcomeMessage");
       
        Container cnt = BorderLayout.center(message);
        cnt.setUIID("Container");
        add(cnt);
        add(c);
               
               
    }

    private CategorySeries buildDataset(String title, double prcntFeed, double prcntRec) {
       
        CategorySeries series = new CategorySeries(title);
       
        series.add("In Progress",prcntRec);
        series.add("Done",prcntFeed);
       
        return series;
    }

 }