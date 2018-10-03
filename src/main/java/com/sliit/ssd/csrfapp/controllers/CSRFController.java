package com.sliit.ssd.csrfapp.controllers;

import com.sliit.ssd.csrfapp.exceptions.UnauthorizedException;
import com.sliit.ssd.csrfapp.models.SessionStore;
import com.sliit.ssd.csrfapp.services.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ssudheesan on 9/6/18.
 */

@RestController
public class CSRFController {


    @Autowired
    AuthService authenticationService;

    @GetMapping(path="/token")
    public String getToken(HttpServletRequest request) throws UnauthorizedException {
        String sessionId = authenticationService.sessionIdFromCookies(request.getCookies());
        if (null != sessionId && null != SessionStore.getUserCredentialsStore().getTokenFromSession(sessionId)){
            return SessionStore.getUserCredentialsStore().getTokenFromSession(sessionId);
        }
        throw new UnauthorizedException();
    }
}
