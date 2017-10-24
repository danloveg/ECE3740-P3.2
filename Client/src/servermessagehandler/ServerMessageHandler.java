package servermessagehandler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 * Handle sending and receiving messages to the server.
 * @author Daniel Lovegrove
 */
public class ServerMessageHandler {
    private static final char TERMINATOR = 0xFFFD; // UTF-8 encoding of 0xFF
    private final client.Client myClient;
    private BufferedReader input;
    private OutputStream output;
    

    /**
     * Create an instance with an associated client, socket, and interface.
     * Throws IOException if it cannot get the client's input and output streams.
     * @param client The client
     * @param clientSocket The socket the client is connected on
     * @param ui The user interface
     * @throws IOException 
     */
    public ServerMessageHandler(client.Client client,
                               Socket clientSocket,
                               userinterface.StandardIO ui) throws IOException {
        this.myClient = client;
        this.input = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()));
        this.output = clientSocket.getOutputStream();
    }


    /**
     * Blocking method that reads an n-character String from the server. Waits
     * to receive n bytes until it returns the sequence in the order they were
     * received.
     * @param msgLength The length of the message
     * @return The byte from the server.
     * @throws IOException 
     */
    public String readFromServer(int msgLength) throws IOException {
        StringBuilder message = new StringBuilder(msgLength);
        boolean terminatorFound = false;
        
        while (false == terminatorFound) {
            if (input != null && input.ready()) {
                // Get a byte from the server
                char serverByte = (char) input.read();
                // Check whether the terminator byte has come in yet
                if (serverByte == TERMINATOR) {
                    terminatorFound = true;
                } else {
                    message.append(serverByte);
                }
            }
        }
        
        return message.toString();
    }


    /**
     * Write a message to the connected server.
     * @param message The byte to write to the server.
     * @throws IOException 
     */
    public void sendMessage(String message) throws IOException {
        if (output != null) {
            for (int i = 0; i < message.length(); i++) {
                output.write(message.charAt(i));
            }
            // Finally, terminate it.
            output.write(TERMINATOR);
            output.flush();
        }
    }


    /**
     * Close associated input and output streams to avoid memory leaks.
     * @throws IOException
     */
    public void close() throws IOException {
        if (input != null) {
            input.close();
            input = null;
        }
        if (output != null) {
            output.close();
            output = null;
        }
    }
}
