package clienttest;

/**
 * 
 * @author Daniel Lovegrove
 */
public class ClientTest {
    
    public static void main (String[] args) {
        // Instantiate a user interface
        userinterface.StandardIO userInterface = new userinterface.StandardIO();
        
        // Instantiate a new Client
        client.Client myClient = new client.Client(5555, userInterface);
        
        // Instantiate a command handler for the user
        clientcommandhandler.ClientCommandHandler commandHandler =
                new clientcommandhandler.ClientCommandHandler(userInterface, myClient);
        
        // Set the user interface's command handler
        userInterface.setCommandHandler(commandHandler);
        
        // Start the user interface thread
        new Thread(userInterface).start();
        
        // Build the title to display to the client
        StringBuilder title = new StringBuilder(150);
        title.append("------------- Client Application -------------\n");
        title.append("The following commands are available:\n");
        title.append("* connect:\tConnect to server\n");
        title.append("* disconnect:\tDisconnect from server\n");
        title.append("* time:\t\tGet the time from the server\n");
        title.append("* quit:\t\tQuit the application\n");
        
        // Display the initial menu
        userInterface.log(title.toString());
        
    }
}
