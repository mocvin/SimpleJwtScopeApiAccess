package it.mocciavincenzo.enel.wso2.JWTAuthentication.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Resource {

    public String name;
    public String value;

    public Resource(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
