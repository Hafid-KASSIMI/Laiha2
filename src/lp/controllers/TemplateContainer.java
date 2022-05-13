/*
 * Copyright (C) 2020 Sicut
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
/* 
    Author     : H. KASSIMI
*/

package lp.controllers;

import java.io.IOException;
import javafx.beans.property.BooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import lp.Settings;

public class TemplateContainer {
    
    private TemplateContainerController tcc;
    private Parent root;
    
    public TemplateContainer() {
        try {
            FXMLLoader fl = new FXMLLoader(getClass().getResource("/lp/views/TemplateContainer.fxml"), Settings.I18N_BUNDLE);
            root = fl.load();
            tcc = fl.getController();
            root.setOnMouseEntered(evt -> {
                root.getStyleClass().add("hover");
            });
            root.setOnMouseExited(evt -> {
                root.getStyleClass().remove("hover");
            });
        } catch(IOException ex) { }
    }

    public void setTemplate(int template) {
        if ( tcc != null )
            tcc.setTemplate(template);
    }

    public void select(Boolean selected) {
        if ( tcc != null )
            tcc.select(selected);
    }

    public Parent getRoot() {
        return root;
    }
    
    public void attachListener(EventHandler previewEvt, EventHandler settingsEvt) {
        tcc.attachListener(previewEvt, settingsEvt);
    }
    
    public boolean isSeleceted() {
        return tcc.isSelected();
    }
    
    public BooleanProperty selecetedProperty() {
        return tcc.selecetedProperty();
    }
    
}
