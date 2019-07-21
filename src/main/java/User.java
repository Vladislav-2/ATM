import Exceptions.CardAlreadyBlockedException;
import Exceptions.NotEnoughMoneyException;

public class User {

    private String numberOfCard;
    private int PIN;
    private double balance; //in dollars
    private boolean blocked;

    User(String numberOfCard, int PIN){
        this.numberOfCard = numberOfCard;
        this.PIN = PIN;
    }

    User(String numberOfCard, int PIN, double balance){
        this.numberOfCard = numberOfCard;
        this.PIN = PIN;
        this.balance = balance;
    }

    User(String numberOfCard, int PIN, double balance, boolean blocked){
        this.numberOfCard = numberOfCard;
        this.PIN = PIN;
        this.balance = balance;
        this.blocked = blocked;
    }

    public String getNumberOfCard(){ return numberOfCard; }

    public double getbalance(){
        return this.balance;
    }

    public boolean isBlocked(){
        return blocked;
    }

    public int getPIN(){ return PIN;}

    public boolean checkPIN(int PIN){
        if (PIN == this.PIN){
            return true;
        } else {
            return false;
        }
    }

    public void withdraw(double amount) throws NotEnoughMoneyException {
        if (balance >= amount) {
            balance = balance - amount;
        } else {
            throw new NotEnoughMoneyException();
        }
    }

    public void deposit(double amount){
        balance = balance + amount;
    }

    public void setBlocked() throws CardAlreadyBlockedException {
        if (blocked != true){
            blocked = true;
        } else {
            throw new CardAlreadyBlockedException();
        }
    }
}
