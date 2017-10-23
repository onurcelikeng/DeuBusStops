package com.onurcelikeng.deubusstops.Models;

import java.io.Serializable;

public class SurveyModel implements Serializable {

    public String nameSurname;

    public String email;

    public String phone;

    public String gender;

    public String birthdate;

    public String busStop;

    public String opinion;

    public int point;

    public SurveyModel() {
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBusStop() {
        return busStop;
    }

    public void setBusStop(String busStop) {
        this.busStop = busStop;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public float getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
