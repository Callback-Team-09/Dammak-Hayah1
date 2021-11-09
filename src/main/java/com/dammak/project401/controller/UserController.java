//package com.dammak.project401.controller;
//
//import com.dammak.project401.HospitalRepo;
//import com.dammak.project401.Security.UserDetailsServiceImpl;
//import com.dammak.project401.UserRepo;
//import com.dammak.project401.models.AppUser;
//import com.dammak.project401.models.Hospital;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.view.RedirectView;
//import java.security.Principal;
//import java.time.LocalDate;
//import java.util.*;
//
//@Controller
//public class UserController {
//    @Autowired
//    PasswordEncoder encoder;
//    @Autowired
//    HospitalRepo hospitalRepo;
//    @Autowired
//    UserRepo userRepo;
////    @Autowired
////    UserDetailsServiceImpl userDetailsService;
//
//
//
//
//    @GetMapping("/login")
//    public String getLoginPage(){return "login";
//    }
//
//    @GetMapping("/signup")
//    public String getSignUpPage()
//    {
//        return "signup";
//    }
//
//    @GetMapping("/hello")
//    public String test(){
//        return "hello word";
//    }
//
//    @PostMapping("/signup")
//    public RedirectView signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String blodType
//            , @RequestParam String placeName , @RequestParam String emailAdress , @RequestParam String phoneNum ){
//        AppUser appUser = new AppUser(username, encoder.encode(password),firstName,lastName,dateOfBirth,blodType,placeName,emailAdress,phoneNum,"ROLE_USER");
//        userRepo.save(appUser);
//
//        return new RedirectView("/login");
//
//    }
//    @GetMapping("/myprofile")
//    public String profile(Principal p){
//
//       AppUser appUser = userRepo.findByUsername(p.getName());
//        String auth = appUser.getAuthority();
//
//        System.out.println("ssssssssssssssssssssssssss"+appUser.getAuthority().toLowerCase(Locale.ROOT));
//
//       return "/"+auth;
//    }
//    @GetMapping("/admin")
//    public String hospitalProfil(Principal p, Model m){
//        Hospital hospital = hospitalRepo.findByUsername(p.getName());
//        m.addAttribute("hospitalname",p.getName());
//        return "donerss";
//    }
//@GetMapping("/ROLE_USER")
//public String userProfile(Principal p , Model m){
//        AppUser appUser = userRepo.findByUsername(p.getName());
//        m.addAttribute("userInformatiom",appUser);
//        return "home";
//}
//    @GetMapping("/user/{id}")
//    public String getUser(Principal p, Model model, @PathVariable Long id) {
//        model.addAttribute("usernamePrincipal", p.getName());
//        AppUser appUser = userRepo.findById(id).get();
//        model.addAttribute("userInformation", appUser);
//        return "users.html";
//    }
//    @GetMapping("/neaarhospital")
//    public String getNearHospital(Principal p,Model m){
//        AppUser appUser= userRepo.findByUsername(p.getName());
//        List<Hospital> nearHospital = hospitalRepo.findAllByPlaceName(appUser.getPlaceName());
//        if (appUser.getHospitals().isEmpty()){
//            m.addAttribute("isHaveHospital",false);
//            m.addAttribute("neareHospitals",nearHospital);
//            return "nearHospital";
//
//        }
//        Set<Hospital> removeHospital = appUser.getHospitals();
//        for (Hospital hospital : removeHospital){
//            if (nearHospital.contains(hospital)){
//                nearHospital.remove(hospital);
//            }
//        }
////        m.addAttribute("isHaveHospital",true);
//        m.addAttribute("hospitalHave",appUser.getHospitals());
//        m.addAttribute("neareHospitals",nearHospital);
//
//        return "nearHospital";
//
//    }
//    @GetMapping("/addhospital/{hospitalId}")
//    public String addHospital(@PathVariable Long hospitalId, Principal p,Model m){
//        Hospital hospital = hospitalRepo.findById(hospitalId).get();
//        AppUser appUser = userRepo.findByUsername(p.getName());
//        if (hospital.getDonors().contains(appUser)){
//            hospital.getDonors().remove(appUser);
//
//        }else {
//            hospital.getDonors().add(appUser);
//        }
//        hospitalRepo.save(hospital);
//        return "/neaarhospital";
//    }
//    @GetMapping("/getDonors/{type}")
//    public String getDoners(Principal p,Model m,@PathVariable String type){
//
//        ArrayList<AppUser> doonersList = new ArrayList<>();
//        Hospital hospital = hospitalRepo.findByUsername(p.getName());
//        if(hospital.getDonors().isEmpty()){
//            m.addAttribute("on",0);
//        }
//        if(type.equals("all")){
////            List<AppUser> doner = ;
//            m.addAttribute("doners",hospital.getDonors());
//
//           return "donerss";
//
//        }else if (type.equals("o+")){
//            for (AppUser doners : hospital.getDonors()){
//                if(doners.getBlodType().equals("o+")){
//                    doonersList.add(doners);
//                }
//
//            }
//
//        }else if (type.equals("o-")){
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType().equals("o-")) {
//                    doonersList.add(doners);
//                }
//
//            }
//            }
//            else if (type == "a+"){
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType() == "a+") {
//                    doonersList.add(doners);
//                }
//
//            }}else if (type == "a-"){
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType() == "a-") {
//                    doonersList.add(doners);
//                }
//
//            }}else if (type == "b+"){
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType() == "b+") {
//                    doonersList.add(doners);
//                }
//
//            }}else if (type == "b-"){
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType() == "b-") {
//                    doonersList.add(doners);
//                }
//
//            }}else if (type == "ab+"){
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType() == "ab+") {
//                    doonersList.add(doners);
//                }
//
//            } }else{
//            for (AppUser doners : hospital.getDonors()) {
//                if (doners.getBlodType() == "ab-") {
//                    doonersList.add(doners);
//                }
//
//            }
//        }
//            m.addAttribute("word",type);
//        m.addAttribute("doners",doonersList);
//        m.addAttribute("on",1);
////
//
//        return  "donerss";
//    }
//
//
//        @GetMapping("/")
//    public String getRoot() {
//        return "Home";
//    }
//
//
//}
