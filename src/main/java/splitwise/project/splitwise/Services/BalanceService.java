package splitwise.project.splitwise.Services;

import java.util.List;
import java.util.Map;

public interface BalanceService {
    Map<String, Double> calculateBalances(Long groupId);

    List<Map<String, Object>> settleDebts(Long groupId);
    
}
