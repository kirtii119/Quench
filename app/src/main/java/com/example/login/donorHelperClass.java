package com.example.login;

public class donorHelperClass {
    String name,contactNumber,state,city,district,area,pincode,street,buildingName,houseNumber,quantity,phoneNumber;

    public donorHelperClass() {
    }



    public donorHelperClass(String name, String contactNumber, String state, String city, String district, String area, String pincode, String street, String buildingName, String houseNumber, String quantity,String phoneNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.state = state;
        this.city = city;
        this.district = district;
        this.area = area;
        this.pincode = pincode;
        this.street = street;
        this.buildingName = buildingName;
        this.houseNumber = houseNumber;
        this.quantity = quantity;
        this.phoneNumber=phoneNumber;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
