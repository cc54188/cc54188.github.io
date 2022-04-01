package com.example.demo.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "Atm")
public class Atm {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // 身分證
    @Column(name = "idno")
    private String idno;

    // 手機
    @Column(name = "phone")
    private String phone;

    // 交易類型  // 新加的
    @Column(name = "type")
    private int type;

    //有摺
    @Column(name = "hasPassbook")
    private boolean hasPassbook;

    //本人帳戶
    @Column(name = "isSelfAccount")
    private boolean isSelfAccount;

    //是否為現金 or 轉帳
    @Column(name = "isCash")
    private boolean isCash;

    //轉帳/匯款/存款/提款 金額
    @Column(name = "money")
    private long money;

    //臨櫃交易人姓名 (存提款)(無摺-非本人)
    @Column(name = "counterTrader")
    private String counterTrader;

    //臨櫃交易人身分證號 (存提款)(無摺-非本人)
    @Column(name = "counterTraderId")
    private String counterTraderId;

    //臨櫃交易人聯絡電話 (存提款)(無摺-非本人)
    @Column(name = "counterTraderPhone")
    private String counterTraderPhone;

    //代理人姓名 (匯款)(無摺-非本人)
    @Column(name = "agentTrader")
    private String agentTrader;

    //代理人身分證號 (匯款)(無摺-非本人)
    @Column(name = "agentTraderId")
    private String agentTraderId;

    //代理人聯絡電話 (匯款)(無摺-非本人)
    @Column(name = "agentTraderPhone")
    private String agentTraderPhone;

    //存入帳號 (Deprecated)
    @Column(name = "bankAccount")
    private String bankAccount;

    //轉出帳號
    @Column(name = "depositAccount")
    private String depositAccount;

    //存入帳號
    @Column(name = "withdrawAccount")
    private String withdrawAccount;

    //收款銀行
    @Column(name = "withdrawBank")
    private String withdrawBank;

    //收款分行
    @Column(name = "withdrawBankBranch")
    private String withdrawBankBranch;

    //存摺備註 匯款附言
    @Column(name = "memo")
    private String memo;

    //本行/他行
    @Column(name = "isOwnedBank")
    private boolean isOwnedBank;

    //轉出帳號 扣款帳號
    @Column(name = "remitterAccount")
    private String remitterAccount;

    //轉入&收款帳號
    @Column(name = "payeeAccount")
    private String payeeAccount;

    //收款戶名
    @Column(name = "payeeName")
    private String payeeName;

    //收款銀行ID
    @Column(name = "payeeBankId")
    private String payeeBankId;

    //匯款方式 1: 存摺扣款匯款, 2: 現金匯款
    @Column(name = "transactionType")
    private long transactionType;

    //匯款人姓名
    @Column(name = "remitterName")
    private String remitterName;

    //匯款人身分證字號
    @Column(name = "remitterIdno")
    private String remitterIdno;

    //匯款人電話
    @Column(name = "remitterPhone")
    private String remitterPhone;

    @Column(name = "isAgent")
    private boolean isAgent;

    //代理人姓名
    @Column(name = "agentName")
    private String agentName;

    //代理人身分證字號
    @Column(name = "agentIdno")
    private String agentIdno;

    //代理人電話
    @Column(name = "agentPhone")
    private String agentPhone;

    // 是否已完成
    @Column
    private boolean isFinish;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isHasPassbook() {
        return hasPassbook;
    }

    public void setHasPassbook(boolean hasPassbook) {
        this.hasPassbook = hasPassbook;
    }

    public boolean isIsSelfAccount() {
        return isSelfAccount;
    }

    public void setIsSelfAccount(boolean isSelfAccount) {
        this.isSelfAccount = isSelfAccount;
    }

    public boolean isIsCash() {
        return isCash;
    }

    public void setIsCash(boolean isCash) {
        this.isCash = isCash;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getCounterTrader() {
        return counterTrader;
    }

    public void setCounterTrader(String counterTrader) {
        this.counterTrader = counterTrader;
    }

    public String getCounterTraderId() {
        return counterTraderId;
    }

    public void setCounterTraderId(String counterTraderId) {
        this.counterTraderId = counterTraderId;
    }

    public String getCounterTraderPhone() {
        return counterTraderPhone;
    }

    public void setCounterTraderPhone(String counterTraderPhone) {
        this.counterTraderPhone = counterTraderPhone;
    }

    public String getAgentTrader() {
        return agentTrader;
    }

    public void setAgentTrader(String agentTrader) {
        this.agentTrader = agentTrader;
    }

    public String getAgentTraderId() {
        return agentTraderId;
    }

    public void setAgentTraderId(String agentTraderId) {
        this.agentTraderId = agentTraderId;
    }

    public String getAgentTraderPhone() {
        return agentTraderPhone;
    }

    public void setAgentTraderPhone(String agentTraderPhone) {
        this.agentTraderPhone = agentTraderPhone;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getDepositAccount() {
        return depositAccount;
    }

    public void setDepositAccount(String depositAccount) {
        this.depositAccount = depositAccount;
    }

    public String getWithdrawAccount() {
        return withdrawAccount;
    }

    public void setWithdrawAccount(String withdrawAccount) {
        this.withdrawAccount = withdrawAccount;
    }

    public String getWithdrawBank() {
        return withdrawBank;
    }

    public void setWithdrawBank(String withdrawBank) {
        this.withdrawBank = withdrawBank;
    }

    public String getWithdrawBankBranch() {
        return withdrawBankBranch;
    }

    public void setWithdrawBankBranch(String withdrawBankBranch) {
        this.withdrawBankBranch = withdrawBankBranch;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public boolean isIsOwnedBank() {
        return isOwnedBank;
    }

    public void setIsOwnedBank(boolean isOwnedBank) {
        this.isOwnedBank = isOwnedBank;
    }

    public String getRemitterAccount() {
        return remitterAccount;
    }

    public void setRemitterAccount(String remitterAccount) {
        this.remitterAccount = remitterAccount;
    }

    public String getPayeeAccount() {
        return payeeAccount;
    }

    public void setPayeeAccount(String payeeAccount) {
        this.payeeAccount = payeeAccount;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayeeBankId() {
        return payeeBankId;
    }

    public void setPayeeBankId(String payeeBankId) {
        this.payeeBankId = payeeBankId;
    }

    public long getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(long transactionType) {
        this.transactionType = transactionType;
    }

    public String getRemitterName() {
        return remitterName;
    }

    public void setRemitterName(String remitterName) {
        this.remitterName = remitterName;
    }

    public String getRemitterIdno() {
        return remitterIdno;
    }

    public void setRemitterIdno(String remitterIdno) {
        this.remitterIdno = remitterIdno;
    }

    public String getRemitterPhone() {
        return remitterPhone;
    }

    public void setRemitterPhone(String remitterPhone) {
        this.remitterPhone = remitterPhone;
    }

    public boolean isIsAgent() {
        return isAgent;
    }

    public void setIsAgent(boolean isAgent) {
        this.isAgent = isAgent;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentIdno() {
        return agentIdno;
    }

    public void setAgentIdno(String agentIdno) {
        this.agentIdno = agentIdno;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isIsFinish() {
        return isFinish;
    }

    public void setIsFinish(boolean isFinish) {
        isFinish = isFinish;
    }
}
