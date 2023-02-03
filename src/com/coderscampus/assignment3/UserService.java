package com.coderscampus.assignment3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserService {
    private ArrayList<User> users;
   
    public UserService() {
        users = new ArrayList<>();
        loadUsersFromFile();
    }
   
    private void loadUsersFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(",");
                users.add(new User(userData[0], userData[1], userData[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
    public User validateUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
   
    public void promptForCredentials() {
        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        while (attempts < 5) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            User user = validateUser(username, password);
            if (user != null) {
                System.out.println("Welcome " + user.getName());
                break;
            } else {
                System.out.println("Invalid login, please try again.");
                attempts++;
                if (attempts == 5) {
                    System.out.println("Too many failed login attempts, you are now locked out.");
                }
            }
        }
    }
}