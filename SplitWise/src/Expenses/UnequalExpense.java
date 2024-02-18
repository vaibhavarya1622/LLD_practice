package Expenses;
import User.User;

import java.util.List;


public abstract class UnequalExpense extends Expense{
    protected List<Double> owes;
    public UnequalExpense(User mainUser, Double amount, List<User> users, List<Double> owes,ExpenseStore expenseStore){
        super(mainUser,amount,users,expenseStore);
        this.owes = owes;
    }
    @Override
    public abstract void calculateShares();
}
