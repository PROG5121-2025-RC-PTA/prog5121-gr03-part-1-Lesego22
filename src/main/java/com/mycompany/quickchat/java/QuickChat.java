/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.quickchat.java;

/**
 *
 * @author leseg
 */
// Import necessary libraries
import javax.swing.JOptionPane;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuickChat {
    
    // Main method to run the program
    public static void main(String[] args) {
        Login loginSystem = new Login();
        
        // Get registration information from user
        String username = JOptionPane.showInputDialog("Create a username (must contain an underscore and be 5 characters or less):");
        String password = JOptionPane.showInputDialog("Create a password (must be at least 8 characters, include a capital letter, a number, and a special character):");
        String firstName = JOptionPane.showInputDialog("Enter your first name:");
        String lastName = JOptionPane.showInputDialog("Enter your last name:");
        String cellNumber = JOptionPane.showInputDialog("Enter your cell phone number (with international code, e.g., +27...):");
        
        // Register the user
        loginSystem.setUsername(username);
        loginSystem.setPassword(password);
        loginSystem.setFirstName(firstName);
        loginSystem.setLastName(lastName);
        loginSystem.setCellNumber(cellNumber);
        
        String registrationStatus = loginSystem.registerUser();
        JOptionPane.showMessageDialog(null, registrationStatus);
        
        // If registration is successful, proceed to login
        if (registrationStatus.contains("successfully")) {
            // Get login information
            String loginUsername = JOptionPane.showInputDialog("Enter your username to login:");
            String loginPassword = JOptionPane.showInputDialog("Enter your password:");
            
            // Login the user
            loginSystem.setLoginUsername(loginUsername);
            loginSystem.setLoginPassword(loginPassword);
            
            boolean loginSuccess = loginSystem.loginUser();
            String loginStatus = loginSystem.returnLoginStatus();
            
            JOptionPane.showMessageDialog(null, loginStatus);
        }
    }
}

// Login class to handle authentication
class Login {
    // Class variables
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String cellNumber;
    private String loginUsername;
    private String loginPassword;
    private boolean loginSuccessful;
    
    // Setters
    public void setUsername(String username) {
        this.username = username;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setCellNumber(String cellNumber) {
        this.cellNumber = cellNumber;
    }
    
    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
    
    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }
    
    // Method to check if the username meets requirements
    public boolean checkUserName() {
        // Check if username contains an underscore and is 5 characters or less
        return username.contains("_") && username.length() <= 5;
    }
    
    // Method to check if the password meets complexity requirements
    public boolean checkPasswordComplexity() {
        // Check if password is at least 8 characters
        boolean isLongEnough = password.length() >= 8;
        
        // Check if password contains a capital letter
        boolean hasCapital = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                hasCapital = true;
                break;
            }
        }
        
        // Check if password contains a number
        boolean hasNumber = false;
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                hasNumber = true;
                break;
            }
        }
        
        // Check if password contains a special character
        boolean hasSpecial = false;
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = pattern.matcher(password);
        hasSpecial = matcher.find();
        
        // Return true if all conditions are met
        return isLongEnough && hasCapital && hasNumber && hasSpecial;
    }
    
    // Method to check if the cell phone number is correctly formatted
    // Using regular expression to check for international code format
    public boolean checkCellPhoneNumber() {
        // This regex pattern checks for a plus sign, followed by digits, and limits to 10 digits after any country code
        // I used ChatGPT to help create this regex pattern (Claude 3.7 Sonnet, April 10, 2025)
        String regex = "^\\+\\d+\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(cellNumber);
        
        return matcher.matches();
    }
    
    // Method to register a user
    public String registerUser() {
        // Check username
        if (!checkUserName()) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        // Check password
        if (!checkPasswordComplexity()) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        // Check cell phone number
        if (!checkCellPhoneNumber()) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        
        // If all checks pass, return success message
        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }
    
    // Method to login a user
    public boolean loginUser() {
        // Check if the entered username and password match the registered ones
        if (loginUsername.equals(username) && loginPassword.equals(password)) {
            loginSuccessful = true;
        } else {
            loginSuccessful = false;
        }
        
        return loginSuccessful;
    }
    
    // Method to return login status message
    public String returnLoginStatus() {
        if (loginSuccessful) {
            return "Welcome " + firstName + ", " + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}
