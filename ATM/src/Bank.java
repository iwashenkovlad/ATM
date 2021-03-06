import java.util.ArrayList;
import java.util.Random;

public class Bank {

    private  String name;

    private ArrayList<User> users;

    private ArrayList<Account> accounts;

    public Bank(String name){

        this.name = name;
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /*
    generate a universally unique id for a user.
    @return the uuid
     */
    public String getNewUserUUID(){

        //inits
        String uuid;
        Random rng = new Random();
        int len = 6;
        boolean nonUnique;

        //continue looping until we get a unique ID
        do{

            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid+= ((Integer)rng.nextInt(10)).toString();
            }

            //check fo unique
            nonUnique = false;
            for(User u : this.users){
                if(uuid.compareTo(u.getUUID()) == 0){
                nonUnique = true;
                break;
                }
            }

        } while(nonUnique);


        return uuid;

    }

    public String getNewAccountUUID(){

        String uuid;
        Random rng = new Random();
        int len = 10;
        boolean nonUnique;

        //continue looping until we get a unique ID
        do{

            //generate the number
            uuid = "";
            for (int c = 0; c < len; c++) {
                uuid+= ((Integer)rng.nextInt(10)).toString();
            }

            //check fo unique
            nonUnique = false;
            for(Account a : this.accounts){
                if(uuid.compareTo(a.getUUID()) == 0){
                    nonUnique = true;
                    break;
                }
            }

        } while(nonUnique);


        return uuid;

    }

    /*
    add an acc
    @param anAcct   the account to add
     */
    public void addAccount(Account anAcct){
        this.accounts.add(anAcct);
    }

    public User addUser(String firstName,String lastName,String pin){
        //create a new user object
        User newUser = new User(firstName,lastName,pin,this);
        this.users.add(newUser);

        //create a savings for the user
        Account newAccount = new Account("Savings",newUser,this);
        //add to holder and bank lists
        newUser.addAccount(newAccount);
        this.accounts.add(newAccount);

        return newUser;
    }

    public User userLogin(String userID,String pin){

        //search through list of users
        for (User u : this.users){
            // check user id is correct
            if (u.getUUID().compareTo(userID)==0 && u.validatePin(pin)){
                return u;
            }
        }
        //if haven't fount user or have incorrect pin
        return null;
    }
    public String  getName(){
        return this.name;
    }
}
