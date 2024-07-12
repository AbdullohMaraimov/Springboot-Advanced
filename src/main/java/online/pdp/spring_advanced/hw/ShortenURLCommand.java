package online.pdp.spring_advanced.hw;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ShellComponent
public class ShortenURLCommand {

    private final Map<String, String> urlStore = new HashMap<>();

    @ShellMethod("Shorten a URL")
    public String shortenUrl(@ShellOption String url) {
        String shortUrl = UUID.randomUUID().toString().substring(0, 8);
        urlStore.put(shortUrl, url);
        return "Shortened URL: " + shortUrl;
    }

    @ShellMethod("Get original URL")
    public String getUrl(@ShellOption String shortUrl) {
        String url = urlStore.get(shortUrl);
        if (url != null) {
            return "Original URL: " + url;
        } else {
            return "URL not found!";
        }
    }

    @ShellMethod("Delete a URL")
    public String deleteUrl(@ShellOption String shortUrl) {
        if (urlStore.remove(shortUrl) != null) {
            return "URL deleted successfully!";
        } else {
            return "URL not found!";
        }
    }

}
