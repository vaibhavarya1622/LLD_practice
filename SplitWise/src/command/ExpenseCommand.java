package command;
import Expenses.EqualExpense;
import Expenses.ExactExpense;
import Expenses.Expense;
import Expenses.ExpenseStore;
import Expenses.PercentExpense;
import User.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpenseCommand implements Command{
    private final UserManagement userManagement;
    private final ExpenseStore expenseStore;
    public ExpenseCommand(UserManagement userManagement, ExpenseStore expenseStore){
        this.userManagement = userManagement;
        this.expenseStore = expenseStore;
    }

    @Override
    public void execute(String[] args) {
        String mainUser = args[1]; // The person who paid the amount
        double amount = Double.parseDouble(args[2]);
        int n = Integer.parseInt(args[3]);
        List<String> userIDs = new ArrayList<>(Arrays.asList(args).subList(4, n + 4));
        List<User> users = new ArrayList<>();
        for (String userID : userIDs) {
            users.add(userManagement.getUser(userID));
        }
        String operationType = args[n+4];
        if(operationType.equals("EQUAL")) {
            Expense expense = new EqualExpense(userManagement.getUser(mainUser),amount,users,expenseStore);
            expense.calculateShares();
        }
        else if(operationType.equals("EXACT")) {
            List<Double> amounts = new ArrayList<>();
            for(int i=0;i<n;++i){
                amounts.add(Double.parseDouble(args[i+5+n]));
            }
            Expense expense = new ExactExpense(userManagement.getUser(mainUser),amount,users,amounts,expenseStore);
            expense.calculateShares();
        }
        else if(operationType.equals("PERCENT")) {
            List<Double> percents = new ArrayList<>();
            for(int i=0;i<n;++i){
                percents.add(Double.parseDouble(args[i+5+n]));
            }
            Expense expense = new PercentExpense(userManagement.getUser(mainUser),amount,users,percents,expenseStore);
            expense.calculateShares();
        }
        else{
            throw new IllegalArgumentException("Invalid Expense command");
        }
    }
}
