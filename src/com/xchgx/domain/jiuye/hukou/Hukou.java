/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xchgx.domain.jiuye.hukou;

import java.io.Serializable;

/**
 *
 * @author cg
 */
public class Hukou implements Serializable{

    private String no;
    private String name;
    private String sex;
    private String profession;
    private String sfz;
    private String where;
    private String company;

    public Hukou() {
    }

    public Hukou(String no, String name, String sex, String profession, String sfz, String where, String company) {
        this.no = no;
        this.name = name;
        this.sex = sex;
        this.profession = profession;
        this.sfz = sfz;
        this.where = where;
        this.company = company;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return no+","+name+","+sex+","+profession+","+sfz+","+where+","+company;
    }

}
