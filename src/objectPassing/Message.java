package objectPassing;

import java.io.Serializable;

public class Message implements Serializable {

    private int header;
    private String data;
    private int protocolId;
    private boolean tailor;

    public Message(int header, String data, int protocolId, boolean tailor) {
        this.header = header;
        this.data = data;
        this.protocolId = protocolId;
        this.tailor = tailor;
    }

    public int getHeader() {
        return header;
    }

    public int getProtocolId() {
        return protocolId;
    }

    public String getData() {
        return data;
    }

    public boolean isTailor() {
        return tailor;
    }
}

