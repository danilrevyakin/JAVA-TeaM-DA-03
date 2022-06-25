package view.exam;

import javafx.geometry.Orientation;
import javafx.scene.control.Separator;

public class SeparatorFactory {

    public Separator getSeparator(Orientation orientation, double size, boolean visible){
        Separator separator = new Separator();
        separator.setOrientation(orientation);
        if(orientation == Orientation.VERTICAL){
            separator.setPrefHeight(size);
        }else{
            separator.setPrefWidth(size);
        }
        separator.setVisible(visible);
        return separator;
    }


}
