package clientmessagehandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.IOException;

/**
 * Handles the executing of the user's commands.
 * 
 * @author Daniel Lovegrove
 */
public class ClientMessageHandler {
    
    private final userinterface.StandardIO console;
    private client.Client myClient;
    
    
    /**
     * Creates an instance with an associated user interface and client.
     * @param console The console user interface
     * @param client The client
     */
    public ClientMessageHandler(userinterface.StandardIO console, client.Client client) {
        this.console = console;
        this.myClient = client;
    }
    
    
    /**
     * Parses the user's command and executes the appropriate task.
     * @param userCommand The string the user entered
     */
    public void execute(String userCommand) {
        // First, trim any whitespace
        String cmd = userCommand.trim();

        switch (cmd) {
            case "connect":
                if (false == myClient.isConnected()) {
                    try {
                        myClient.connectToServer(InetAddress.getLocalHost());
                        Thread clientThread = new Thread(myClient);
                        clientThread.start();
                    } catch (UnknownHostException e) {
                        console.update("Could not determine host.");
                    } catch (IOException e) {
                        console.update("Could not connect to server.");
                    }
                } else {
                    console.update("Already connected!");
                }
                break;
            case "disconnect":
                if (true == myClient.isConnected()) {
                    try {
                        myClient.sendMessageToServer("d");
                        myClient.disconnectFromServer();
                    } catch (IOException e) {
                        console.update("Error: " + e.toString());
                    }
                } else {
                    console.update("No connected server.");
                }
                break;
            case "quit":
                console.update("Quitting...");
                if (true == myClient.isConnected()) {
                    try {
                        myClient.sendMessageToServer("q");
                        myClient.disconnectFromServer();
                    } catch (IOException e) {
                        console.update("Error: " + e.toString());
                    }
                }
                System.exit(0);
                break;
            case "time":
                if (true == myClient.isConnected()) {
                    try {
                        myClient.sendMessageToServer("t");
                    } catch (IOException e) {
                        console.update("Could not send message to server.");
                    }
                } else {
                    console.update("No connected server.");
                }
                break;
            case "":
                break;
            default:
                console.update("\"" + cmd + "\" is not recognized.");
                break;
        }
    }
}
