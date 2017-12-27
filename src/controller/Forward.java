package controller;

public class Forward {
    private String url;
    private boolean redirect;

    public Forward(String url, boolean redirect) {
        this.url = url;
        this.redirect = redirect;
    }

    public Forward(String url) {
        this(url, true);
    }

    public String getUrl() {
        return url;
    }

    public boolean isRedirect() {
        return redirect;
    }
}
