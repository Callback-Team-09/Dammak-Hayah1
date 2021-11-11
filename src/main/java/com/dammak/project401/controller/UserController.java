package com.dammak.project401.controller;

import com.dammak.project401.Repo.HospitalRepo;
import com.dammak.project401.Repo.NumberRepo;
import com.dammak.project401.Repo.UserRepo;
import com.dammak.project401.models.AppUser;
import com.dammak.project401.models.Hospital;
import com.dammak.project401.models.NumberDonate;
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
    @Autowired
    NumberRepo numberRepo;



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


        AppUser appUser = new AppUser(username, encoder.encode(password),firstName,lastName,dateOfBirth,blodType,placeName,emailAdress,phoneNum,"ROLE_USER","yes",0);
        userRepo.save(appUser);
        NumberDonate numberDonate = numberRepo.findByUsername("global");
        numberDonate.setNumberOfUser(numberDonate.getNumberOfUser()+1);
        numberRepo.save(numberDonate);
        return new RedirectView("/login");

    }
    @GetMapping("/")
    public String homePage (Model m){
        NumberDonate numberDonate = numberRepo.findByUsername("global");


        m.addAttribute("number", numberDonate.getNumberOfDonate());
        m.addAttribute("numberofuser",numberDonate.getNumberOfUser());
        return  "home";
    }
//    @GetMapping("/about")
//    public String about
    @GetMapping("/myprofile")
    public RedirectView profile(Principal p, Model m){

       AppUser doner = userRepo.findByUsername(p.getName());
        String auth = doner.getAuthority();
        if(auth.equals("admin")){
//            Hospital hospital = hospitalRepo.findByUsername(p.getName());
//            m.addAttribute("hospitalname",p.getName());
            return  new RedirectView("/getDonors/all");
        }

       return new RedirectView("/profile");
    }
    @GetMapping("/profile")
    public String userProfil(Principal p, Model m){
        AppUser doner = userRepo.findByUsername(p.getName());
        m.addAttribute("userInformatiom",doner);
        return "profile";
    }
    @GetMapping("/about")
    public String getAboutUs(){

        return "about";
    }

    @GetMapping("/user/{id}")
    public String getUser(Principal p, Model model, @PathVariable Long id) {
        model.addAttribute("usernamePrincipal", p.getName());
        AppUser appUser = userRepo.findById(id).get();
        model.addAttribute("userInformation", appUser);
        return "users.html";
    }
    @GetMapping("/allhospital")
    public String getAllHospital(Principal p,Model m){
        AppUser appUser= userRepo.findByUsername(p.getName());
        List<Hospital> nearHospital = (List<Hospital>) hospitalRepo.findAll();
        if (appUser.getHospitals().isEmpty()){
            m.addAttribute("isHaveHospital",false);
            m.addAttribute("allhospitals",nearHospital);
            return "allhospital";

        }
        Set<Hospital> removeHospital = appUser.getHospitals();
        for (Hospital hospital : removeHospital){
            if (nearHospital.contains(hospital)){
                nearHospital.remove(hospital);
            }
        }
        m.addAttribute("isHaveHospital",true);
        m.addAttribute("hospitalHave",appUser.getHospitals());
        m.addAttribute("allhospitals",nearHospital);
        m.addAttribute("user",appUser);

        return "allhospital";

    }
    @GetMapping("/neaarhospital")
    public String getNearHospital(Principal p,Model m){

        AppUser appUser= userRepo.findByUsername(p.getName());
        List<Hospital> nearHospital = hospitalRepo.findAllByPlaceName(appUser.getPlaceName());
        if (appUser.getHospitals().isEmpty()){
            m.addAttribute("isHaveHospital",false);
            m.addAttribute("neaarhospitals",nearHospital);
            return "neaarhospital";

        }
        Set<Hospital> removeHospital = appUser.getHospitals();
        for (Hospital hospital : removeHospital){
            if (nearHospital.contains(hospital)){
                nearHospital.remove(hospital);
            }
        }
        m.addAttribute("isHaveHospital",true);
        m.addAttribute("hospitalHave",appUser.getHospitals());
        m.addAttribute("neaarhospitals",nearHospital);
        m.addAttribute("user",appUser);

        return "neaarhospital";

    }
    @GetMapping("/addhospital/{direct}/{hospitalId}")
    public RedirectView addHospital(@PathVariable String direct, @PathVariable Long hospitalId, Principal p,Model m){
        Hospital hospital = hospitalRepo.findById(hospitalId).get();
        AppUser appUser = userRepo.findByUsername(p.getName());
        if (hospital.getDonors().contains(appUser)){
            hospital.getDonors().remove(appUser);

        }else {
            hospital.getDonors().add(appUser);
        }
        hospitalRepo.save(hospital);
if(direct.equals("all")){
    return new RedirectView("/allhospital");
}
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

              if(current.equals(testIf.plusMonths(3)) || current.isAfter(testIf.plusMonths(3))){
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
            m.addAttribute("number",hospital.getNumnerOfDonat());


            m.addAttribute("name",hospital.getUsername());

            return "donerss";

        }else if (type.equals("O+")){
            for (AppUser doners : hospital.getDonors()){
                if(doners.getBlodType().equals("O+")){
                    doonersList.add(doners);
                }

            }

        }else if (type.equals("O-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("O-")) {
                    doonersList.add(doners);
                }

            }
            }
            else if (type.equals("A+")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("A+")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("A-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("A-")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("B+")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("B+")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("B-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("B-")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("AB+")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("AB+")) {
                    doonersList.add(doners);
                }

            }}else if (type.equals("AB-")){
            for (AppUser doners : hospital.getDonors()) {
                if (doners.getBlodType().equals("AB-")) {
                    doonersList.add(doners);
                }

            }
        }
            m.addAttribute("word",type);
        m.addAttribute("doners",doonersList);
        m.addAttribute("on",1);
        m.addAttribute("number",hospital.getNumnerOfDonat());
        m.addAttribute("name",hospital.getUsername());
//

        return  "donerss";
    }

    @GetMapping("confermDonate/{userId}")
    public RedirectView confarmDonate (Principal p, Model m,@PathVariable Long userId){

//        Hospital hospital = hospitalRepo.findByUsername(p.getName());
        AppUser appUser = userRepo.findById(userId).get();
        appUser.setStatus("no");
        userRepo.save(appUser);

        java.util.Date utilDate = new java.util.Date();
        appUser.setDonatDate(new java.sql.Date(utilDate.getTime()));
        userRepo.save(appUser);
        return new RedirectView("/number/"+userId);
    }

@GetMapping("/number/{userId}")
    public RedirectView addNumberOfDoners(Principal p,@PathVariable Long userId) {

    AppUser appUser = userRepo.findById(userId).get();
    appUser.setNumberOfDonat(appUser.getNumberOfDonat() + 1);
    Hospital hospital = hospitalRepo.findByUsername(p.getName());
    hospital.setNumnerOfDonat(hospital.getNumnerOfDonat() + 1);
    NumberDonate numberDonate = numberRepo.findByUsername("global");

    numberDonate.setNumberOfDonate(numberDonate.getNumberOfDonate() + 1);
    userRepo.save(appUser);
    hospitalRepo.save(hospital);
    numberRepo.save(numberDonate);
    return new RedirectView("/getDonors/all");
}


}
