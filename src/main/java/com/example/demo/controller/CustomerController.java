package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers/new")
    public String showNewCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        model.addAttribute("customerGroups", Customer.CustomerGroup.values());
        return "new-customer";
    }

    @PostMapping("/customers/new")
    public String createCustomer(
            @Valid Customer customer,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            // Xử lý lỗi cho từng field
            if (bindingResult.hasFieldErrors("customerName")) {
                List<String> customerNameErrors = bindingResult.getFieldErrors("customerName")
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                errorMessages.addAll(customerNameErrors);
            }

            if (bindingResult.hasFieldErrors("phoneNumber")) {
                List<String> phoneNumberErrors = bindingResult.getFieldErrors("phoneNumber")
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                errorMessages.addAll(phoneNumberErrors);
            }

            if (bindingResult.hasFieldErrors("customerGroup")) {
                List<String> customerGroupErrors = bindingResult.getFieldErrors("customerGroup")
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .collect(Collectors.toList());
                errorMessages.addAll(customerGroupErrors);
            }

            // Sắp xếp lỗi theo thứ tự cố định
            List<String> sortedErrors = errorMessages.stream()
                    .sorted((error1, error2) -> {
                        List<String> fixedOrder = List.of(
                                "Tên khách hàng không được để trống",
                                "Tên khách hàng chỉ chứa chữ cái, khoảng trắng và dấu",
                                "Tên khách hàng phải từ 3-50 ký tự",
                                "Nhóm khách hàng là bắt buộc",
                                "Số điện thoại không được để trống",
                                "Số điện thoại phải bắt đầu bằng 0 và có độ dài 10-12 ký tự"
                        );
                        return Integer.compare(
                                fixedOrder.indexOf(error1),
                                fixedOrder.indexOf(error2)
                        );
                    })
                    .collect(Collectors.toList());

            // Đưa các lỗi vào model
            model.addAttribute("errorMessages", sortedErrors);
            model.addAttribute("customerGroups", Customer.CustomerGroup.values());
            return "new-customer";
        }

        try {
            customerService.createCustomer(customer);
            redirectAttributes.addFlashAttribute("successMessage", "Thêm mới khách hàng thành công");
            return "redirect:/customers/new";
        } catch (RuntimeException e) {
            List<String> errorMessages = new ArrayList<>();
            errorMessages.add(e.getMessage());
            model.addAttribute("errorMessages", errorMessages);
            model.addAttribute("customerGroups", Customer.CustomerGroup.values());
            return "new-customer";
        }
    }

}
