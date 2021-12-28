import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.security.MessageDigest;

public class User {
    //First name of the user
    private String firstName;
    //Last name of the user
    private String lastName;
    //ID number of the user
    private String uuid;
    //MD5 hash of the user's pin number.
    private byte pinHash[];
    //List of accounts for this user.
    private ArrayList<Account> accounts;

    /*
    @param firstName    the user's first name
    @param lastName     the user's last name
    @param pin          the user's account pin number
    @param theBank      the Bank object that the user is a customer of
     */

    public User(String firstName,String lastName, String pin, Bank theBank){
        //set user's name
        this.firstName = firstName;
        this.lastName = lastName;

        // store the pin's MD5 hash, for security


        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            this.pinHash = md.digest(pin.getBytes());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);
        }

        //get a new, unique universal id for the user
        this.uuid = theBank.getNewUserUUID();

        //create empty list of accounts
        this.accounts = new ArrayList<Account>();

        //print log message
        System.out.printf("New user %s, %s with ID %s created.\n", lastName , firstName, this.uuid);

    }

    /*
    add an acc for the user
    @param anAcct       the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }
    public String getUUID(){
        return  this.uuid;
    }

    public boolean validatePin(String aPin){

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return MessageDigest.isEqual(md.digest(aPin.getBytes()),
                    this.pinHash);
        } catch (NoSuchAlgorithmException e) {
            System.err.println("error, caught NoSuchAlgorithmException");
            e.printStackTrace();
            System.exit(1);

        }

        return false;
    }
    public String getFirstName(){
        return  this.firstName;
    }

    public void printAccountsSummary(){

        System.out.printf("\n\n%s's accounts summary\n", this.firstName);
        for (int a = 0; a < this.accounts.size();a++){
            System.out.printf("  %d) %s\n", a+1,
                    this.accounts.get(a).getSummaryLine());
        }
        System.out.println();
    }

    public int numAccounts(){
        return this.accounts.size();
    }

    public void printAcctTransHistory(int acctIndx){
        this.accounts.get(acctIndx).printTransHistory();
    }
    public double getAcctBalance(int acctIdx){
        return  this.accounts.get(acctIdx).getBalance();
    }
    public String getAcctUUID(int acctIdx){
        return this.accounts.get(acctIdx).getUUID();
    }
    public void addAcctTransacttion(int acctIdx, double amount, String memo){
        this.accounts.get(acctIdx).addTransaction(amount, memo);
    }
}
