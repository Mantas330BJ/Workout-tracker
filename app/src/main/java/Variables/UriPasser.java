package Variables;

import Interfaces.InfoData;

public class UriPasser implements InfoData {
    private String uri;

    public UriPasser(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
