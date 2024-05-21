package splitwise.project.splitwise.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import splitwise.project.splitwise.Services.BalanceService;

@RestController
@RequestMapping("/balances")
public class BalanceController {
    @Autowired
    private BalanceService balanceService;

    @GetMapping("/{groupId}")
    public Map<String, Double> getBalances(@PathVariable Long groupId) {
        return balanceService.calculateBalances(groupId);
    }

    @GetMapping("/settle/{groupId}")
    public List<Map<String, Object>> settleDebts(@PathVariable Long groupId) {
        return balanceService.settleDebts(groupId);
    }
}
