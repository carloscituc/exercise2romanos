/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2romanos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author CCITUC
 */
public class Exercise2romanos {

    //Array of values in roman numerals
    static final Map<Integer, Character> romanNumerals = new HashMap<>();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        boolean invalidOption = true;
        
        //Store all roman numerals in a map list
        romanNumerals.put(1,'I');
        romanNumerals.put(5,'V');
        romanNumerals.put(10,'X');
        romanNumerals.put(50,'L');
        romanNumerals.put(100,'C');
        romanNumerals.put(500,'D');
        romanNumerals.put(1000,'M');
        
        System.out.println(
            "Bienvenido\n"+
            "Este es un programa que recibe un valor numérico del 1 al 1000\n"+
            "y lo convierte en números romanos\n"
        );
        
        do{
            try{
               
                System.out.print(
                    "------- Ingresar datos -------\n"+
                    "Introduzca el número: "
                );
                //Get value introduce by user
                Scanner scanner = new Scanner(System.in);
                String number = scanner.nextLine();
                Integer.parseInt(number);
                
                //Verify if is a valid value
                if(Integer.parseInt(number) > 1000 ||
                    Integer.parseInt(number) == 0){
                    System.out.println(
                        "Número no válido. Rango correcto de 1 a 1000\n"
                    );
                    invalidOption = true;
                } else{
                    //If is valid number beetwen the range
                    System.out.println(
                        "------- Resultados -------\n"+
                        "Número ingresado: " + number +"\n"+
                        "Convertido en números romanos: " + getRomanNumeralsByNumber(number)
                    );
                    invalidOption = false;
                }
            }catch(java.lang.NumberFormatException e){
                System.out.println("No se introdujo un valor numérico\n");
            }
            
        } while (invalidOption);
        
        
    }
    
    static String getRomanNumeralsByNumber(String number){
        //Initializa roman numeral var
        String romanNumeral = "";
        
        
        
        //Execute funtion by length of number string
        switch(number.length()){
            //When the number has only units
            case 1:
                romanNumeral = getValueRoman(Integer.parseInt(number), 1);
                break;
            //When the number has tens and units
            case 2:                
                romanNumeral =
                    getValueRoman(Character.getNumericValue(number.charAt(0)), 10) +
                    getValueRoman(Character.getNumericValue(number.charAt(1)), 1)
                ;
                break;
            //When the number has hundreds, tens and units
            case 3:
                romanNumeral =
                    getValueRoman(Character.getNumericValue(number.charAt(0)), 100) +
                    getValueRoman(Character.getNumericValue(number.charAt(1)), 10) +
                    getValueRoman(Character.getNumericValue(number.charAt(2)), 1)
                ;
                break;
            //When the number has units of thousand, hundreds, tens and units
            case 4:
                romanNumeral =
                    getValueRoman(Character.getNumericValue(number.charAt(0)), 1000) +
                    getValueRoman(Character.getNumericValue(number.charAt(1)), 100) +
                    getValueRoman(Character.getNumericValue(number.charAt(2)), 10) +
                    getValueRoman(Character.getNumericValue(number.charAt(3)), 1)
                ;
                break;
        }
        
        return romanNumeral;
    }
    
    static String getValueRoman(int number, int valueByPosition){
        //Initialize the var that will contain the final roman number
        String romanNumeral = "";
        //Initializa the var when is necessary more than one char for roman number
        String romanNumeralSingle = "";
        
        //Numbers 1,2,3
        if(number > 0 && number < 4){
            //Get value in roman numeral single by 1, 10, 100
            romanNumeralSingle = Character.toString(
                    romanNumerals.get(1 * valueByPosition)
            );
            
            //Simulate addition of roman numerals
            for(int i = 0; i < number; i++){
                romanNumeral += romanNumeralSingle;
            }
        }
        
        //Number 4
        if(number == 4){
            //Get value in roman numeral single by 1, 10, 100
            String menorNumeralSingle = Character.toString(
                    romanNumerals.get(1 * valueByPosition)
            );
            //Get value for 5, or 50 or 500
            String mayorNumeralSingle = Character.toString(
                    romanNumerals.get(5 * valueByPosition)
            );
            //Simulate substraction
            romanNumeral = menorNumeralSingle + mayorNumeralSingle;
        }
        
        //Number 5
        if(number == 5){
            //Get value for 5, or 50 or 500
            romanNumeral = Character.toString(
                    romanNumerals.get(5 * valueByPosition)
            );
        }
        
        //Number 6,7,8
        if(number > 5 &&
            number < 9){
            String menorNumeralSingle = "";
            //Get all the roman numerals exceeding 5
            for(int i = 0; i < (number - 5); i++){
                //Get value in roman numeral single by 1, 10, 100
                menorNumeralSingle += Character.toString(
                        romanNumerals.get(1 * valueByPosition)
                );
            }
            //Get value for 5, or 50 or 500
            String mayorNumeralSingle = Character.toString(
                    romanNumerals.get(5 * valueByPosition)
            );
            //Simulate addition
            romanNumeral = mayorNumeralSingle + menorNumeralSingle;
        }
        
        //Number 9
        if(number == 9){
            //Get value in roman numeral single by 1, 10, 100
            String menorNumeralSingle = Character.toString(
                    romanNumerals.get(1 * valueByPosition)
            );
            //Get value in roman numeral single by 10, 100, 1000
            String mayorNumeralSingle = Character.toString(
                    romanNumerals.get(10 * valueByPosition)
            );
            //Simulate substraction
            romanNumeral = menorNumeralSingle + mayorNumeralSingle;
        }
        
        
        return romanNumeral;
    }
}
