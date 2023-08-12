package com.ecommerce.ecommerce.service.impl;

import com.ecommerce.ecommerce.entity.*;
import com.ecommerce.ecommerce.repo.DiscountRepo;
import com.ecommerce.ecommerce.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService{
    private final DiscountRepo discountRepo;
    private final CategoryService categoryService;

    @Override
    public void addDiscount() {
        Discount discount = new Discount();
        List<Category> categories = categoryService.getData();
        List<Integer> id=new ArrayList<>();
        for(Category category : categories) {
            id.add(category.getCatId());
        }
        Random random = new Random();
        int index = random.nextInt(id.size());
        Optional<Category> category = categoryService.getByIdNoOpt(index);
        if(category.isEmpty()) {
            addDiscount();
        }else{
            discount.setCategory(category.get());
        }

        discount.setDiscountCode(generateRandomCode());

        discount.setDiscountPercentage(getRandomDiscount());
        discount.setDiscountStatus("active");
        discountRepo.save(discount);

    }

    @Override
    public List<Discount> getData() {
        return discountRepo.findAllByStatus();
    }

    @Override
    public Optional<Discount> getByCode(String code) {
        return discountRepo.findByDiscountCode(code);
    }

    @Override
    public void setDiscountStatus(String code) {
        Discount discount = discountRepo.findByDiscountCode(code).get();
        discount.setDiscountStatus("inactive");
        discountRepo.save(discount);
    }

    public static String generateRandomCode() {
        int length = 4;
        String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder code = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }

    public float getRandomDiscount(){
        Random random = new Random();
        return random.nextFloat(100);
    }
}
