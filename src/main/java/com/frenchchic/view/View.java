package com.frenchchic.view;

import java.net.URL;

public abstract class View {
    public abstract String nomFile();
    public URL ressource;
    public View(){
        ressource = getClass().getResource("/view/"+nomFile()+".fxml");
    }
}
