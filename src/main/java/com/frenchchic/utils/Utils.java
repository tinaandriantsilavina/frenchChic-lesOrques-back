package com.frenchchic.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

public class Utils {
    public static FXMLLoader getFxml(String path)throws Exception{
        FXMLLoader fxmlLoader =  new FXMLLoader(Utils.class.getResource(path));
        fxmlLoader.load();
        return fxmlLoader;
    }
}
