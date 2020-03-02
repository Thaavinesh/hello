package com.example.healthcaresystemproject.ModelClasses;

public class Products {
    private String Category, Description, date, pName, pid, price, time;

    public Products(){

    }

    public Products(String category, String description, String date, String pName, String pid, String price, String time) {
        Category = category;
        Description = description;
        this.date = date;
        this.pName = pName;
        this.pid = pid;
        this.price = price;
        this.time = time;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
