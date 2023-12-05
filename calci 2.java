package calculator;

import java.awt.EventQueue;

import java.util.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Array;
import java.util.Stack;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JToggleButton;

public class calci {

	private JFrame frame;
	private JTextField textField;
		
	
	//Basic variables to keep the storage all different functions.
	double num1;
	double num2;
	String operation;
	
	ArrayList<Double> numberArray = new ArrayList<Double>(); //Will initialize the arrayList which will keep track of all numbers entered by user.
	
	double number;
	
	double result;
	String answer = null;
	
	//The Stack will be saved to showCase the last calculated history.
    Stack<String> history = new Stack<String>();
    
    //Have to create a temporary stack which doesn't show any purpose as showing us history but does help us to Clear out the extra number after each function.
    Stack<String> temp = new Stack<String>();
    private JTextField textField_1;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					calci window = new calci();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public calci() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setForeground(Color.YELLOW);
		frame.setBackground(Color.YELLOW);
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setForeground(new Color(0, 255, 255));
		frame.setBounds(100, 100, 655, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionForeground"));
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			}
		});
		
		textField.setBounds(6, 6, 506, 57);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() 
		{
			
			//The purpose of the clear buttons is to empty out the whole program. It will delete the stacks, array and empty out the textField.
			
			public void actionPerformed(ActionEvent e) 
			{
				textField.setText(null); //Clears out the textField.
				
				while(!history.isEmpty()){ //Empties the stack.
					history.pop();				
				}
				
				while(!numberArray.isEmpty()){ //Empties the array.
					numberArray.clear();
				}
				
				textField_1.setText(""); //Also clears out the second textField.
			}
			
			
		});
		
		JButton btnNegative = new JButton("(-)");
		btnNegative.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				//Will add the computations for negative numbers and a way to add the negative sign in our expressions.
				
				double negativeSign = Double.parseDouble(textField.getText()); //Will store whatever the textField is into a double Variable.
				negativeSign = negativeSign * -1; //Multiplies the TextField number with -1, which makes the whole expression negative.
				textField.setText(String.valueOf(negativeSign).replaceAll(".0+$", "")); //Prints out the negative number in textField, and replaces all unnecessary spaces with blank.
				
			}
		});
		btnNegative.setBounds(0, 75, 127, 46);
		frame.getContentPane().add(btnNegative);
	
		
		btnClear.setBounds(126, 75, 127, 46);
		frame.getContentPane().add(btnClear);
		
		JButton btn9 = new JButton("9");
		btn9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btn9.getText();//Will store the 9 into a String.
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btn9.setBounds(126, 119, 127, 46);
		frame.getContentPane().add(btn9);
		
		JButton btn6 = new JButton("6");
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField(); 
				String number = textField.getText()+btn6.getText(); //Will store 6 into a String.
				textField.setText(number); //Will show the string into textField.
			}
		});
		btn6.setBounds(126, 163, 127, 46);
		frame.getContentPane().add(btn6);
		
		JButton btn3 = new JButton("3");
		btn3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btn3.getText();//Will return the 3.
				textField.setText(number); //Will set the textField as 3.
			}
		});
		btn3.setBounds(126, 206, 127, 46);
		frame.getContentPane().add(btn3);
		
		JButton btnHistory = new JButton("History");
		btnHistory.setBackground(new Color(224, 255, 255));
		btnHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				textField.setText(history.toString().replaceAll(",", "")); //Will print out the history stacks. Will replace all the unnecessary spaces.
				textField.getText();
			}
		});
		btnHistory.setBounds(126, 245, 127, 46);
		frame.getContentPane().add(btnHistory);
		
		JButton btnBackSpace = new JButton("backspace");
		btnBackSpace.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				if(textField.getText().length() > 0)
				{
					//In this part, we will first create a String builder which will contain textField as SB, then we will delete a specific char in end and then show it in text.
					String number;
					StringBuilder str = new StringBuilder(textField.getText());
					str.deleteCharAt((textField.getText().length() - 1)); //Will delete each char at a time. User can delete any chars until the list is empty.
					number = str.toString();
					textField.setText(number);
				}
			
			}
		});
		btnBackSpace.setBounds(256, 75, 127, 46);
		frame.getContentPane().add(btnBackSpace);
		
		JButton btnRem = new JButton("%");
		btnRem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				   //Inspired By Iphone's calculations.
					num1 = Double.parseDouble(textField.getText());
					result = num1 / 100; //Will give us the value of any number's percentage.
					textField.setText(String.valueOf(result));
				
			}
		});
		btnRem.setBounds(385, 75, 127, 46);
		frame.getContentPane().add(btnRem);
		
		JButton btn8 = new JButton("8");
		btn8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				clearTextField();
				String number = textField.getText()+btn8.getText();
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btn8.setBounds(256, 119, 127, 46);
		frame.getContentPane().add(btn8);
		
		JButton btn5 = new JButton("5");
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btn5.getText();
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btn5.setBounds(256, 163, 127, 46);
		frame.getContentPane().add(btn5);
		
		JButton btn2 = new JButton("2");
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField(); //Will call the clearTextField method.
				String number = textField.getText()+btn2.getText(); //Will return the 2.
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btn2.setBounds(256, 206, 127, 46);
		frame.getContentPane().add(btn2);
		
		JButton btndot = new JButton(".");
		btndot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btndot.getText(); //Will let the user enter a dot (.) !!
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btndot.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		btndot.setBounds(256, 245, 127, 46);
		frame.getContentPane().add(btndot);
		
		JButton btn7 = new JButton("7");
		btn7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btn7.getText();
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btn7.setBounds(385, 119, 127, 46);
		frame.getContentPane().add(btn7);
		
		JButton btn4 = new JButton("4");
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btn4.getText();
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			}
		});
		btn4.setBounds(385, 163, 127, 46);
		frame.getContentPane().add(btn4);
		
		JButton btn1 = new JButton("1");
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
				String number = textField.getText()+btn1.getText();
				textField.setText(number); //This line will set our current empty textField with number, which we have set up.
			
			}
		});
		btn1.setBounds(385, 206, 127, 46);
		frame.getContentPane().add(btn1);
		
		JButton btnZero = new JButton("0");
		btnZero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				clearTextField();
			    String number = textField.getText()+btnZero.getText(); //If pressed the button, will set the textField as 0.
				textField.setText(number); //Will print out the textField.
			}
		});
		btnZero.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		btnZero.setBounds(385, 245, 127, 46);
		frame.getContentPane().add(btnZero);
		
		JButton btnAdd = new JButton("+");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				number = Double.parseDouble(textField.getText()); //Changes the textField into double and stores it into a number.
				numberArray.add(number);//Adds the number in ArrayList.
				operation = "+";//Sets the operation into +, Will be used as if Statement while doing the computations.
				textField.setText("");//Will set the textField as empty so we can store another number in array for doing a computations.
				
				history.push(String.valueOf("{ " + number + " " + operation + " "));//Will keep the track of first number and an operation.
				temp.push(String.valueOf(number + " " + operation + " "));//Will do the same thing above. However used only so we can clear out the textField after each operation.
				

				textField_1.setText(operation); //Will let user know that they have entered a +sign.

			
			}
		});
		btnAdd.setBounds(514, 76, 127, 46);
		frame.getContentPane().add(btnAdd);
		
		JButton btnSubtract = new JButton("-");
		btnSubtract.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Will set up the button which can do subtraction.
				number = Double.parseDouble(textField.getText());
				numberArray.add(number);
				operation = "-";
				textField.setText("");
				
				history.push(String.valueOf("{ " + number + " " + operation + " "));
				temp.push(String.valueOf(number + " " + operation + " "));
				

				textField_1.setText(operation); //Will let user know that they have entered a +sign.
			}
		});
		btnSubtract.setBounds(515, 119, 127, 46);
		frame.getContentPane().add(btnSubtract);
		
		JButton btnMultiply = new JButton("*");
		btnMultiply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Will store the textField string into double number and then add that into ArrayList. Will compute the multiplication operation.
				number = Double.parseDouble(textField.getText());
				numberArray.add(number);
				operation = "*";
				textField.setText("");
				
				history.push(String.valueOf("{ " + number + " " + operation + " "));
				temp.push(String.valueOf(number + " " + operation + " "));
				

				textField_1.setText(operation); //Will let user know that they have entered a +sign.
				
			}
		});
		btnMultiply.setBounds(515, 163, 127, 46);
		frame.getContentPane().add(btnMultiply);
		
		JButton btnDivide = new JButton("÷");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				//Will store the textField string into double number and then add that into ArrayList.
				
				number = Double.parseDouble(textField.getText());
				numberArray.add(number);
				operation = "÷";
				textField.setText("");
				
				history.push(String.valueOf("{ " + number + " " + operation + " "));
				temp.push(String.valueOf(number + " " + operation + " "));

				textField_1.setText(operation); //Will let user know that they have entered a +sign.
			}
		});
		btnDivide.setBounds(515, 206, 127, 46);
		frame.getContentPane().add(btnDivide);
		
		JButton btnEqual = new JButton("=");
		btnEqual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				number = Double.parseDouble(textField.getText()); //Will convert the textField String to double and then store it in variable Number.
				numberArray.add(number); //Will add that number to array.
								
				if(operation == "+")
				{		
					//First, as we have created an array List, we will give make result = 0, and then find sum of all numbers.	
					double result = 0;
					for(int i = 0; i < numberArray.size(); i++) 
					{
						
						result = result + numberArray.get(i);
					}
					String finalAnswer = String.valueOf(result);
					textField.setText(finalAnswer.replaceAll(".0+$", "")); //The Line will delete all the extra .00, so for example if num is .00, it will change to whole number.
					
					//Will Push the answer and quotient in stacks, which can be viewed by users if they press a history button.
					//There is also one more stack which will be emptied it out in end.
					history.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result + " }"));
					temp.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result));
				}
				
			
				
				else if(operation == "-")
				{
					double result = numberArray.get(0); //Start from the first value of array.
					textField.setText(String.valueOf(numberArray.get(1))); //Will let the user see the second number entered by them.
					
					for(int i = 1; i < numberArray.size(); i++){ //For loop will run until the arraySize.
						result -= numberArray.get(i); //Will compute the subtraction functionality.
					}

					String finalAnswer = String.valueOf(result);
					textField.setText(finalAnswer.replaceAll(".0+$", ""));//Will replace out the extra space.
					
					history.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result + " }")); //Will store the number and it's operation into stacks.
					temp.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result));
					
					
				}
				
				
				
				else if(operation == "*")
				{
					//Will run a for loop until a operation division is composed and then set answer to the textField.
					
					double result = numberArray.get(0);
					
					textField.setText(String.valueOf(numberArray.get(1)));
					
					for(int i = 1; i < numberArray.size(); i++)
					{
						
						result *= numberArray.get(i);
					}

					String finalAnswer = String.valueOf(result);
					
					textField.setText(finalAnswer.replaceAll(".0+$", ""));
					
					history.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result + " }"));
					temp.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result));
					
					
				}
				else if(operation == "÷")
				{
					//Will run a for loop until a operation division is composed and then set answer to the textField.
					
					double result = numberArray.get(0); 
					textField.setText(String.valueOf(numberArray.get(1)));
					
					for(int i = 1; i < numberArray.size(); i++)
					{
						
						result /= numberArray.get(i);
					}

					String finalAnswer = String.valueOf(result);
					
					textField.setText(finalAnswer.replaceAll(".0+$", ""));
					
					history.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result + " }"));
					temp.push(String.valueOf(numberArray.get(numberArray.size() - 1) + " = " + result));
				}
				
				numberArray.clear();
		
			}
		});
		btnEqual.setMnemonic('=');
		btnEqual.setBounds(515, 245, 127, 46);
		frame.getContentPane().add(btnEqual);
		
		
		
		JButton btnSin = new JButton("Sin");
		btnSin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				double sinNumber = Double.parseDouble(textField.getText());//Converts the double into String.
				result = Math.sin(sinNumber); //Computes the cos function through the help of Math Class
				textField.setText(String.valueOf(result)); //So, the student will press for example 8 and then sin, the textField will print out the sin(8).
				
				history.push(" { Sin(" + sinNumber + " ) = " + String.valueOf(result) + " } ");
				temp.push(" { Sin(" + sinNumber + " ) = " + String.valueOf(result) + " } ");
				
			}
		});
		btnSin.setBounds(0, 119, 127, 46);
		frame.getContentPane().add(btnSin);
		
		JButton btnCos = new JButton("Cos");
		btnCos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				double cosNumber = Double.parseDouble(textField.getText());//Converts the double into String.
				result = Math.cos(cosNumber);//Computes the cos function through the help of Math Class
				textField.setText(String.valueOf(result)); //Sets the textField which can show it's value.
				
				history.push(" { Cos(" + cosNumber + " ) = " + String.valueOf(result) + " } ");
				temp.push(" { Cos(" + cosNumber + " ) = " + String.valueOf(result) + " } ");
				
			}
		});
		btnCos.setBounds(0, 163, 127, 46);
		frame.getContentPane().add(btnCos);
		
		JButton btnTan = new JButton("Tan");
		btnTan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				double tanNumber = Double.parseDouble(textField.getText()); //Converts the double into String.
				result = Math.tan(tanNumber);//Computes the tan function through the help of Math Class.
				textField.setText(String.valueOf(result));  //Sets the textField which can show it's value.
				
				history.push(" { Tan(" + tanNumber + " ) = " + String.valueOf(result) + " } "); //Stores that into history stack.
				temp.push(" { Tan(" + tanNumber + " ) = " + String.valueOf(result) + " } ");
				
			}
		});
		btnTan.setBounds(0, 206, 127, 46);
		frame.getContentPane().add(btnTan);
		
		JButton btnExponent = new JButton("x^2");
		btnExponent.setBackground(SystemColor.textHighlight);
		btnExponent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				double exponent = Double.parseDouble(textField.getText()); //Store the number into a variable.
				result = (exponent) * (exponent); // A variable exponent will be multiplied by it's own number.
				textField.setText(String.valueOf(result)); //Prints out the string value of variable.
				
				history.push(" { " + exponent + " * " + exponent + " = " + String.valueOf(result) + " } "); //Stores the computations in stacks.
				temp.push(" { " + exponent + "^2 = " + String.valueOf(result) + " } "); //Stores it in another stack, which is used to clearTextField.
				
			}
		});
		btnExponent.setBounds(0, 245, 127, 46);
		frame.getContentPane().add(btnExponent);
		
		
		textField_1 = new JTextField(); //iniatilization of another textField which will print out the operation sign.
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
			
			}
		});
		textField_1.setBounds(505, 6, 136, 57);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

	public  void clearTextField()
	{
		//The purpose of this class is that it can empty out the textField each time after the computations without messing up our original stack.
		
		while(!temp.isEmpty())
		{
			temp.pop();
			textField.setText("");
		}
		
		
	}
}

