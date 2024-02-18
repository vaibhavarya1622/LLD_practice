package Operations;

import Expenses.ExpenseStore;
import User.UserManagement;
import command.AddUserCommand;
import command.Command;
import command.ExpenseCommand;
import command.ShowCommand;

public class HandleOperations {
    private static final UserManagement userManagement = UserManagement.getInstance();
    private static final ExpenseStore expenseStore = ExpenseStore.getInstance();

    public static Command createOperationObject(String input){
        return switch (input) {
            case "ADD" -> new AddUserCommand(userManagement);
            case "EXPENSE" -> new ExpenseCommand(userManagement,expenseStore);
            case "SHOW" -> new ShowCommand(userManagement,expenseStore);
            default -> throw new IllegalStateException("Unexpected value: " + input);
        };
    }
}
