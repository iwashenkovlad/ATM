import java.util.ArrayList;

public class Account {
    //Name of the account
    private String name;
//    //The current balance of the account
//    private double balance;
    //Account ID number
    private String uuid;
    //User object that owns this account
    private User holder;
    //List of transactions for this account
    private ArrayList<Transaction> transactions;

    /*
    Create a new Acc
    @param name     the name of the acc
    @param holder   the user object that holds this account
    @param the Bank the bank that issues the account
     */

    public Account(String name, User holder, Bank theBank){
        //set the acc name and holder
        this.name = name;
        this.holder = holder;

        //get new acc uuid
        this.uuid = theBank.getNewAccountUUID();

        //init trans
        this.transactions = new ArrayList<Transaction>();


    }
        public String getUUID(){
        return this.uuid;
        }

        public String getSummaryLine() {
            //get the acc balance
            double balance = this.getBalance();

            //format the summary line, depending on the whether the balance is negative
            if (balance>=0){
                return String.format("%s : $%.02f : %s", this.uuid, balance,
                        this.name);
            } else{
                return String.format("%s : $(%.02f) : %s", this.uuid, balance,
                        this.name);
            }
        }

        public double getBalance() {

        double balance = 0;
        for (Transaction t : this.transactions){
            balance += t.getAmount();
        }
        return balance;
        }

        public void printTransHistory(){

        System.out.printf("\nTransaction history for account %s\n", this.uuid);
        for(int t = this.transactions.size()-1;t>=0; t--){
            System.out.println(this.transactions.get(t).getSummaryLine());
        }
        System.out.println();
        }

        public void addTransaction(double amount, String memo){

        Transaction newTrans = new Transaction(amount,memo,this);
        this.transactions.add(newTrans);
        }
}
