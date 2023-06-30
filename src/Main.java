import dao.JdbcQuestionDao;
import dao.QuestionDao;
import exception.DaoException;
import model.Monster;
import model.Question;
import model.User;
import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;

import java.util.Scanner;

public class Main {

    private final QuestionDao questionDao;
    Scanner userInput = new Scanner(System.in);
    User user = new User(0, 0, 100, 0);
    Monster monster = new Monster("Test_monster", 100, 10);


    public static void main(String[] args) {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Japanese");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        Main application = new Main(dataSource);
        application.run();
    }

    public Main(DataSource dataSource) {
        questionDao = new JdbcQuestionDao(dataSource);
    }

    private void run() {
        displayBanner();
        boolean exit = false;
        while (!exit) {
            displayMenu();
            int selection = promptForInt("Please enter your choice: ");
            if (selection == 1) {
                adventure();
            }else if(selection == 4){
                System.out.println("Thank you for using Japanese quiz!");
                exit = true;
            } else {
                //invalid choice entered
                System.out.println("\n***Invalid choice. Please try again***\n");
            }

        }
    }

    private void adventure() {
        monster.setMonsterHP(100);
        user.setUserHP(100);
        System.out.println("An enemy appears!!");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (monster.getMonsterHP() > 0 && user.getUserHP() > 0) {
                System.out.println(monster.getMosterName() + " HP: " + monster.getMonsterHP());
                System.out.println("User HP: " + user.getUserHP());
                System.out.println();
                System.out.println("1) Hiragana quiz attack: 10");
                System.out.println("2) Katakana quiz attack: 15");
                System.out.println("3) Kanji quiz attack: 20");
                System.out.print("Please enter your choice: ");
                String choice = userInput.nextLine();
                scanner.nextLine();

                if (choice.equals("1")) {
                    user.changeUserAttack(1);
                    System.out.println("User attack changed to 10");

                    Question question = getRandomQuestionByGenreId(1);
                    System.out.println("Which one says " + question.getQuestion() + "?");
                    System.out.println(question.getOptions());
                    System.out.print("Please enter your choice: ");
                    String userChoice = userInput.nextLine();
                    scanner.nextLine();
                    user.answerQuestion();
                    if (question.getAnswer() == Integer.parseInt(userChoice)) {
                        giveAttack();
                    } else {
                        takeDamage(question);
                    }
                } else if (choice.equals("2")) {
                    user.changeUserAttack(2);
                    System.out.println("User attack changed to 15");

                    Question question = getRandomQuestionByGenreId(2);
                    System.out.println("Which one says " + question.getQuestion() + "?");
                    System.out.println(question.getOptions());
                    System.out.print("Please enter your choice: ");
                    String userChoice = userInput.nextLine();
                    scanner.nextLine();
                    user.answerQuestion();
                    if (question.getAnswer() == Integer.parseInt(userChoice)) {
                        giveAttack();
                    } else {
                        takeDamage(question);
                    }
                } else if (choice.equals("3")) {
                    user.changeUserAttack(3);
                    System.out.println("User attack changed to 20");

                    Question question = getRandomQuestionByGenreId(3);
                    System.out.println("Which one says " + question.getQuestion() + "?");
                    System.out.println(question.getOptions());
                    System.out.print("Please enter your choice: ");
                    String userChoice = userInput.nextLine();
                    scanner.nextLine();
                    user.answerQuestion();
                    if (question.getAnswer() == Integer.parseInt(userChoice)) {
                        giveAttack();
                    } else {
                        takeDamage(question);
                    }
                }

            } else if (monster.getMonsterHP() <= 0 && user.getUserHP() > 0) {
                System.out.println("You beat the enemy!");
                break;

            } else if (monster.getMonsterHP() > 0 && user.getUserHP() <= 0) {
                System.out.println("YOU ARE DEAD ....");
                break;

            }
        }
    }

    private void giveAttack(){
        System.out.println("That is correct!");
        //give attack
        System.out.println("You give " + user.getUserAttack() + " of damage to the " + monster.getMosterName() + "!");

        //set monster's HP
        monster.damagedMonster(user.getUserAttack());
        System.out.println(monster.getMosterName() + " HP: " + monster.getMonsterHP());
        System.out.println("User HP: " + user.getUserHP());
        System.out.println();
    }

    private void takeDamage(Question question){
        System.out.println("Wrong answer!");
        //receive damage
        System.out.println("The correct answer is: " + question.getAnswer());
        System.out.println("You take " + monster.getMonsterAttack() + " points of damage");

        //set User HP
        user.damagedUser(monster.getMonsterAttack());
        System.out.println(monster.getMosterName() + " HP: " + monster.getMonsterHP());
        System.out.println("User HP: " + user.getUserHP());
        System.out.println();
    }

    private Question getRandomQuestionByGenreId(int genreId) {
        Question question = null;
        try {
            question = questionDao.getRandomQuestionByGenreId(genreId);
        }catch (DaoException e) {
            displayError("Error occurred: " + e.getMessage());
        }
        return question;
    }


    private void displayBanner() {
        System.out.println("*****Welcome to Japanese quiz!*****");
        System.out.println("*********日本語を勉強しよう!**********");
        System.out.println();
    }

    private void displayMenu() {
        System.out.println("Main Menu");
        System.out.println("1) Go to adventure");

        System.out.println("4) exit");
        System.out.println();
    }

    private int promptForInt(String prompt) {
        return (int) promptForDouble(prompt);
    }

    private double promptForDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String response = userInput.nextLine();
            try {
                return Double.parseDouble(response);
            } catch (NumberFormatException e) {
                if (response.isBlank()) {
                    return -1; //Assumes negative numbers are never valid entries.
                } else {
                    displayError("Numbers only, please.");
                }
            }
        }
    }


    private void displayError(String message) {
        System.out.println();
        System.out.println("***" + message + "***");
    }
}











