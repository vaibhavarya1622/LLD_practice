package command;

import Expenses.ExpenseStore;
import User.*;

import java.util.Map;

public class ShowCommand implements Command{
    private final UserManagement userManagement;
    private final ExpenseStore expenseStore;

    public ShowCommand(UserManagement userManagement,ExpenseStore expenseStore) {
        this.userManagement = userManagement;
        this.expenseStore = expenseStore;
    }
    @Override
    public void execute(String[] args) {
        if(args.length == 1){
            handleShowAll();
        }
        else{
            handleShowForUser(args[1]);
        }
    }
    private boolean checkNoExpense(Map<String,User> userMap) {
        if(userMap.isEmpty()) return true;
        return expenseStore.getOwedBy().isEmpty() && expenseStore.getOwes().isEmpty();
    }
    private void handleShowAll() {
        Map<String, User> userMap = userManagement.getUserMap();
        if(checkNoExpense(userMap)) {
            System.out.println("No balances");
            return;
        }
        for(Map.Entry<User, Map<User, Double>> mp:expenseStore.getOwes().entrySet()){
            for(Map.Entry<User,Double> owe:mp.getValue().entrySet()){
                System.out.printf("%s owes %s: %.2f%n",mp.getKey().getName(),owe.getKey().getName(),owe.getValue());
            }
        }
    }
    private void handleShowForUser(String userId){
        User user = userManagement.getUserMap().get(userId);
        if(!expenseStore.getOwes().containsKey(user) && !expenseStore.getOwedBy().containsKey(user)){
            System.out.println("No Balances");
            return;
        }
        if(expenseStore.getOwes().containsKey(user)) {
            for(Map.Entry<User,Double> owe:expenseStore.getOwes().get(user).entrySet()){
                System.out.printf("%s owes %s: %.2f%n",user.getName(),owe.getKey().getName(),owe.getValue());
            }
        }
        if(expenseStore.getOwedBy().containsKey(user)){
            for(Map.Entry<User,Double> owed:expenseStore.getOwedBy().get(user).entrySet()) {
                System.out.printf("%s owes %s: %.2f%n",owed.getKey().getName(),user.getName(),owed.getValue());
            }
        }
    }
}
