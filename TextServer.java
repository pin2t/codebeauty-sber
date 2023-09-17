import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

import static java.lang.System.out;

public class TextServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/checkBrackets", exchange -> {
            byte[] response = new byte[]{};
            int code = 200;
            try {
                if (!"POST".equals(exchange.getRequestMethod())) {
                    throw new RuntimeException("invalid request");
                }
                JSONObject json = (JSONObject) new JSONParser().parse(new InputStreamReader(exchange.getRequestBody()));
                if (!json.containsKey("text")) {
                    throw new RuntimeException("text not found");
                }
                response = String.format("{\"isCorrect\": %b}", isCorrect((String) json.get("text"))).getBytes();
            } catch (Exception e) {
                response = e.getMessage().getBytes();
                code = 400;
            } finally {
                exchange.sendResponseHeaders(code, response.length);
                exchange.getResponseBody().write(response);
                exchange.close();
            }
        });
        server.start();
        out.println("Listening localhost:8080 ... Press Ctrl+c to stop");
    }

    static boolean isCorrect(String text) {
        if (text.isBlank()) { return false; }
        int open = 0, pos = 0;
        for(int i = 0, len = text.length() ; i < len; i++) {
            switch (text.charAt(i)) {
                case '(' -> { open++; pos = i; }
                case ')' -> {
                    if (open <= 0) return false;
                    if (text.substring(pos + 1, i).isBlank()) return false;
                    open--;
                }
            }
        }
        return open == 0;
    }
}
