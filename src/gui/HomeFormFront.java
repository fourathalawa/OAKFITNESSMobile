/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package gui;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author kriaa
 */
public class HomeFormFront extends Form {
Form current;
 public HomeFormFront() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("User Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddTask = new Button(" Shop  ");
        Button btnAddTask1 = new Button("Transformation Forum");


        btnAddTask.addActionListener(e -> new Listproduitfront(current).show());
        btnAddTask1.addActionListener(e -> new listtransformationfront(current).show());
        //btnAddTask1.addActionListener(e -> new Stat().createPieChartForm().show());
        addAll(btnAddTask,btnAddTask1);

    }
}
