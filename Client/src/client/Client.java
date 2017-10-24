package client;

import java.io.IOException;
import java.net.*;
import servermessagehandler.ServerMessageHandler;

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
    private servermessagehandler.ServerMessageHandler commandHandler;
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
     * @throws IOException
     */
    public void connectToServer(InetAddress address) throws IOException {
        if (null == this.clientSocket) {
            // Create a socket
            clientSocket = new Socket(address, portNumber);
            // Create a new command handler
            this.commandHandler = new ServerMessageHandler(this,
                                                           clientSocket,
                                                           console);
            // Mark Client as "connected"
            connected = true;
            clientConnected();
        }
    }


    /**
     * Disconnect from the server. Closes the client connection to the server
     * and closes the Server Command Handler
     * @throws IOException
     */
    public void disconnectFromServer() throws IOException {
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
        
        // Mark client as "Not connected."
        connected = false;
        clientDisconnected();
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
     * @throws IOException
     */
    public void sendMessageToServer(String message) throws IOException {
        commandHandler.sendMessage(message);
    }


    /**
     * Reads messages from the server.
     */
    @Override
    public void run() {
        while (true == connected) {
            try {
                String msg = this.commandHandler.readFromServer(MSG_LENGTH);
                console.update("Response: " + msg);
            } catch (IOException e) {
                if (true == connected) {
                    serverNotResponding(e);
                }
            }
        }
    }
    
    // -------------------------------------------------------------------------
    // Callback Methods
    // -------------------------------------------------------------------------
    public void clientConnected() {
        console.update("Client connected to server on port " + this.portNumber);
    }

    public void clientDisconnected() {
        console.update("Client disconnected from server on port " + this.portNumber);
    }
    
    public void serverNotResponding(IOException e) {
        console.update("Could not read from server: " + e.toString());
        console.update("Disconnecting...");

        try {
            this.disconnectFromServer();
        } catch (IOException ex) {
            // Trouble closing the socket, nullify it as a last resort
            clientSocket = null;
        }
    }

    // -------------------------------------------------------------------------
    // Getters and Setters
    // -------------------------------------------------------------------------
    public void setPort(int newPort) { portNumber = newPort; }
    public int getPort()             { return portNumber; }
}
