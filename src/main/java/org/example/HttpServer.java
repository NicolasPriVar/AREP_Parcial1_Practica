package org.example;

import java.net.*;
import java.io.*;

public class HttpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(36000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 36000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            System.out.println("Listo para recibir ...");
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Recibí: " + inputLine);
            if (!in.ready()) {break; }
        }
        outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"

                + "<head>\n"
                + "<title>Parcial 1er corte</title>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"\n>"
                + "</head>"

                + "<body>"
                + "<h1>Formulario SET</h1>\n"
                + "<form action=\"/setkv?key={key}&value={value}\">\n"
                + "<label for=\"Key\">Key:</label><br>\n"
                + "<input type=\"text\" id=\"key\" name=\"key\" value=\"\"><br><br>\n"
                + "<label for=\"Value\">Value:</label><br>\n"
                + "<input type=\"text\" id=\"value\" name=\"value\" value=\"\"><br><br>\n"

                + "<input type=\"button\" value=\"Crear o cambiar\" onclick=\"loadSetMsg()\">\n"
                + "</form>\n"
                + "<h1>Formulario GET</h1>\n"
                + "<form action=\"/getkv?key={key}\">\n"
                + "<label for=\"Key\">Key:</label><br\n>"
                + "<input type=\"text\" id=\"key2\" name=\"key\" value=\"\"><br><br>\n"
                + "<input type=\"button\" value=\"Pedir\" onclick=\"loadGetMsg()\">\n"
                + "</form>\n"
                + "<div id=\"getrespmsg\"></div>\n"

                + "<script>\n" +
                "    function loadGetMsg() {\n" +
                "        let keyy = document.getElementById(\"key2\").value;\n" +
                "        const xhttp = new XMLHttpRequest();\n" +
                "        xhttp.onload = function () {\n" +
                "            document.getElementById(\"getrespmsg\").innerHTML =\n" +
                "                this.responseText;\n" +
                "        }\n" +
                "        xhttp.open(\"GET\", \"/getkv?key=\" + keyy);\n" +
                "        xhttp.send();\n" +
                "    }\n" +
                "\n" +
                "    function loadSetMsg() {\n" +
                "        let keyyy = document.setElementById(\"key\").value;\n" +
                "        let keyyyy = document.setElementById(\"value\").value;\n" +
                "        const xhttp = new XMLHttpRequest();\n" +
                "        xhttp.onload = function () {\n" +
                "            document.setElementById(\"setrespmsg\").innerHTML =\n" +
                "                this.responseText;\n" +
                "        }\n" +
                "        xhttp.open(\"SET\", \"/setkv?key=\" + keyyy + \"&value=\" + keyyyy);\n" +
                "        xhttp.send();\n" +
                "    }\n" +
                "</script>\n"
                + "<h4>Autor: Nicolás Prieto Vargas</h4>\n"

                + "</body>\n"

                + "</html>\n";

        out.println(outputLine);
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}