package com.step.communication.factory;

import com.step.communication.channel.MessageChannel;
import com.step.communication.channel.MessageChannelListener;
import com.step.communication.server.MessageServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class CommunicationFactory {

    public MessageServer createMessageServer() {
        return new MessageServer(this);
    }
    public MessageChannel connectTo(String serverAddress, MessageChannelListener observer){
        return new MessageChannel(connectTo(serverAddress, 9090));
    }

    public ServerSocket createServerSocket() {
        try {

            ServerSocket serverSocket = new ServerSocket(9090);
            serverSocket.setSoTimeout(200);
            return serverSocket;
        } catch (IOException e) {
            throw new RuntimeException("Could not create ",e);
        }
    }

    public MessageChannel acceptFrom(ServerSocket serverSocket){
        try {
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(100);
            return new MessageChannel(socket);
        }
        catch (SocketTimeoutException ste){
            return null;
        }
        catch (IOException e) {
            throw new RuntimeException("could not accept connection",e);
        }
    }
    private Socket connectTo(String serverAddress, int port) {
        try {
            Socket socket = new Socket(serverAddress, port);
            socket.setSoTimeout(100);
            return socket;
        } catch (IOException e) {
            throw new RuntimeException("could not connect to "+serverAddress+" at "+port,e);
        }
    }
}
