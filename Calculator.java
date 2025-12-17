/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package advancejavaproject1;

import com.sun.webkit.Timer;
import java.util.Stack;

/*
 * @author yigitt
 */
public class Calculator {
    // operation kullanıldığında old valueyı set et
    private Stack<Double> numberStack;
    private Stack<String> operationsStack;
    private String currentValue;
    private boolean lastInputWasOperator;
    public Calculator(){
            numberStack = new Stack<>();
            operationsStack = new Stack<>();
            clearAll();
    }
    
    public String getCurrentValue(){
        return currentValue;
    }
    /*
 * @author yigitt
 */
    public void clickNumber(String digit){
        if(lastInputWasOperator || currentValue.equals("0")){
            currentValue = digit;
        } else{
            currentValue = currentValue.concat(digit);
        }
        lastInputWasOperator = false;
    }
    /*
 * @author yigitt
 */
    public void processOperator(String operator){
        if(operator.equals("(")){
            operationsStack.push(operator);
            currentValue = "(";
            lastInputWasOperator = true;
            return;
        }
        if(lastInputWasOperator){
            // kullanıcı fikir değiştirip farklı bir işlem uyuglamak isterse eğer.
            if(!operationsStack.empty()){
                operationsStack.pop();
            }
            operationsStack.push(operator);
            currentValue = operator;
            return ;
        }
        pushCurrentNumToStack();
        
        while(!operationsStack.isEmpty() && !operationsStack.peek().equals("(") &&
                getPrecedence(operationsStack.peek()) >= getPrecedence(operator))
        {
            applyTopOperation();
        }
        operationsStack.push(operator);
        lastInputWasOperator = true;
        currentValue = operator;
        
    }
    /*
 * @author yigitt
 */
    
    public void processEqual(){
        if(lastInputWasOperator){
            return;
        }
        pushCurrentNumToStack();
        
        while(!operationsStack.isEmpty()){
            applyTopOperation();
        }
        if(!numberStack.isEmpty()){
            double result = numberStack.pop();
            currentValue = formatDouble(result);
            numberStack.push(result);
        }
        lastInputWasOperator = true;
        
    }
    
    /*
 * @author yigitt
 */
    public String formatDouble(double d){
        if(d == (long)d){
            // tamsayıysa eğer 0 lık kısmını at
            return String.format("%d",(long)d ); // longu stringe dönüştürür(int)
        }else{
            // değilse olduğu gibi bırak
            return String.format("%s", d);// oldugu gibi yazar
        }
    }

    /*
 * @author yigitt
 */
    public void pushCurrentNumToStack() 
    {
        try{
            double val = Double.parseDouble(currentValue);
            numberStack.push(val);
            
        }catch(Exception e){
            handleError("Error during parsing current value to double");
            System.out.println(e);
            
        }
    }
    
    /*
 * @author yigitt
 */
    public void clearAll(){
        numberStack.clear();
        operationsStack.clear();
        currentValue = "0";
        lastInputWasOperator = false;
    }
    /*
 * @author yigitt
 */
    public void backSpace(){
        if(lastInputWasOperator){
            return;
        }
        if(currentValue.length() > 1){
            currentValue = currentValue.substring(0, currentValue.length()-1);
        }else{
            currentValue = "0";
        }
    }
    /*
 * @author yigitt and aral
 */
    public int getPrecedence(String operator){
        switch (operator){
            case "+":
            case "-":
                return 1;
            case "X":
            case "÷":
                return 2;
            case "^":
                return 3;
            case "%":
                return 3;
            default:
                return 0;
        }
        
    }
    /*
 * @author yigitt
 */
    public void applyTopOperation(){
        try{
            if(!(operationsStack.isEmpty() || numberStack.size() < 2)){
                double lastNum = numberStack.pop();
                double firstNum = numberStack.pop();
                String operation = operationsStack.pop();
                double result = 0.0;
                switch(operation){
                    case "+":
                        result = lastNum + firstNum;
                        break;
                    case "-":
                        result = firstNum - lastNum;
                        break;
                    case "X":
                        result = firstNum * lastNum;
                        break;
                    case "÷":
                        if(lastNum == 0){
                            handleError("The divisor cannot be 0");
                            return;
                        }
                        result = firstNum / lastNum;
                        break;
                    case "^":
                        result = Math.pow(firstNum, lastNum);
                        break;
                    case "%":
                        result = firstNum % lastNum;
                }
                numberStack.push(result);     
            }
            
        }catch(Exception e){
            handleError("Error during applying operation");
            System.out.println(e);
        }
        
        
}
    /*
 * @author yigitt
 */
    public void handleError(String s){
        currentValue = s;
        lastInputWasOperator = false;
        numberStack.clear();
        operationsStack.clear();
           
    }

    /*
 * @author yigitt
 */
    public void processTrigonometricOperation(String command) {
        
        
            try{
               double convertedCurrentValue = Double.parseDouble(currentValue);
               double result = 0.0;
               switch(command){
                   case "csc":
                       if(Math.sin(Math.toRadians(convertedCurrentValue)) == 0){
                           handleError("Calculation Error");
                           return;
                       }
                       result = 1 / Math.sin(Math.toRadians(convertedCurrentValue));
                       break;
                       
                   case "sec":
                       if(Math.cos(Math.toRadians(convertedCurrentValue)) == 0){
                           handleError("Calculation Error");
                           return;
                       }
                       result = 1 / Math.cos(Math.toRadians(convertedCurrentValue));
                       break;
                       
                   case "cot":
                       if(Math.tan(Math.toRadians(convertedCurrentValue)) == 0){
                           handleError("Calculation Error");
                           return;
                       }
                       result = 1 / Math.tan(Math.toRadians(convertedCurrentValue));
                       break;
                       
                   case "cos":
                       result = Math.cos(Math.toRadians(convertedCurrentValue));
                       break;
                       
                   case "sin":
                       result = Math.sin(Math.toRadians(convertedCurrentValue));
                       break;
                       
                   case "tan":
                       result = Math.tan(Math.toRadians(convertedCurrentValue));
                       break;     
               } 
               currentValue = formatDouble(result);
                
            }catch(Exception e){
                handleError("Calculation Error");
                System.out.println(e);
            
        }
        
    }
    /*
 * @author yigitt
 */
    
    public void processLogaritmicOperation(String command){
        
            try{
                double convertedCurrentValue = Double.parseDouble(currentValue);
                double result = 0.0;
        
               switch(command){
                   case "√x":
                       if(convertedCurrentValue < 0){
                           handleError("Calculation Error");
                           return;
                       }
                       result = Math.sqrt(convertedCurrentValue);
                       break;
                       
                   case "10ˣ":
                       result = Math.pow(10,convertedCurrentValue);
                       break;
                       
                   case "log":
                       result = Math.log10(convertedCurrentValue);
                       break;
                       
                   case "ln":
                       result = Math.log(convertedCurrentValue);
                       break;  
               } 
               currentValue = formatDouble(result);
                
            }catch(Exception e){
                handleError("Calculation Error");
                System.out.println(e);
            }
        
        
    }/*
 * @author yigitt
 */

    public void negateNum() {
            
                if(currentValue.equals("0")){
                    return;
                }
                try{
                    Double.parseDouble(currentValue);
                    if(currentValue.startsWith("-")){
                        currentValue = currentValue.substring(1);
                    }else {
                        currentValue = "-" + currentValue;
                    } 
                    // yani eşittire bastık ve bize bir sonuc veri
                    if(lastInputWasOperator && !numberStack.isEmpty()){
                        numberStack.pop();
                        numberStack.push(Double.parseDouble(currentValue));
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }
    /**
     *@author aral
    */
    public void square(){
        try{
            double convertedCurrentValue = Double.parseDouble(currentValue);
            double result = Math.pow(convertedCurrentValue, 2);
            currentValue = formatDouble(result);
        }catch(Exception e){
            handleError("Calculation Error");
            System.out.println(e);
        }
    }
    /**
     *@author aral
    */
    public void factorial(){
        try{
            double convertedCurrentValue = Double.parseDouble(currentValue);
        
            //negatif ve ondalık sayılarda hata vermeli
            if(convertedCurrentValue < 0 || convertedCurrentValue != Math.floor(convertedCurrentValue)){
                handleError("Invalid input");
                return;
            }
        
            long n = (long)convertedCurrentValue;
            long result = 1;
        
            for (long i = 2; i <= n; i++) {
                result *= i;
                //Sayı long değerini aşarsa hata vermeli
                if(result < 0){
                    handleError("Overflow");
                    return;
                }
            }
        
            currentValue = formatDouble(result);
        }catch(Exception e){
            handleError("Calculation Error");
            System.out.println(e);
        }   
    }
    /**
     *@author aral
    */
    public void exp(){
        try{
            double convertedCurrentValue = Double.parseDouble(currentValue);
            double result = Math.exp(convertedCurrentValue);
            currentValue = formatDouble(result);
        }catch(Exception e){
            handleError("Calculation Error");
            System.out.println(e);
        }
    }
    /**
     *@author aral
    */
    public void oneDivX(){
        try{
            double convertedCurrentValue = Double.parseDouble(currentValue);
            
            if(convertedCurrentValue == 0){
                handleError("Can not divide by 0");
                return;
            }
            
            double result = 1/convertedCurrentValue;
            currentValue = formatDouble(result);
        }catch(Exception e){
            handleError("Calculation Error");
            System.out.println(e);
        }
    }

    public void processCloseParanthesis() {
        if(lastInputWasOperator){
            handleError("Calculation Error");
            return;
        }
        
        pushCurrentNumToStack();
        
        while(!operationsStack.isEmpty()&&!operationsStack.peek().equals("(")){
            applyTopOperation();
        } 
        
        operationsStack.pop();
        if(!numberStack.isEmpty()){
            currentValue = formatDouble(numberStack.peek());
        }
        lastInputWasOperator = false;
        
    }

            
    
}
    
    
    
    
        
    
    

  


  


