package com.xiaoxuedi.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users", schema = "xiaoxuedi", catalog = "")
public class UsersEntity {
    private String id;
    private String authStatus;
    private int balance;
    private Timestamp createTime;
    private boolean enabled;
    private String idCard;
    private String invitationCode;
    private int invitationCount;
    private String mobile;
    private String name;
    private String password;
    private String sex;
    private String username;
    private String schoolId;
    private String photo;

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "auth_status")
    public String getAuthStatus() {
        return authStatus;
    }

    public void setAuthStatus(String authStatus) {
        this.authStatus = authStatus;
    }

    @Basic
    @Column(name = "balance")
    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Basic
    @Column(name = "create_time")
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "enabled")
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "id_card")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @Basic
    @Column(name = "invitation_code")
    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    @Basic
    @Column(name = "invitation_count")
    public int getInvitationCount() {
        return invitationCount;
    }

    public void setInvitationCount(int invitationCount) {
        this.invitationCount = invitationCount;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "school_id")
    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    @Basic
    @Column(name = "photo")
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (balance != that.balance) return false;
        if (enabled != that.enabled) return false;
        if (invitationCount != that.invitationCount) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (authStatus != null ? !authStatus.equals(that.authStatus) : that.authStatus != null) return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (idCard != null ? !idCard.equals(that.idCard) : that.idCard != null) return false;
        if (invitationCode != null ? !invitationCode.equals(that.invitationCode) : that.invitationCode != null)
            return false;
        if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (sex != null ? !sex.equals(that.sex) : that.sex != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (schoolId != null ? !schoolId.equals(that.schoolId) : that.schoolId != null) return false;
        return photo != null ? photo.equals(that.photo) : that.photo == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (authStatus != null ? authStatus.hashCode() : 0);
        result = 31 * result + balance;
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (enabled ? 1 : 0);
        result = 31 * result + (idCard != null ? idCard.hashCode() : 0);
        result = 31 * result + (invitationCode != null ? invitationCode.hashCode() : 0);
        result = 31 * result + invitationCount;
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (schoolId != null ? schoolId.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }
}
