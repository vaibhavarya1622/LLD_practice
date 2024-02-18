package Expenses;
import User.User;

import java.util.List;

public abstract class Expense {
    protected User mainUser;
    protected Double amount;
    List<User> participants;
    protected ExpenseStore expenseStore;
    public abstract void calculateShares();

    public Expense(User mainUser,Double amount, List<User> users,ExpenseStore expenseStore) {
        this.mainUser = mainUser;
        this.amount = amount;
        this.participants = users;
        this.expenseStore = expenseStore;
    }
}
