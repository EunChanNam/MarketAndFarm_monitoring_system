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
                             @Validated @ModelAttribute("releaseForm") ReleaseInputForm form,
                             BindingResult bindingResult,
                             Model model) {

        Stock stock = stockService.findStock(stockId);
        model.addAttribute("stock", stock); //@ModelAttribute로 주고 받고 service로직에서 stock찾는 쿼리를 날리는게 베스트

        if (bindingResult.hasErrors()) {
            return "releaseInputUpload";
        }

        if (stock.getQuantity() < form.getQuantity()) {
            bindingResult.rejectValue("quantity", "NotEnoughQuantity", "수량이 부족합니다.");
            return "releaseInputUpload";
        }

        Company company = cr.findByName(form.getCompanyName());
        if (company == null) {
            Company newCompany = new Company(form.getCompanyName());
            cr.save(newCompany);
            rs.saveRelease(form.getPrice(), form.getQuantity(), form.getReleasedDate(), stock, newCompany);
        } else {
            rs.saveRelease(form.getPrice(), form.getQuantity(), form.getReleasedDate(), stock, company);
        }

        return "redirect:/"; //todo 출고 리스트페이지 완성되면 수정
    }
}
