package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.dto.BillDto;
import com.ecommerce.ecommerce.entity.Bill;
import com.ecommerce.ecommerce.entity.Cart;
import com.ecommerce.ecommerce.entity.Discount;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.BillService;
import com.ecommerce.ecommerce.service.CartService;
import com.ecommerce.ecommerce.service.DiscountService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequestMapping("/buyer/bill")
@RequiredArgsConstructor
@Controller
public class BillController {
    private final BillService billService;

    private final CartService cartService;

    private final UserService userService;

    private final DiscountService discountService;

    public static String uploadDir = System.getProperty("user.dir") + "/bill";

    @GetMapping("/checkout")
    public String getBill(Model model,@RequestParam(defaultValue = "0") double discount) {

        User user = userService.getActiveUser().get();
        int userId = user.getUserId();
        model.addAttribute("carts", cartService.getDataByUserId(userId));
        model.addAttribute("user", user);
        model.addAttribute("discount",discount);
        return "checkout";
    }

    @PostMapping("/discount")
    public String discountCart(@RequestParam String code) {
        User user=userService.getActiveUser().get();
        int userId = user.getUserId();
        Optional<Discount> discount =discountService.getByCode(code);
        double discountPercentage=0;
        if(discount.isPresent()){
            discountPercentage=discount.get().getDiscountPercentage();
            discountService.setDiscountStatus(code);
            return "redirect:/buyer/bill/checkout?discount="+discountPercentage;
        }
        else{
            String error="Invalid Code or Code Expired";
            return "redirect:/buyer/bill/checkout?error="+error;
        }
    }

    @PostMapping("/save")
    public String saveBill(BillDto billDto) {

        int billId = 0;
        User user = userService.getActiveUser().orElse(new User());
        int userId = user.getUserId();

        List<Cart> carts = cartService.getDataByUserId(userId);
        billService.saveBill(billDto, carts);
        billId=billService.getLatestBillId();
        if (Objects.equals(billDto.getBillPayment(), "Cash")) {
            return "redirect:/buyer/bill/show/"+billId;
        }

        return "redirect:/buyer/bill/QR?id="+billId;

    }

@GetMapping("/show/{id}")
public String showBill(Model model, @PathVariable int id){
        User user = userService.getActiveUser().orElse(new User());
    Bill bill = billService.getByIdNoOpt(id).orElse(new Bill());
    List<Cart> carts = cartService.getCartByBillId(bill.getBillId());
    model.addAttribute("carts", carts);
        model.addAttribute("bill", bill);
        return "bill";
        }



    @GetMapping("/QR")
    public String getQR(Model model)
    {
        User user = userService.getActiveUser().orElse(new User());
        model.addAttribute("user", user);
        return "multiplePaymentMethod";
    }

    @PostMapping("/upload/{id}")
    public String uploadBill(BillDto billDto, @PathVariable String id) {
        return "redirect:/buyer/bill/show/"+id;
    }
}
