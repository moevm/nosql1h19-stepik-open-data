package leti.nosql19.model;

import lombok.Data;

@Data
public class UserModule {

    private String module;

    private int attempts;

    private int comments;

    private boolean successPassing;

    public UserModule(String module, int attempts, int comments, boolean successPassing) {
        this.module = module;
        this.attempts = attempts;
        this.comments = comments;
        this.successPassing = successPassing;
    }
}
