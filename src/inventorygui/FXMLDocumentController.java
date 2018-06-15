/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventorygui;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author james
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField 
            productNameField,
            costField,
            priceField,
            quantityInField,
            quantityOutField,
            totalOnHandField;
    
    @FXML
    private TextArea descriptionField;
    
    @FXML
    private DatePicker dateField;

    @FXML
    private TableView<Item> itemTable;
    
    @FXML
    private TableColumn 
            itemProductNameColumn, 
            itemDescriptionColumn, 
            itemCostColumn, 
            itemPriceColumn, 
            itemDateColumn, 
            itemQuantityInColumn, 
            itemQuantityOutColumn, 
            itemTotalColumn;
    
    @FXML
    private ObservableList<Item> data;
    
    private File file;
    
    private DateTimeStringConverter dateConverter;
    
    private String datePattern;
    
    @FXML
    private void saveButtonAction(ActionEvent event){
        //get product name entered
        String productName;
        if(productNameField.getText().isEmpty()){
            productName = "";
        }else{
            productName = productNameField.getText();
            System.out.print("Name of product:");
            System.out.println(productName);
        }
        
        //get product description
        String description;
        if(descriptionField.getText().isEmpty()){
            description = "";
        }else{
            description = descriptionField.getText();
            System.out.print("Description of product:");
            System.out.println(description);
        }
        
        //get product purchase cost
        Float cost;
        if(costField.getText().isEmpty()){
            cost = 0.0f;
        }else{
            cost = Currency(costField.getText());
            String productCost = costField.getText();
            System.out.print("Production/purchase cost of product:");
            System.out.println(productCost);
        }
        
        //get product selling price
        Float price;
        if(priceField.getText().isEmpty()){
            price = 0.0f;
        }else{
            price = Currency(priceField.getText());
            String productPrice = priceField.getText();
            System.out.print("Sell price of product:");
            System.out.println(productPrice);
        }
        
        //get date the product inventory was last checked
        LocalDate date = dateField.getValue();
        System.out.print("Date the products were checked for inventory:");
        System.out.println(date);
        
        //get quantity of product input since last update
        int quantityIn;
        if(quantityInField.getText().isEmpty()){
            quantityIn = 7;
        }else{
            quantityIn = 6;
            String quantityInString = quantityInField.getText();
            System.out.print("Quantity of product input:");
            System.out.println(quantityInString);
        }
        
        //get quantity of product output since last update
        int quantityOut;
        if(quantityOutField.getText().isEmpty()){
            quantityOut = 0;
        }else{
            quantityOut = 1;
            String quantityOutString = quantityOutField.getText();
            System.out.print("Quantity of product output:");
            System.out.println(quantityOut);
        }
                //quantityOutField.getText();
        int totalOnHand;
        //get total quantity of product on hand at this date
        if(totalOnHandField.getText().isEmpty()){
            totalOnHand = 7;
        }else{
            totalOnHand = 0;
            String totalOnHandString = totalOnHandField.getText();
            System.out.print("Total on hand:");
            System.out.println(totalOnHandString);
        }
                //totalOnHandField.getText();
        
        Item inventoried = new Item(
                productName, 
                description, 
                cost,
                price,
                date,
                quantityIn,
                quantityOut,
                totalOnHand
        );
        
        data.add(inventoried);  
    }
    
    @FXML
    private void cancelButtonAction(ActionEvent event){
        productNameField.clear();
        descriptionField.clear();
        costField.clear();
        priceField.clear();
        quantityInField.clear();
        quantityOutField.clear();
        totalOnHandField.clear();
    }

    @FXML
    private void newFile(ActionEvent event){
        
    }
    
    @FXML
    private void openFile(ActionEvent event){
        
        //DateTimeStringConverter.getLocalDateStringConverter(formatter, null);
        
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Comma Separated Values files (*.csv)", "*.csv");
        chooser.getExtensionFilters().add(filter);
        file = chooser.showOpenDialog(productNameField.getScene().getWindow());
        if(file != null){
            try{
                Scanner scanner = new Scanner(file);
                String line;
                if(scanner.hasNextLine()){
                    line = scanner.nextLine();
                    String[] columnNames = line.split(";");
                }
                while(scanner.hasNextLine()){

                    String productName, description;
                    float cost, price;
                    LocalDate date;
                    int quantityIn, quantityOut, totalOnHand;

                    line = scanner.nextLine();

                    String[] columnValues = line.split(";");
                    for(String value : columnValues){
                        System.out.print(value);    
                    }

                    productName = columnValues[0];
                    description = columnValues[1];
                    cost = Currency(columnValues[2]);
                    price = Currency(columnValues[3]);
                    date = dateConverter.fromString(columnValues[4]).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    quantityIn = (int)Currency(columnValues[5]);
                    quantityOut = (int)Currency(columnValues[6]);
                    totalOnHand = (int)Currency(columnValues[7]);

                    Item inventoried = new Item(
                    productName, 
                    description, 
                    cost,
                    price,
                    date,
                    quantityIn,
                    quantityOut,
                    totalOnHand
            );

            data.add(inventoried); 
                }
            }catch(FileNotFoundException e){

            }    
        }
    }
    
    @FXML
    private void saveFile(ActionEvent event){
        
    }
    
    @FXML
    private void saveFileAs(ActionEvent event){
        FileChooser chooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Comma Separated Values files (*.csv)", "*.csv");
        chooser.getExtensionFilters().add(filter);
        file = chooser.showSaveDialog(productNameField.getScene().getWindow());
        if(file != null){
            
            for(Item i : data){
                
            }
        }
                //showOpenDialog(productNameField.getScene().getWindow());
    }
    
    @FXML
    private void exit(ActionEvent event){
        
    }        
    
    private float Currency(String currencyString){
        float currency = 0.0f;
        if(currencyString.matches("\\d*([\\.]\\d{0,2})?")){
            for(char c : currencyString.toCharArray()){
                switch(c){
                    case '0':
                        currency += 0;
                        break;
                    case '1':
                        currency += 1;
                        break;
                    case '2':
                        currency += 2;
                        break;
                    case '3':
                        currency += 3;
                        break;
                    case '4':
                        currency += 4;
                        break;
                    case '5':
                        currency += 5;
                        break;
                    case '6':
                        currency += 6;
                        break;
                    case '7':
                        currency += 7;
                        break;
                    case '8':
                        currency += 8;
                        break;
                    case '9':
                        currency += 9;
                        break;
                    default:
                        currency *= 10;
                        break;
                }
            }
        }
        return currency;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        itemProductNameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));
        itemDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        itemCostColumn.setCellValueFactory(new PropertyValueFactory<>("cost"));
        itemPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        itemDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        itemQuantityInColumn.setCellValueFactory(new PropertyValueFactory<>("quantityIn"));
        itemQuantityOutColumn.setCellValueFactory(new PropertyValueFactory<>("quantityOut"));
        itemTotalColumn.setCellValueFactory(new PropertyValueFactory<>("total"));
        
        data = FXCollections.observableArrayList();
        itemTable.setItems(data);

        ChangeListener<String> currencyChecker = new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable, 
                    String oldValue, 
                    String newValue) {
                    if(!newValue.matches("\\d*([\\.]\\d{0,2})?")){
                        StringProperty property = (StringProperty) observable;
                        TextField field = (TextField) property.getBean();
                        field.setText(oldValue);
                    }
            }
        };
        costField.textProperty().addListener(currencyChecker);
        priceField.textProperty().addListener(currencyChecker);
        ChangeListener<String> quantityChecker = new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable, 
                    String oldValue, 
                    String newValue) {
                    if(!newValue.matches("\\d*")){
                        StringProperty property = (StringProperty) observable;
                        TextField field = (TextField) property.getBean();
                        field.setText(oldValue);
                    }
            }
        };
        quantityInField.textProperty().addListener(quantityChecker);
        quantityOutField.textProperty().addListener(quantityChecker);
        totalOnHandField.textProperty().addListener(quantityChecker);
        
        datePattern = "dd/MM/yyyy";
        dateConverter = new DateTimeStringConverter(datePattern);
        
        
    }    
    
}
