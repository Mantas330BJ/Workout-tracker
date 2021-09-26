package Variables;

import Interfaces.InfoData;

public class wUri implements InfoData {
    private String uri;

    public wUri(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
