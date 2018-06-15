/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorygui;

/**
 *
 * @author james
 */

import javafx.beans.property.SimpleStringProperty;
import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Item {
    
    private final SimpleStringProperty productName = new SimpleStringProperty("");
    private final SimpleStringProperty description = new SimpleStringProperty("");
    private final SimpleFloatProperty cost = new SimpleFloatProperty(0);
    private final SimpleFloatProperty price = new SimpleFloatProperty(0);
    private final ObjectProperty<LocalDate> date;
    private final SimpleIntegerProperty quantityIn = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty quantityOut = new SimpleIntegerProperty(0);
    private final SimpleIntegerProperty total = new SimpleIntegerProperty(0);
    
    public Item(String productName, String description, Float cost, Float price, LocalDate date, int quantityIn, int quantityOut, int total){
        this.setProductName(productName);
        this.setDescription(description);
        this.setCost(cost);
        this.setPrice(price);
        this.date = new SimpleObjectProperty<>(this, "date", date);
        this.setQuantityIn(quantityIn);
        this.setQuantityOut(quantityOut);
        this.setTotal(total);
    }
    
    public void setProductName(String productName){
        this.productName.set(productName);
    }
    public String getProductName(){
        return this.productName.get();
    }
    
    public void setDescription(String description){
        this.description.set(description);
    }
    public String getDescription(){
        return this.description.get();
    }
    
    public void setCost(float cost){
        this.cost.set(cost);
    }
    public float getCost(){
        return this.cost.get();
    }

    public void setPrice(float price){
        this.price.set(price);
    }
    public float getPrice(){
        return this.price.get();
    }
    
    public void setDate(LocalDate date){
        this.date.set(date);
    }
    public LocalDate getDate(){
        return this.date.get();
    }
    
    public void setQuantityIn(int quantityIn){
        this.quantityIn.set(quantityIn);
    }
    public int getQuantityIn(){
        return this.quantityIn.get();
    }
    
    public void setQuantityOut(int quantityOut){
        this.quantityOut.set(quantityOut);
    }
    public int getQuantityOut(){
        return this.quantityOut.get();
    }
    
    public void setTotal(int total){
        this.total.set(total);
    }
    public int getTotal(){
        return this.total.get();
    }
    
}
