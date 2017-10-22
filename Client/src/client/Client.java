package client;

import java.io.IOException;
import java.net.*;
import servercommandhandler.ServerCommandHandler;

/**
 * Client to connect to server via TCP/IP socket.
 * 
 * @author Daniel Lovegrove
 */
public class Client implements Runnable {
    // The length of the time string from the server
    private static final int MSG_LENGTH = 8;
    
    private int portNumber;
    private Socket clientSocket = null;
    private final userinterface.StandardIO console;
    private servercommandhandler.ServerCommandHandler commandHandler;
    private boolean connected = false;

    /**
     * Creates an instance with an associated port number and user interface
     * @param portNumber The port number
     * @param ui The user interface
     */
    public Client(int portNumber, userinterface.StandardIO ui) {
        this.portNumber = 5555;
        this.console = ui;
    }


    /**
     * Connect to a server on the specified port number using the local host.
     * @param address The host's address
     */
    public void connectToServer(InetAddress address) {
        if (null == this.clientSocket) {
            try {
                // Create a socket
                clientSocket = new Socket(address, portNumber);
                // Create a new command handler
                this.commandHandler = new ServerCommandHandler(this,
                                                               clientSocket,
                                                               console);
                // Mark Client as "connected"
                connected = true;
            } catch (IOException e) {
                console.log(e.toString());
                System.exit(1);
            }
        }
    }


    /**
     * Disconnect from the server. Closes the client connection to the server
     * and closes the Server Command Handler
     */
    public void disconnectFromServer() {
        try {
            // Close the socket
            if (null != this.clientSocket) {
                clientSocket.close();
                clientSocket = null;
            }

            // Close the command handler
            if (null != this.commandHandler) {
                this.commandHandler.close();
                this.commandHandler = null;
            }
            
        } catch (IOException e) {
            System.err.println("Could not close client connection, exiting program.");
            System.exit(1);
        }
        
        // Mark client as "Not connected."
        connected = false;
    }


    /**
     * Determine whether the client is connected or not.
     * @return true for connected, false if not.
     */
    public synchronized boolean isConnected() {
        return connected;
    }


    /**
     * Uses the server command handler to send a message to the server.
     * @param message The message to send.
     */
    public void sendMessageToServer(byte message) {
        try {
            commandHandler.sendMessage(message);
        } catch (IOException e) {
            console.log("Could not send message to server: " + e.toString());
            console.log("Disconnecting...");
            this.disconnectFromServer();
        }
    }


    /**
     * Reads messages from the server.
     */
    @Override
    public void run() {
        while (true == connected) {
            try {
                String msg = this.commandHandler.readFromServer(MSG_LENGTH);
                console.log("Response: " + msg);
            } catch (IOException e) {
                if (true == connected) {
                    console.log("Could not read from server: " + e.toString());
                    console.log("Disconnecting...");
                    this.disconnectFromServer();
                }
            }
        }
    }


    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------
    public void setPort(int newPort) { portNumber = newPort; }
    public int getPort()             { return portNumber; }
}
