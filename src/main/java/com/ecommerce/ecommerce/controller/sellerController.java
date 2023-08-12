package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.entity.Item;
import com.ecommerce.ecommerce.entity.Sales;
import com.ecommerce.ecommerce.entity.User;
import com.ecommerce.ecommerce.service.CategoryService;
import com.ecommerce.ecommerce.service.ItemService;
import com.ecommerce.ecommerce.service.SaleService;
import com.ecommerce.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/seller")
public class SellerController {
    private final UserService userService;

    private  final ItemService itemService;

    private final CategoryService categoryService;

    private final SaleService saleService;
    @GetMapping("/verify/{email}")
    public String sellerVerify(@PathVariable String email, Model model){
        model.addAttribute("email", email);

        return "sellerVerify";
    }
    @PostMapping("/verify/{email}")
    public String sellerVerify(@PathVariable String email, @RequestParam("panNumber") String panNumber){
        userService.verifySeller(email,panNumber);
        return "redirect:/login?success";
    }

    @GetMapping("/dashboard")
    public String sellerDashboard(Model model){
        User user = userService.getActiveUser().get();
        saleService.saveSale(user);
        model.addAttribute("user",user);
        List<Sales> sales=saleService.pastSevenDaysSales(user);

        List<LocalDate> totalDates=getDatesBetween();
        List<Double> totalSalesList=getTotalSales(totalDates,sales);

        model.addAttribute("soldItem",saleService.getSalesCount(user));
        model.addAttribute("count",itemService.getItemCount());

        model.addAttribute("totalSales",totalSalesList);
        model.addAttribute("day",totalDates);
        return "sellerDashboard";
    }

    public List<Double> getTotalSales(List<LocalDate> totalDates,List<Sales> sales){
        double[] totalSales=new double[7];
        for(int i=0;i<7;i++){
            for(Sales sale:sales){
                if(sale.getDate().equals(totalDates.get(i))){
                    totalSales[i]=sale.getTotal();
                }
            }
        }
        List<Double> totalSalesList= new ArrayList<>();
        for(double sale:totalSales){
            totalSalesList.add(sale);
        }
        return totalSalesList;
    }

    public List<LocalDate> getDatesBetween(){
        LocalDate start = LocalDate.now().minusDays(7);
        LocalDate end = LocalDate.now();
        List<LocalDate> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(start);
            start = start.plusDays(1);
        }
        return totalDates;
    }

    @GetMapping("/inventory")
    public String menuByCategory(Model model, @RequestParam(defaultValue = "0") int id,@RequestParam(defaultValue = "1") int page,@RequestParam(defaultValue = "") String partialName) throws IOException {
        int totalItems;
        User activeUser = userService.getActiveUser().get();
        if(id==0) {
            totalItems = itemService.countAllItemsBySeller(partialName,activeUser.getUserId());
        }
        else{
            totalItems = itemService.countAllItemsByCategoryIdAndSeller(id,partialName,activeUser.getUserId());
        }

        int pageSize=6;

        // Calculate the total number of pages based on the page size and total items
        int totalPages = (int) Math.ceil((double) totalItems / pageSize);

        if(totalPages==0){
            totalPages=1;
        }
        // Ensure the requested page is within the valid range
        if (page < 1) {
            page = 1;
        } else if (page > totalPages) {
            page = totalPages;
        }

        // Calculate the starting index and ending index for the subset of items to display
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, totalItems);





        List<Item> items =new ArrayList<>();
        if(id==0){
            items=itemService.getSixItemsBySeller(page,partialName,activeUser.getUserId());
        }else{
            items=itemService.getSixItemsByCategoryIdAndSeller(id,page,partialName,activeUser.getUserId());
        }


        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("categories", categoryService.getData());
        model.addAttribute("items", items.stream().map(item -> Item.builder()
                .itemId(item.getItemId())
                .itemName(item.getItemName())
                .itemPrice(item.getItemPrice())
                .itemDescription(item.getItemDescription())
                .itemQuantity(item.getItemQuantity())
                .imageBase64(getImageBase64(item.getItemImage()))
                .category(item.getCategory())
                .build()
        ));

        model.addAttribute("user",activeUser);
        return "inventoryManagement";
    }


    public String getImageBase64(String fileName) {
        String filePath = System.getProperty("user.dir") + "/item_img/";
        File file = new File(filePath + fileName);
        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return Base64.getEncoder().encodeToString(bytes);
    }
}
