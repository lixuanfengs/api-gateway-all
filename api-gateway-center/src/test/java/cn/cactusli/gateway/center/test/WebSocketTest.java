package cn.cactusli.gateway.center.test;

import org.apache.catalina.Server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.server.ServerEndpointConfig;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Package: cn.cactusli.gateway.center.test
 * Description:
 *  模拟服务器
 *
 * @Author 仙人球⁶ᴳ | 微信：Cactusesli
 * @Date 2023/9/28 10:05
 * @Github https://github.com/lixuanfengs
 */
public class WebSocketTest {


    public static void main(String[] args) throws IOException {
        String response1 = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><head><title>Simple HTTP Server</title></head>" +
                "<body><h1>api102 success!</h1></body></html>";

        String response2 = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><head><title>Simple HTTP Server</title></head>" +
                "<body><h1>api102 success!</h1></body></html>";

        String response3 = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n" +
                "\r\n" +
                "<html><head><title>Simple HTTP Server</title></head>" +
                "<body><h1>api103 success!</h1></body></html>";


        ServerSocket serverSocket = new ServerSocket(9001);
        Thread thread = new Thread(new HttpRequestHandler(serverSocket, response1));
        thread.start();

        ServerSocket serverSocket2 = new ServerSocket(9002);
        Thread thread2 = new Thread(new HttpRequestHandler(serverSocket2, response2));
        thread2.start();

        ServerSocket serverSocket3 = new ServerSocket(9003);
        Thread thread3 = new Thread(new HttpRequestHandler(serverSocket3, response3));
        thread3.start();
    }


}
 class HttpRequestHandler implements Runnable {
    private final ServerSocket serverSocket;
    private final String response;

    public HttpRequestHandler(ServerSocket serverSocket,String response) {
        this.serverSocket = serverSocket;
        this.response = response;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Accepted connection from " + clientSocket.getInetAddress());

                // 处理HTTP请求
                handleHttpRequest(clientSocket, response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void handleHttpRequest(Socket socket, String response) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        // 读取HTTP请求
        String requestLine = in.readLine();
        System.out.println("Received request: " + requestLine);

        out.write(response);
        out.flush();

        // 关闭连接
        socket.close();
    }
}