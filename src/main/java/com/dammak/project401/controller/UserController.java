package com.dammak.project401.controller;

import com.dammak.project401.HospitalRepo;
import com.dammak.project401.UserRepo;
import com.dammak.project401.models.AppUser;
import com.dammak.project401.models.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.security.Principal;
import java.time.LocalDate;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    HospitalRepo hospitalRepo;
    @Autowired
    UserRepo userRepo;





    @GetMapping("/login")
    public String getLoginPage(){return "login";
    }

    @GetMapping("/signup")
    public String getSignUpPage()
    {
        return "signup";
    }

    @GetMapping("/hello")
    public String test(){
        return "hello word";
    }

    @PostMapping("/signup")
    public RedirectView signUpUser(@RequestParam String username, @RequestParam String password, @RequestParam String firstName, @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String blodType
            , @RequestParam String placeName , @RequestParam String emailAdress , @RequestParam String phoneNum ){


        AppUser appUser = new AppUser(username, encoder.encode(password),firstName,lastName,dateOfBirth,blodType,placeName,emailAdress,phoneNum,"ROLE_USER","yse",0);
        userRepo.save(appUser);

        return new RedirectView("/login");

    }
    @GetMapping("/myprofile")
    public String profile(Principal p){

       AppUser appUser = userRepo.findByUsername(p.getName());
        String auth = appUser.getAuthority();

        System.out.println("ssssssssssssssssssssssssss"+appUser.getAuthority().toLowerCase(Locale.ROOT));

       return "/"+auth;
    }
    @GetMapping("/admin")
    public String hospitalProfil(Principal p, Model m){
        Hospital hospital = hospitalRepo.findByUsername(p.getName());
        m.addAttribute("hospitalname",p.getName());
        return "donerss";
    }
@GetMapping("/ROLE_USER")
public String userProfile(Principal p , Model m){
        AppUser appUser = userRepo.findByUsername(p.getName());
        m.addAttribute("userInformatiom",appUser);
        return "userProfile";
}
    @GetMapping("/user/{id}")
    public String getUser(Principal p, Model model, @PathVariable Long id) {
        model.addAttribute("usernamePrincipal", p.getName());
        AppUser appUser = userRepo.findById(id).get();
        model.addAttribute("userInformation", appUser);
        return "users.html";
    }
    @GetMapping("/neaarhospital")
    public String getNearHospital(Principal p,Model m){
        AppUser appUser= userRepo.findByUsername(p.getName());
        List<Hospital> nearHospital = hospitalRepo.findAllByPlaceName(appUser.getPlaceName());
        if (appUser.getHospitals().isEmpty()){
            m.addAttribute("isHaveHospital",false);
            m.addAttribute("neareHospitals",nearHospital);
            return "nearHospital";

        }
        Set<Hospital> removeHospital = appUser.getHospitals();
        for (Hospital hospital : removeHospital){
            if (nearHospital.contains(hospital)){
                nearHospital.remove(hospital);
            }
        }
//        m.addAttribute("isHaveHospital",true);
        m.addAttribute("hospitalHave",appUser.getHospitals());
        m.addAttribute("neareHospitals",nearHospital);
        m.addAttribute("user",appUser);

        return "nearHospital";

    }
    @GetMapping("/addhospital/{hospitalId}")
    public RedirectView addHospital(@PathVariable Long hospitalId, Principal p,Model m){
        Hospital hospital = hospitalRepo.findById(hospitalId).get();
        AppUser appUser = userRepo.findByUsername(p.getName());
        if (hospital.getDonors().contains(appUser)){
            hospital.getDonors().remove(appUser);

        }else {
            hospital.getDonors().add(appUser);
        }
        hospitalRepo.save(hospital);
        return new RedirectView("/neaarhospital");
    }
    @GetMapping("/getDonors/{type}")
    public String getDoners(Principal p,Model m,@PathVariable String type){
//        java.util.Date utilDate = new java.util.Date();
//int x = utilDate.getMonth();

        ArrayList<AppUser> doonersList = new ArrayList<>();
        Hospital hospital = hospitalRepo.findByUsername(p.getName());
        for (AppUser doners : hospital.getDonors()) {
            if ( doners.getStatus().equals("no")) {
                LocalDate current = LocalDate.now();
              LocalDate testIf = doners.getDonatDate().toLocalDate();

              if(current.equals(testIf.plusMonths(3)) || !current.isAfter(testIf.plusMonths(3))){
                  doners.setStatus("yes");
                  userRepo.save(doners);
              }

            }
        }
        if(hospital.getDonors().isEmpty()){
            m.addAttribute("on",0);
        }
        if(type.equals("all")){
//
            m.addAttribute("doners",hospital.getDonors());
            m.addAttribute("on",1);
            m.addAttribute("word",type);



            return "donerss";

        }else if (type.equals("o+")){
            for (AppUser doners : hospital.getDonors()){
                if(doners.getBlodType().equals("o+")){
                    doonersList.add(doners);
                }

            }

        }else if (type.equals("o-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("o-")) {
                    doonersList.add(doners);
                }

            }
            }
            else if (type.equals("a+")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("a+")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("a-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("a-")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("b+")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("b+")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("b-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("b-")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("ab+")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("ab+")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("ab-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("ab-")) {
                    doonersList.add(doners);
                }

            }
        }
            m.addAttribute("word",type);
        m.addAttribute("doners",doonersList);
        m.addAttribute("on",1);
//

        return  "donerss";
    }
    @GetMapping("confermDonate/{userId}")
    public RedirectView confarmDonate (Principal p, Model m,@PathVariable Long userId){

//        Hospital hospital = hospitalRepo.findByUsername(p.getName());
        AppUser appUser = userRepo.findById(userId).get();
        java.util.Date utilDate = new java.util.Date();
        appUser.setDonatDate(new java.sql.Date(utilDate.getTime()));
        appUser.setStatus("no");
        userRepo.save(appUser);
        return new RedirectView("getDonors/all");
    }







}
