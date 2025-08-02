package org.opri.service;

import com.sun.security.auth.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("permissionService")
public class PermissionService {

    /*public boolean poateVizualizaProiectul(Long proiectId, Authentication authentication) {
        UserPrincipal user = (UserPrincipal) authentication.getPrincipal();
        return repository.existsByIdAndUserId(proiectId, user.getId());
    }*/
}