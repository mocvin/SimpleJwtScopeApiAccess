package it.mocciavincenzo.enel.wso2.JWTAuthentication.controller;


import it.mocciavincenzo.enel.wso2.JWTAuthentication.domain.Resource;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.domain.Response;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.exception.ResourceNotFoundException;
import it.mocciavincenzo.enel.wso2.JWTAuthentication.service.SimpleServiceResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController()
@RequestMapping("/scope")
public class SimpleScopeController {

    private final static Logger LOG = LoggerFactory.getLogger(SimpleScopeController.class.getName());
    private final SimpleServiceResource serviceResource;

    @Autowired
    public SimpleScopeController(SimpleServiceResource serviceResource) {
        this.serviceResource = serviceResource;
    }

    @PostMapping("/resource")
    @PreAuthorize("hasAuthority('urn:scope:resource:create')")
    public ResponseEntity<?> createResource(@RequestBody Resource resource) {
        Response resourceCreated = serviceResource.createResource(resource);
        return ResponseEntity.ok(resourceCreated);
    }

    @GetMapping("/resource/{hash}")
    @PreAuthorize("hasAuthority('urn:scope:resource:read')")
    public ResponseEntity<Resource> readResource(@PathVariable Integer hash) throws ResourceNotFoundException {
        Optional<Resource> optResource = serviceResource.getResource(hash);
        Resource resource = optResource.orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(resource);
    }

}
