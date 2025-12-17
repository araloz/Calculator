/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package advancejavaproject1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author yigitt
 */
public class CalculatorFXMLController implements Initializable {
    Calculator calculator = new Calculator();

    @FXML
    private Button btnZero;
    @FXML
    private Button btnOne;
    @FXML
    private Button btnTwo;
    @FXML
    private Button btnThree;
    @FXML
    private Button btnFour;
    @FXML
    private Button btnFive;
    @FXML
    private Button btnSix;
    @FXML
    private Button btnSeven;
    @FXML
    private Button btnEight;
    @FXML
    private Button btnNine;
    @FXML
    private TextField tfResult;
    @FXML
    private Button btnRemoveLast;
    @FXML
    private Button btnRemoveAll;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnSubstraction;
    @FXML
    private Button btnMultiplication;
    @FXML
    private Button btnDivide;
    @FXML
    private Button btnEqual;
    @FXML
    private Button btnNegation;
    @FXML
    private Button btnLn;
    @FXML
    private Button btnLog;
    @FXML
    private Button btnTenPower;
    @FXML
    private Button btnSin;
    @FXML
    private Button btnCos;
    @FXML
    private Button btnTan;
    @FXML
    private Button btnSec;
    @FXML
    private Button btnCsc;
    @FXML
    private Button btnCot;
    @FXML
    private Button btnSqrt;
    @FXML
    private Button btnDecimal;
    @FXML
    private Button btnPi;
    @FXML
    private Button btnXPowY;
    @FXML
    private Button btnXSqr;
    @FXML
    private Button btnFactorial;
    @FXML
    private Button btnLeftPar;
    @FXML
    private Button btnRightPar;
    @FXML
    private Button btnMod;
    @FXML
    private Button btnExp;
    @FXML
    private Button btnE;
    @FXML
    private Button btnOneDivX;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        updateDisplay();
        tfResult.setEditable(false);
    }    

    @FXML
    private void clickNumber(MouseEvent event) {
        String digit = ((Button)event.getSource()).getText();
        calculator.clickNumber(digit);
        updateDisplay();
    }

    @FXML
    private void removeLast(MouseEvent event) {
        calculator.backSpace();
        updateDisplay();        
    }

    @FXML
    private void removeAll(MouseEvent event) {
        calculator.clearAll();
        updateDisplay();
       
    }
    
   

    @FXML
    private void handleOperation(MouseEvent event) {
        String command = ((Button)event.getSource()).getText();
        calculator.processOperator(command);
        updateDisplay();
    }

    @FXML
    private void calculateResult(MouseEvent event) {
       calculator.processEqual();
       updateDisplay();
        
    }

    public void updateDisplay() {
        tfResult.setText(calculator.getCurrentValue());
    }

    @FXML
    private void calculateTrigonometric(MouseEvent event) {
        String command = ((Button)event.getSource()).getText();
        calculator.processTrigonometricOperation(command);
        updateDisplay();
    }

    @FXML
    private void calculateLogaritmic(MouseEvent event) {
        String command = ((Button)event.getSource()).getText();
        calculator.processLogaritmicOperation(command);
        updateDisplay();
    }

    @FXML
    private void negateNum(MouseEvent event) {
        calculator.negateNum();
        updateDisplay();
    }

    @FXML
    private void handleDecimal(MouseEvent event) {
        calculator.clickNumber(".");
        updateDisplay();
    }

    @FXML
    private void handlePi(MouseEvent event) {
        String digit = String.valueOf(Math.PI);
        calculator.clickNumber(digit);
        updateDisplay();
    }

    @FXML
    private void handleXPowY(MouseEvent event) {
        calculator.processOperator("^");
        updateDisplay();
        
    }

    @FXML
    private void handleXSqr(MouseEvent event) {
        calculator.square();
        updateDisplay();
    }

    @FXML
    private void handleFactorial(MouseEvent event) {
        calculator.factorial();
        updateDisplay();
    }


    @FXML
    private void handleMod(MouseEvent event) {
        calculator.processOperator("%");
        updateDisplay();
    }

    @FXML
    private void handleExp(MouseEvent event) {
        calculator.exp();
        updateDisplay();
    }

    @FXML
    private void handleE(MouseEvent event) {
        String digit = String.valueOf(Math.E);
        calculator.clickNumber(digit);
        updateDisplay();
    }

    @FXML
    private void handleOneDivX(MouseEvent event) {
        calculator.oneDivX();
        updateDisplay();
    }

    @FXML
    private void handleCloseParanthesis(MouseEvent event) {
        calculator.processCloseParanthesis();
        updateDisplay();
    }
    
}
