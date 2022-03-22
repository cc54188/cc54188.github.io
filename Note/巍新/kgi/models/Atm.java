package com.example.kgi.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Atm")
public class Atm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // 交易類型                (我加的)
    @Column(name = "type")
    private String type;
    //使用者名稱               (不知道FEP規格，想稍作限制)
    @Column(name = "userName", length = 6)
    private String userName;
    //電話號碼
    @Column(name = "phoneNumber")
    private int phoneNumber;
    //身分證
    @Column(name = "userId")
    private String userId;
    // 使用者帳號               (建議改long，可能不用unique)
    @Column(name = "userAccount")
    private long userAccount;
    //選擇是否有摺 無摺
    @Column(name = "passBook")
    private boolean passBook;
    //操作台幣金額
    @Column(name = "ntdAount" , length = 14)
    private int ntdAount;
    //備註
    @Column(name = "content",length = 16)
    private String content;
    //選擇是否為本人帳號
    @Column(name = "saveMySelf")
    private boolean saveMySelf;
    //創建時間
    @CreationTimestamp  //針對時間貨日期創建默認值
    @Temporal(TemporalType.DATE) //只要日期
    @Column(nullable = false,updatable = false) //不能為空且不能更新
    private Date CreatTime;
    // 收款銀行               ------(下面都我加的)------
    @Column(name = "inBank")
    private String inBank;
    // 收款分行
    @Column(name = "branch")
    private String branch;
    // 收款人戶名               (長度不知道FEP，想稍作限制)
    @Column(name = "payeeName", length = 6)
    private String payeeName;
    // 收款人帳號
    @Column(name = "payeeAccount", length = 16)
    private long payeeAccount;
    // 是否替人轉帳，匯款
    @Column(name = "isReplace")
    private boolean isReplace;
    // 代理人姓名
    @Column(name = "replaceName")
    private String replaceName;
    // 代理人id
    @Column(name = "replaceId")
    private String replaceId;
    // 代理人電話
    @Column(name = "replacePhoneNumber")
    private int replacePhoneNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(long userAccount) {
        this.userAccount = userAccount;
    }

    public boolean isPassBook() {
        return passBook;
    }

    public void setPassBook(boolean passBook) {
        this.passBook = passBook;
    }

    public int getNtdAount() {
        return ntdAount;
    }

    public void setNtdAount(int ntdAount) {
        this.ntdAount = ntdAount;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isSaveMySelf() {
        return saveMySelf;
    }

    public void setSaveMySelf(boolean saveMySelf) {
        this.saveMySelf = saveMySelf;
    }

    public String getInBank() {
        return inBank;
    }

    public void setInBank(String inBank) {
        this.inBank = inBank;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public long getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(long payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public boolean isReplace() {
        return isReplace;
    }

    public void setReplace(boolean replace) {
        isReplace = replace;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }

    public String getReplaceId() {
        return replaceId;
    }

    public void setReplaceId(String replaceId) {
        this.replaceId = replaceId;
    }

    public int getReplacePhoneNumber() {
        return replacePhoneNumber;
    }

    public void setReplacePhoneNumber(int replacePhoneNumber) {
        this.replacePhoneNumber = replacePhoneNumber;
    }
}
