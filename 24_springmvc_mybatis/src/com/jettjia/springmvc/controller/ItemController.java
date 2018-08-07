package com.jettjia.springmvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jettjia.springmvc.pojo.Item;
import com.jettjia.springmvc.pojo.QueryVo;
import com.jettjia.springmvc.service.ItemService;

@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 商品列表
     * 
     * @return
     */
    @RequestMapping("itemList")
    public ModelAndView itemList() {
        System.out.println("sss");
        ModelAndView mav = new ModelAndView();
        // 获取商品列表
        List<Item> itemList = itemService.getItemList();
        mav.addObject("itemList", itemList);
        mav.setViewName("itemList");
        return mav;
    }

    /**
     * 根据id查询商品，使用ModelAndView
     * 
     * @param request
     * @return
     */
    /*
     * @RequestMapping("itemEdit") public ModelAndView
     * itemEdit(HttpServletRequest request) { // 从request中获取请求参数 String idString
     * = request.getParameter("id"); Integer id = Integer.valueOf(idString); //
     * 根据id获取商品数据 Item item = itemService.getItemById(id); // 把结果返回给页面
     * ModelAndView mav = new ModelAndView(); mav.addObject("item", item);
     * mav.setViewName("itemEdit"); return mav; }
     */

    /**
     * 根据id查询商品，使用Model
     * 
     * @param request
     * @param model
     * @return
     */
    /*
     * @RequestMapping("itemEdit") public String itemEdit(HttpServletRequest
     * request, Model model) { // 从request中获取请求参数 String idString =
     * request.getParameter("id"); Integer id = Integer.valueOf(idString); //
     * 根据id获取商品数据 Item item = itemService.getItemById(id);
     * model.addAttribute("item", item); return "itemEdit"; }
     */

    /**
     * 根据id查询商品，使用ModelMap
     * 
     * @param request
     * @param model
     * @return
     */
    /*
     * @RequestMapping("itemEdit") public String itemEdit(HttpServletRequest
     * request, ModelMap model) { // 从request中获取请求参数 String idString =
     * request.getParameter("id"); Integer id = Integer.valueOf(idString); //
     * 根据id获取商品数据 Item item = itemService.getItemById(id);
     * model.addAttribute("item", item); return "itemEdit"; }
     */

    /**
     * 根据id查询商品，绑定简单数据类型
     * 
     * @param id
     * @param model
     * @return
     */
    /*
     * @RequestMapping("itemEdit") public String itemEdit(int id, ModelMap
     * model) { // 根据id获取商品数据 Item item = itemService.getItemById(id);
     * model.addAttribute("item", item); return "itemEdit"; }
     */

    /**
     * 根据id查询商品，绑定简单数据类型-RequestParam使用
     * 
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("itemEdit")
    public String itemEdit(@RequestParam(value = "itemId", required = true, defaultValue = "1") Integer id,
            ModelMap model) {
        // 根据id获取商品数据
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        return "itemEdit";
    }

    /**
     * 修改商品
     * 
     * @param item
     * @return
     */
    @RequestMapping("updateItem")
    public String updateItem(Item item, Model model) {
        // 更新商品
        itemService.updateItemById(item);
        model.addAttribute("item", item);
        model.addAttribute("msg", "修改商品信息成功！");
        return "itemEdit";
    }

    /**
     * 商品列表搜索
     * 
     * @param vo
     * @param model
     * @return
     */
    @RequestMapping("queryItem")
    public String queryItem(QueryVo vo, Model model) {
        if (vo.getItem() != null) {
            System.out.println(vo.getItem());
        }
        // 模拟搜索商品
        List<Item> itemList = itemService.getItemList();

        model.addAttribute("itemList", itemList);
        return "itemList";
    }
}