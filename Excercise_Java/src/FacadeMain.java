
class AccountService {
    public void getAccountDetails(String accountId) {
        System.out.println("Fetching account details for account ID: " + accountId);
    }
}

class TransferService {
    public void transferFunds(String fromAccount, String toAccount, double amount) {
        System.out.println("Transferring " + amount + " from account " + fromAccount + " to account " + toAccount);
    }
}

class BillPaymentService {
    public void payBill(String accountId, String billId, double amount) {
        System.out.println("Paying bill " + billId + " from account " + accountId + " with amount " + amount);
    }
}

class BankingFacade {
    private AccountService accountService;
    private TransferService transferService;
    private BillPaymentService billPaymentService;

    public BankingFacade() {
        this.accountService = new AccountService();
        this.transferService = new TransferService();
        this.billPaymentService = new BillPaymentService();
    }

    public void getAccountDetails(String accountId) {
        accountService.getAccountDetails(accountId);
    }

    public void transferFunds(String fromAccount, String toAccount, double amount) {
        transferService.transferFunds(fromAccount, toAccount, amount);
    }

    public void payBill(String accountId, String billId, double amount) {
        billPaymentService.payBill(accountId, billId, amount);
    }
}

public class FacadeMain {
    public static void main(String[] args) {
        BankingFacade bankingFacade = new BankingFacade();
        bankingFacade.getAccountDetails("123456");
        bankingFacade.transferFunds("123456", "654321", 100.0);
        bankingFacade.payBill("123456", "BILL001", 50.0);
    }
}