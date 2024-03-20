package com.example.CRUD_Tutorial.Service;

import com.example.CRUD_Tutorial.Entity.Menu;
import com.example.CRUD_Tutorial.Repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {

    private final MenuRepository menuRepository;

    @Autowired
    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }


    public Optional<Menu> getMenuById(Integer id) {
        return menuRepository.findById(id);
    }


    public void saveMenu(Menu menu) {
        menuRepository.save(menu);
    }


    public void updateMenu(Integer menuId, Menu updatedMenu) {

        Optional<Menu> existingMenu = menuRepository.findById(menuId);

        if (existingMenu.isPresent()) {
            Menu menuToUpdate = existingMenu.get();
            menuToUpdate.setName(updatedMenu.getName());
            menuToUpdate.setPrice(updatedMenu.getPrice());
            menuToUpdate.setStock(updatedMenu.getStock());

            saveMenu(menuToUpdate);
        }
    }

    public void deleteMenuById(Integer id) {
        menuRepository.deleteById(id);
    }

    public void updateStock(Integer menuId, int newStock)
    {
        Optional<Menu> existingMenu = menuRepository.findById(menuId);

        if (existingMenu.isPresent()) {
            Menu menuToUpdate = existingMenu.get();
            menuToUpdate.setStock(newStock);

            saveMenu(menuToUpdate);
        }
    }

    public void updatePrice(Integer menuId, float newPrice)
    {
        Optional<Menu> existingMenu = menuRepository.findById(menuId);

        if (existingMenu.isPresent()) {
            Menu menuToUpdate = existingMenu.get();
            menuToUpdate.setPrice(newPrice);

            saveMenu(menuToUpdate);
        }
    }


    public List<Menu> listAll() {
        return (List<Menu>) menuRepository.findAll();
    }
}
