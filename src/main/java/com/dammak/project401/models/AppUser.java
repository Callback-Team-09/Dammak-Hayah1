package com.dammak.project401.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.source.doctree.SeeTree;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
public class AppUser implements UserDetails {

    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String blodType;
    private String placeName;
    private String emailAdress;
    private String phoneNum;
    private String authority;
    private java.sql.Date donatDate;
    @JsonInclude
    @ManyToMany(mappedBy = "donors")
    private Set<Hospital> hospitals;
    private String status;
    private int numberOfDonat;

    @OneToMany(mappedBy = "appUser")
    private List<Comment> comments;



    public AppUser(){}

//    public AppUser(String username, String password, String firstName, String lastName, String dateOfBirth, String blodType,String authority, String placeName, String emailAdress, String phoneNum,String authority) {
//        this.username = username;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dateOfBirth = dateOfBirth;
//        this.blodType = blodType;
//        this.placeName=placeName;
//        this.emailAdress=emailAdress;
//        this.phoneNum=phoneNum;
//        this.authority = authority;
//
//    }

    public AppUser(String username, String password, String firstName, String lastName, String dateOfBirth, String blodType, String placeName, String emailAdress, String phoneNum, String role_user,String status,int numberOfDonat) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.blodType = blodType;
        this.placeName=placeName;
        this.emailAdress=emailAdress;
        this.phoneNum=phoneNum;
        this.authority = role_user;
        this.status = status;
        this.numberOfDonat=numberOfDonat;
    }
//    public AppUser(String username, String password){
//        this.username = username;
//        this.password = password;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(authority);
//        List<SimpleGrantedAuthority> grantedAuthorities=new ArrayList<SimpleGrantedAuthority>();
//        grantedAuthorities.add(simpleGrantedAuthority);
//        return grantedAuthorities;
        return null;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBlodType() {
        return blodType;
    }

    public void setBlodType(String blodType) {
        this.blodType = blodType;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Long getId() {
        return id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Set<Hospital> getHospitals() {
        return hospitals;
    }

    public void setHospitals(Set<Hospital> hospitals) {
        this.hospitals = hospitals;
    }
    public void addHospital(Hospital hospital){
        getHospitals().add(hospital);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public java.sql.Date getDonatDate() {
        return donatDate;
    }

    public void setDonatDate(java.sql.Date donatDate) {
        this.donatDate = donatDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getNumberOfDonat() {
        return numberOfDonat;
    }

    public void setNumberOfDonat(int numberOfDonat) {
        this.numberOfDonat = numberOfDonat;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setPosts(List<Comment> comments) {
        this.comments = comments;
    }
}