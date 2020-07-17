package it.mocciavincenzo.enel.wso2.JWTAuthentication.service;


import it.mocciavincenzo.enel.wso2.JWTAuthentication.domain.Resource;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.domain.Response;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SimpleServiceResource {

    private static Map<Integer, Resource> STORE = new ConcurrentHashMap<>();

    /**
     *
     * @param res
     * @return
     */
    public Response createResource(Resource res) {
        Integer hashResource = new Integer(res.hashCode());
        STORE.put(hashResource, res);
        return new Response(hashResource);
    }

    /**
     *
     * @param hash
     * @return
     */
    public Optional<Resource> getResource(Integer hash) {
        if(STORE.containsKey(hash))
            return Optional.of(STORE.get(hash));

        return Optional.empty();
    }
}
