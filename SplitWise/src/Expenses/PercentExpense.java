package Expenses;

import User.User;
import java.util.List;


public class PercentExpense extends UnequalExpense{
    public PercentExpense(User mainUser,Double amount,List<User> users,List<Double> owes,ExpenseStore expenseStore){
        super(mainUser,amount,users,owes,expenseStore);
    }
    @Override
    public void calculateShares() {
        for(int i=0;i<participants.size();++i){
            if(participants.get(i).getUserId().equals(mainUser.getUserId())) continue;
            expenseStore.addExpense(mainUser,participants.get(i),amount*owes.get(i)/100);
        }
    }
}
