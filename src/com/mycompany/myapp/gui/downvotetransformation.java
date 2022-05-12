/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;
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
import com.mycompany.myapp.entities.Transformation;
import com.mycompany.myapp.services.servicetransformationfront;

import com.mycompany.myapp.gui.listtransformationfront;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author kriaa
 */
public class downvotetransformation extends Form{
Form previous;
public downvotetransformation(Transformation ev){
int id = ev.getIdImage();
servicetransformationfront.getInstance().downvoteTransformation(ev);
}
}
