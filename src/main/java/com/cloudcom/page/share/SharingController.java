package com.cloudcom.page.share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("share")
public class SharingController {

    private final SharingService sharingService;

    public SharingController(SharingService sharingService) {
        this.sharingService = sharingService;
    }

    @GetMapping
    public String share() {
        return "share";
    }

    @PostMapping
    public void sendTokens(Principal sender,
                           @RequestParam Integer tokensToSend,
                           @RequestParam String receiverName) {
        sharingService.sendTokens(
                sender.getName(),
                tokensToSend,
                receiverName
        );
    }
}