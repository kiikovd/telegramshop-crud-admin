package com.javamonster.spring;

import com.javamonster.spring.model.Supplier;
import com.javamonster.spring.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SupplierController {

    private SupplierService supplierService;

    @Autowired(required = true)
    @Qualifier("supplierService")
    public void setSupplierService(SupplierService supplierService){
        this.supplierService = supplierService;
    }

    @RequestMapping(value = "/suppliers", method = RequestMethod.GET)
    public String listSuppliers(Model model){
        model.addAttribute("supplier", new Supplier());
        model.addAttribute("listSuppliers", supplierService.listSuppliers());
        return "supplier";
    }

    @RequestMapping(value = "suppliers/add", method = RequestMethod.POST)
    public String addSupplier(@ModelAttribute("supplier") Supplier s){
        if(s.getId() == 0){
            supplierService.addSupplier(s);
        }else{
            supplierService.updateSupplier(s);
        }
        return "redirect:/suppliers";
    }

    @RequestMapping("/remove/{id}")
    public String removeSupplier(@PathVariable("id") int id){
        supplierService.removeSupplier(id);
        return "redirect:/suppliers";
    }

    @RequestMapping("/edit/{id}")
    public String editSupplier(@PathVariable("id") int id, Model model){
        model.addAttribute("supplier", supplierService.getSupplierById(id));
        model.addAttribute("listPersons", supplierService.listSuppliers());
        return "supplier";
    }
}
