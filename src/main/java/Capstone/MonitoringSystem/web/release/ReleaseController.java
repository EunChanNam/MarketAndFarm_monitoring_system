package Capstone.MonitoringSystem.web.release;

import Capstone.MonitoringSystem.domain.Company.Company;
import Capstone.MonitoringSystem.domain.Company.CompanyRepository;
import Capstone.MonitoringSystem.domain.Release.releaseservice.ReleaseService;
import Capstone.MonitoringSystem.domain.Stock.Stock;
import Capstone.MonitoringSystem.domain.Stock.stockservice.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class ReleaseController {

    private final ReleaseService rs;
    private final StockService stockService;
    private final CompanyRepository cr;

    @GetMapping("releases/new/{stockId}")
    public String releaseForm(@PathVariable Long stockId,
                              @ModelAttribute("releaseForm") ReleaseInputForm form,
                              Model model) {

        Stock stock = stockService.findStock(stockId);
        model.addAttribute("stock", stock);

        return "releaseInputUpload";
    }

    @PostMapping("releases/new")
    public String newRelease(@RequestParam Long stockId,
                             @Validated @ModelAttribute ReleaseInputForm form,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "releaseInputUpload";
        }

        Company company = cr.findByName(form.getCompanyName());
        if (company == null) {
            Company newCompany = new Company(form.getCompanyName());
            cr.save(newCompany);
            rs.saveRelease(form.getPrice(), form.getQuantity(), form.getReleasedDate(), stockId, newCompany);
        } else {
            rs.saveRelease(form.getPrice(), form.getQuantity(), form.getReleasedDate(), stockId, company);
        }

        return "redirect:/"; //todo 출고 리스트페이지 완성되면 수정
    }
}
