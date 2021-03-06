package client;

import java.io.IOException;
import java.net.Socket;
import java.net.InetAddress;
import servermessagehandler.ServerMessageHandler;
import java.util.concurrent.TimeoutException;

/**
 * Client to connect to server via TCP/IP socket.
 * 
 * @author Daniel Lovegrove
 */
public class Client implements Runnable {
    private static final int TIMEOUT_MILLIS = 2500;
    
    private int portNumber;
    private Socket clientSocket = null;
    private final userinterface.Userinterface UI;
    private servermessagehandler.ServerMessageHandler commandHandler;
    private boolean connected = false;
    private boolean disconnectWaiting = false;

    /**
     * Creates an instance with an associated port number and user interface
     * @param portNumber The port number
     * @param ui The user interface
     */
    public Client(int portNumber, userinterface.Userinterface ui) {
        this.portNumber = 5555;
        this.UI = ui;
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
            this.commandHandler = new ServerMessageHandler(clientSocket);
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
        // Wait for the disconnection acnowledgement.
        try {
            waitForDisconnectAck();
        } catch (TimeoutException e) {
            UI.update("Server connection timed out. Closing connection immediately.");
        }

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
     * Uses the server command handler to send a message to the server.
     * @param message The message to send.
     * @throws IOException
     */
    public void sendMessageToServer(String message) throws IOException {
        commandHandler.sendStringToServer(message);
    }


    /**
     * Reads messages from the server.
     */
    @Override
    public void run() {
        while (true == connected) {
            try {
                String msg = this.commandHandler.readStringFromServer();
                UI.update(msg);

                // If we were waiting for the message to finish sending to
                // disconnect, notify anything waiting that the server has
                // acknowledged us.
                if (true == getDisconnectWaiting()) {
                    setDisconnectWaiting(false);
                }
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

    /**
     * Notify the user that they are connected.
     */
    public void clientConnected() {
        UI.update("Client connected to server on port " + this.portNumber);
    }


    /**
     * Notify the user that they have disconnected.
     */
    public void clientDisconnected() {
        UI.update("Client disconnected from server on port " + this.portNumber);
    }


    /**
     * Wait for the server to stop sending data. Sets disconnectWaiting to true,
     * and waits until the Thread reading from the server puts it to false. If
     * it takes too long, a TimeoutException is thrown. Since we are blocking
     * here, this is necessary.
     * @throws TimeoutException
     */
    public void waitForDisconnectAck() throws TimeoutException {
        // We are now waiting for a disconnection
        setDisconnectWaiting(true);
        long startTime = System.currentTimeMillis();

        while (true == getDisconnectWaiting()) {
            if (System.currentTimeMillis() - startTime > TIMEOUT_MILLIS) {
                setDisconnectWaiting(false);
                throw new TimeoutException();
            }
        }
    }

    
    /**
     * Act on the server not responding.
     * @param e The exception thrown from trying to communicate with the server
     */
    public void serverNotResponding(IOException e) {
        UI.update("Could not read from server: " + e.toString());
        UI.update("Disconnecting...");

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

    public synchronized boolean isConnected() { return connected; }

    public synchronized void setDisconnectWaiting(boolean waiting) { disconnectWaiting = waiting; }
    public synchronized boolean getDisconnectWaiting() { return disconnectWaiting; }
}
