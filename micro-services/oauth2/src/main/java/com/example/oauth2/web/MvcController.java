//package com.example.oauth2.web;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
//import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
//import org.springframework.security.oauth2.core.user.OAuth2User;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
///**
// * @Description
// * @Author eshuo
// * @Date 2022-11-09 09:58
// * @Version V1.0
// */
//@Controller
//public class MvcController {
//
//    @GetMapping("/")
//    public String index(Model model, @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
//                        @AuthenticationPrincipal OAuth2User oauth2User) {
//        model.addAttribute("userName", oauth2User.getName());
//        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
//        model.addAttribute("userAttributes", oauth2User.getAttributes());
//        return "index";
//    }
//}
