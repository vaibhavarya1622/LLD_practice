package command;
public class CommandInvoker {
    Command command;
    public void setCommand(Command command) {
        this.command = command;
    }
    public void invoke(String[] args){
        command.execute(args);
    }
}
