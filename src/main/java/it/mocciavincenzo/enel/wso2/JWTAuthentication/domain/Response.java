package it.mocciavincenzo.enel.wso2.JWTAuthentication.domain;

public class Response {

    public Integer hash;
    public Resource resource;

    public Response(Integer hash) {
        this.hash = hash;
    }
}
