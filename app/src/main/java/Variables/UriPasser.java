package Variables;

import Interfaces.Variables.InputDatas;

public class UriPasser implements InputDatas {
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
