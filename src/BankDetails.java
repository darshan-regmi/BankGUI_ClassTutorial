public class BankDetails {
    String name;
    String mobile;
    String email;
    String citizenship;
    String accountType;
    String province;
    double balance;

    public BankDetails(String name, String mobile, String email, String citizenship, String accountType, String province, double balance) {
        this.name = name;
        this.mobile = mobile;
        this.email = email;
        this.citizenship = citizenship;
        this.accountType = accountType;
        this.province = province;
        this.balance = balance;
    }
}