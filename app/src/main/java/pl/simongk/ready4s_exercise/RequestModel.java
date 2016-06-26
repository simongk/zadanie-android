package pl.simongk.ready4s_exercise;

/**
 * Created by simongk on 24.06.16.
 */

public class RequestModel {
    private String longUrl;

    public RequestModel() {}

    public RequestModel(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getLongUrl() {
        return longUrl;
    }
}