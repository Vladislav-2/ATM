import Exceptions.*;
import java.util.Scanner;

public class ATM {

    public static void main(String[] args) throws WrongNumberException, CardAlreadyBlockedException, NotEnoughMoneyException, LimitReachedException, VaultEmptyException {
        Tools.load();
        mainMenuOptions();
    }

        //main menu option method
        public static void mainMenuOptions() throws WrongNumberException, CardAlreadyBlockedException, NotEnoughMoneyException, LimitReachedException, VaultEmptyException {
            //enter id
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a number of your card: ");
            String number = input.nextLine();
            int index = -1;
            for(int i = 0; i < DataBase.users.size(); i++) {
                if(DataBase.users.get(i).getNumberOfCard().equals(number)) {
                    if (DataBase.users.get(i).isBlocked()){
                        throw new CardAlreadyBlockedException();
                    }
                    index = i;
                    for (int j = 3; j > 0; j--) {
                        System.out.println("Enter PIN: ");
                        int PIN = input.nextInt();
                        if (DataBase.users.get(i).checkPIN(PIN)) {
                            break;
                        } else if(j == 1){
                            DataBase.users.get(i).setBlocked();
                            System.out.println("Card was blocked");
                            Tools.clean();
                            Tools.save();
                            throw new CardAlreadyBlockedException();
                        } else {
                            System.out.println("Uncorrect PIN, please try again");
                        }
                    }
                    i = DataBase.users.size();
                } else if (i == DataBase.users.size() -1) {
                    System.out.println("Please enter a correct number");
                    throw new WrongNumberException();
                }
            }
            mainMenu(index);
            System.out.println("\n1:back to main menu"+"\n2:exit");
            int enterChoice = input.nextInt();
            if (enterChoice == 1){
                mainMenu(index);
            } else if(enterChoice == 2){
                mainMenuOptions();
            }
        }
        //main menu method
        public static void mainMenu(int index)  throws WrongNumberException, CardAlreadyBlockedException, NotEnoughMoneyException, LimitReachedException, VaultEmptyException{
            System.out.println("\nMain menu"+"\n1:check balance"+"\n2:withdraw"
                    +"\n3:deposit"+"\n4:exit"+"\nEnter a choice");
            //user enters choice in main menu
            Scanner inPut = new Scanner(System.in);
            int enterChoice = inPut.nextInt();
            if(enterChoice == 1){
                System.out.println("The balance is " + DataBase.users.get(index).getbalance());
            }
            else if(enterChoice == 2){
                System.out.println("Enter an amount to withdraw ");
                double amount = inPut.nextDouble();
                //withdraw method
                if (DataBase.money >= amount) {
                    DataBase.users.get(index).withdraw(amount);
                    DataBase.money -= amount;
                    System.out.println(amount + "$ " + "was withdrawn");
                    Tools.clean();
                    Tools.save();
                } else {
                    throw new VaultEmptyException();
                }
            }
            else if(enterChoice == 3){
                System.out.println("Enter an amount to deposit ");
                double amount = inPut.nextDouble();
                //deposit method
                if (amount > 1000000){
                    throw new LimitReachedException();
                } else {
                    DataBase.users.get(index).deposit(amount);
                    DataBase.money += amount;
                    System.out.println(amount + "$ " + "was deposited");
                    Tools.clean();
                    Tools.save();
                }
            }
            else if(enterChoice == 4){
                mainMenuOptions();
            }
            System.out.println("\n1:back to main menu"+"\n2:exit");
            enterChoice = inPut.nextInt();
            if (enterChoice == 1){
                mainMenu(index);
            } else if(enterChoice == 2){
                mainMenuOptions();
            }
        }
    }
