package content.services;

import content.entities.FootballClub;
import content.entities.SportsClub;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PremierLeagueConsoleApplication extends Application {
    //Initialize object to communicate with leagueManagerInterface class
    private static LeagueManager leagueManagerInterface = new PremierLeagueManager();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        leagueManagerInterface.loadDetails();
        Scanner scan = new Scanner(System.in);

        while (true) {
            System.out.println("\n\n|______________________________PREMIER LEAGUE!!______________________________|\n");
            System.out.println("==> Note: Please remember to store data before quit the program!!\n      Otherwise data'll be lost!!\n\n|____Menue____|\n");
            System.out.println("==> Enter \"A\" to add a football club to the PREMIER LEAGUE");
            System.out.println("==> Enter \"R\" to relegate(delete) a football club from the PREMIER LEAGUE");
            System.out.println("==> Enter \"D\" to display the various statistics for a selected football club");
            System.out.println("==> Enter \"P\" to display the PREMIER LEAGUE Table");
            System.out.println("==> Enter \"M\" to add played match for selected football clubs");
            System.out.println("==> Enter \"I\" to view Graphical User Interface(JavaFx)");
            System.out.println("==> Enter \"G\" to view Graphical User Interface(Angular)");
            System.out.println("==> Enter \"S\" to store Data");
            System.out.println("==> Enter \"Q\" to quit");
            System.out.print("\n=> PLEASE ENTER THE GIVEN COMMAND : ");
            String command = scan.next();

            switch (command) {			//Switch case for options
                case "A":
                case "a":
                    createFootballClub();       //calling to the method
                    break;
                case "R":
                case "r":
                    relegateFootballClub();     //calling to the method
                    break;
                case "D":
                case "d":
                    displaySelectedFootballClub();      //calling to the method
                    break;
                case "P":
                case "p":
                    displayPremierLeagueTable();        //calling to the method
                    break;
                case "M":
                case "m":
                    addPlayedMatch();       //calling to the method
                    break;
                case "I":
                case "i":
                    leagueManagerInterface.graphicalUserInterface();        //calling to the method
                    break;
                case "G":
                case "g":
                    String  angularUrl ="http://localhost:4200/";      //calling to the angular GUI
                    Runtime runProg = Runtime.getRuntime();
                    runProg.exec("rundll32 url.dll,FileProtocolHandler " + angularUrl);
                    break;
                case "S":
                case "s":
                    leagueManagerInterface.saveDetails();       //calling to the method
                    break;
                case "Q":
                case "q":
                    System.out.println("\n=> Thank you!!");
                    System.exit(0);
                default:
                    System.out.println("\n ***Please enter the given command!!***");
            }
        }
    }

    /*
     *Use to create football club in premier league
     *All the inputs insert to object and send to interface
     */
    private static void createFootballClub(){
        while (true){
            Scanner scan = new Scanner(System.in);
            try {
                System.out.println("\n|____Add a football club to the PREMIER LEAGUE____|\n");
                System.out.println("=> Please enter the name of the football club : ");
                String footballClubName = scan.nextLine().toUpperCase();	//turn the string value to uppercase

                System.out.println("=> Please enter the location of the football club : ");
                String footballClubLocation = scan.nextLine().toUpperCase();	//turn the string value to uppercase

                System.out.println("=> Please enter the chairman name of the football club : ");
                String footballClubChairmanName = scan.nextLine().toUpperCase();	//turn the string value to uppercase

                if ((footballClubName.equals("")) || (footballClubLocation.equals(""))|| (footballClubChairmanName.equals(""))) {
                    System.out.println("\n ***Error : football club name, club location or chairman name was empty!!***\n");
                }else{
                    System.out.println("=> Please enter the founded_year of the football club (only year ex: xxxx) : ");
                    int footballClubFoundedYearYear = scan.nextInt();

                    System.out.println("=> Please enter the founded_year of the football club (only month ex: xx) : ");
                    int footballClubFoundedYearMonth = scan.nextInt();

                    System.out.println("=> Please enter the founded_year of the football club (only day ex: xx) : ");
                    int footballClubFoundedYearDay = scan.nextInt();

                    String stringFormattedDate = footballClubFoundedYearDay+"/"+footballClubFoundedYearMonth+"/"+footballClubFoundedYearYear;

                    if (leagueManagerInterface.validatingDate(stringFormattedDate)){        //validate input date
                        SportsClub sportsClub = new FootballClub(footballClubName,footballClubLocation,footballClubChairmanName, stringFormattedDate);
                        leagueManagerInterface.addFootballClub(sportsClub);
                        break;
                    }else
                        System.out.println("\n ***Please enter the valid date!!***\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n ***Please enter the date using valid format!!***\n    ERROR : " + e +"\n");
            }
        }
    }

    /*
     *Use to delete football club from premier league
     *Input send to interface
     */
    private static void relegateFootballClub(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n|____Relegate(delete) a football club from the PREMIER LEAGUE____|\n");
        System.out.println("=> Please enter the name of the football club : ");
        String footballClubName = scan.nextLine().toUpperCase();	//turn the string value to uppercase

        leagueManagerInterface.relegateFootballClub(footballClubName);
    }

    /*
     *Use to display football club from premier league
     *Input send to interface
     */
    private static void displaySelectedFootballClub(){
        Scanner scan = new Scanner(System.in);
        System.out.println("\n|____Display the various statistics for a selected football club____|\n");
        System.out.println("=> Please enter the name of the football club : ");
        String footballClubName = scan.nextLine().toUpperCase();	//turn the string value to uppercase

        leagueManagerInterface.displaySelectedFootballClubStatics(footballClubName);
    }

    //Use to display premier league table
    private static void displayPremierLeagueTable(){
        System.out.println("\n                                                                                                           |____PREMIER LEAGUE TABLE!!____|\n");

        leagueManagerInterface.displayPremierLeagueTable();
    }

    /*
     *Use to add played football match to premier league
     *Input send to interface
     */
    private static void addPlayedMatch() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n|____Add played Match for selected football clubs____|\n");
        System.out.println("=> Please enter the name of the first football club : ");
        String firstFootballClubName = scan.nextLine().toUpperCase();		//turn the string value to uppercase
        System.out.println("=> Please enter the name of the second football club : ");
        String secondFootballClubName = scan.nextLine().toUpperCase();		//turn the string value to uppercase

        leagueManagerInterface.addPlayedMatch(firstFootballClubName,secondFootballClubName);
    }
}
