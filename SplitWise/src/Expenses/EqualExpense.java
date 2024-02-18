package Expenses;

import User.User;

import java.util.List;

public class EqualExpense extends Expense{

    public EqualExpense(User mainUser, Double amount, List<User> users, ExpenseStore expenseStore) {
        super(mainUser, amount, users, expenseStore);
    }

    @Override
    public void calculateShares() {
        for(User user:participants){
            if(user.getUserId().equals(mainUser.getUserId())) continue;
            expenseStore.addExpense(mainUser,user,amount/participants.size());
        }
    }
}
