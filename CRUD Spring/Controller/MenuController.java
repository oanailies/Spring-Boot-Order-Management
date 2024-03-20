package com.example.CRUD_Tutorial.Controller;

import com.example.CRUD_Tutorial.Entity.Menu;
import com.example.CRUD_Tutorial.Entity.User;
import com.example.CRUD_Tutorial.Service.MenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.webjars.NotFoundException;

import java.util.List;

@Controller
public class MenuController {

  @Autowired private MenuService service;

  @GetMapping("/menus")
  public String showMenuList(Model model)
  {
      List<Menu> listMenus=service.listAll();
      model.addAttribute("listMenus", listMenus);
      return "menus";
  }




    @GetMapping("/menus/new")
    public String showNewForm(Model model)
    {
       model.addAttribute("menu", new Menu());
       model.addAttribute("pageTitle", "Add New Menu");
        return "menus_form";
    }

    @PostMapping("/menus/save")
    public String saveMenu(@ModelAttribute("menu") Menu menu) {
        service.saveMenu(menu);
        return "redirect:/menus";
    }

    @GetMapping("/menus/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Menu menu = service.getMenuById(id).orElseThrow();
            model.addAttribute("menu", menu);
            model.addAttribute("pageTitle", "Edit Menu (ID: " + id + ")");
            return "menus_form"; //
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", "Menu not found with ID: " + id);
            e.printStackTrace();
            return "redirect:/menus";
        }
    }

    @GetMapping("/menus/delete/{id}")
    public String deleteMenu(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.deleteMenuById(id);
            ra.addFlashAttribute("message", "Menu with ID: " + id + " has been deleted successfully");
            return "redirect:/menus";
        } catch (NotFoundException e) {
            ra.addFlashAttribute("message", "Menu not found with ID: " + id);
            e.printStackTrace();
            return "redirect:/menus";
        }
    }








}
