package splitwise.project.splitwise.Services;

import java.util.List;
import java.util.Map;

import splitwise.project.splitwise.Model.User;

public interface BalanceService {
    Map<User, Double> calculateBalances(Long groupId);

    List<Map<String, Object>> settleDebts(Long groupId);
    
}
