import Expenses.ExpenseStore;
import Operations.HandleOperations;
import User.UserManagement;
import command.Command;
import command.CommandInvoker;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args)
            throws IOException {
        File file = new File("SplitWise/src/input.txt");
        CommandInvoker commandInvoker = new CommandInvoker();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            String[] input = line.split(" ");
            Command command = HandleOperations.createOperationObject(input[0]);
            commandInvoker.setCommand(command);
            commandInvoker.invoke(input);
        }
    }
}