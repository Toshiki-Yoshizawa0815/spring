package jp.co.internous.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.practice.model.domain.Goods;
import jp.co.internous.practice.model.domain.Purchase;
import jp.co.internous.practice.model.domain.User;
import jp.co.internous.practice.model.domain.dto.HistoryDto;
import jp.co.internous.practice.model.form.HistoryForm;
import jp.co.internous.practice.model.form.PurchaseForm;
import jp.co.internous.practice.model.mapper.GoodsMapper;
import jp.co.internous.practice.model.mapper.PurchaseMapper;
import jp.co.internous.practice.model.mapper.UserMapper;

@Controller
@RequestMapping("/practice")
public class IndexController {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	GoodsMapper goodsMapper;
	
	@Autowired
	PurchaseMapper purchaseMapper;
	
	Gson gson = new Gson();
	
	@RequestMapping("/")
	public String index(Model m) {
		String userName = "taro";
		String password = "taropw";
		List<User> users = userMapper.findByUserNameAndPassword(userName, password);
		
		User user = new User();
		if(users != null && users.size() > 0) {
			user = users.get(0);
			System.out.println(user.getFullName());
		}
		m.addAttribute("user", user);
		
		List<Goods> goodsList = goodsMapper.findAll();
		if(goodsList != null && goodsList.size() > 0) {
			m.addAttribute("goodsList", goodsList);
		}
		
		return "index";
	}
	
	@ResponseBody
	@PostMapping("/api/purchase")
	public String purchaseApi(@RequestBody PurchaseForm f) {
		
		Purchase p = new Purchase();
		p.setUserId(f.getUserId());
		p.setGoodsId(f.getGoodsId());
		p.setItemCount(f.getItemCount());
		p.setTotal(f.getTotal());
		purchaseMapper.insert(p);
		
		return "1";
	}
	
	@ResponseBody
	@PostMapping("/api/history")
	public String historyApi(@RequestBody HistoryForm form) {
		
		String userId = form.getUserId();
		List<HistoryDto> history = purchaseMapper.findHistory(userId);
		
		return gson.toJson(history);
	}	

}
