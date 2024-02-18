package Expenses;

import User.*;
import java.util.HashMap;
import java.util.Map;

public class ExpenseStore {
    private final Map<User,Map<User,Double>> owes = new HashMap<>();
    private final Map<User,Map<User,Double>> owedBy = new HashMap<>();
    private static ExpenseStore expenseStore;
    public Map<User, Map<User, Double>> getOwes() {
        return owes;
    }
    public Map<User, Map<User, Double>> getOwedBy() {
        return owedBy;
    }
    public static ExpenseStore getInstance(){
        if(expenseStore == null) {
            expenseStore = new ExpenseStore();
        }
        return expenseStore;
    }
    public void addExpense(User keyUser, User owedUser, Double amount) {
        if(owes.containsKey(keyUser) && owes.get(keyUser).containsKey(owedUser)) {
            Double cur = owes.get(keyUser).get(owedUser);
            if(cur>=amount) {
                owes.get(keyUser).put(owedUser,cur-amount);
                owedBy.get(owedUser).put(keyUser,cur-amount);
            }
            else{
                owes.computeIfAbsent(owedUser,(k)->new HashMap<>()).put(keyUser,amount-cur);
                owedBy.computeIfAbsent(keyUser,(k)->new HashMap<>()).put(owedUser,amount-cur);
            }
        }
        else{
            owes.computeIfAbsent(owedUser,(k)->new HashMap<>()).put(keyUser,owes.get(owedUser).getOrDefault(keyUser,0.0)+amount);
            owedBy.computeIfAbsent(keyUser,(k)->new HashMap<>()).put(owedUser,owedBy.get(keyUser).getOrDefault(owedUser,0.0)+amount);
        }
    }

}
