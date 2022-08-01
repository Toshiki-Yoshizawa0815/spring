package jp.co.internous.ecsite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.internous.ecsite.model.dao.GoodsRepository;
import jp.co.internous.ecsite.model.dao.UserRepository;
import jp.co.internous.ecsite.model.entity.Goods;
import jp.co.internous.ecsite.model.entity.User;
import jp.co.internous.ecsite.model.form.GoodsForm;
import jp.co.internous.ecsite.model.form.LoginForm;

@Controller
@RequestMapping("/ecsite/admin")
public class AdminController {
	
	@Autowired
	private UserRepository userRepos;

	@Autowired
	private GoodsRepository goodsRepos;

	@RequestMapping("/")
	public String index() {
		return "adminindex";
	}
	
	@PostMapping("/welcome")
	public String welcome(LoginForm form, Model m) {
		List<User> users = userRepos.findByUserNameAndPassword(form.getUserName(), form.getPassword());

		// 検証用 ここから
		
		// form2を新たにインスタンス化しても、postされたname属性は代入されないので、
		// getUserName()の結果はnullとなる。
		LoginForm form2 = new LoginForm();
		System.out.println(form2.getUserName());
		// formインスタンス(引数とも)にはpostされたname属性があり、
		// welcomeメソッドが呼び出された時点でインスタンス化されているため、
		// getPassword()にはpostされた内容が反映されている。
		System.out.println(form.getPassword());
		
		// usersはListの配列で格納されているため、get(0)のように連番を指定する必要がある
		// get(1)とするとエラーが発生する。
		// usersをインスタンス化した段階でUserRepositoryがselect文をDBに
		// 送った結果が格納されており、その結果が1つのユーザしか無かったからエラーが出る？
		System.out.println(users.get(0).getUserName());
		System.out.println(users.get(0).getFullName());
		
		// usersではformにpostされたuserNameとpasswordを使ってDBのユーザ検索をしたが、
		// users2では直接引数にユーザ名とパスワードを入力してみた。
		// 結果、渡された引数のユーザ名とパスワードが正しければJpaRipositoryを通して
		// select文の結果が帰ってくることでユーザ名等が格納されることが分かった。
		List<User> users2 = userRepos.findByUserNameAndPassword("jiro", "jiropw");
		System.out.println(users2.get(0).getFullName());
		
		// findAll()メソッドを呼び出してgoods2に代入しているので、
		// goodsテーブルに登録された全てのレコードが格納される
		// 現時点で3つのレコードが登録されているので、get(2)までは表示可能。
		List<Goods> goods2 = goodsRepos.findAll();
		System.out.println(goods2.get(0).getGoodsName());
		System.out.println(goods2.get(2).getGoodsName());
		System.out.println(goods2.get(1).getUpdatedAt());
		
		// 検証用 ここまで
		
		if (users != null && users.size() > 0) {
			boolean isAdmin = users.get(0).getIsAdmin() != 0;
			if(isAdmin) {
				List<Goods> goods = goodsRepos.findAll();
				m.addAttribute("userName", users.get(0).getUserName());
				m.addAttribute("password", users.get(0).getPassword());
				m.addAttribute("goods", goods);
			}
		}
		
		return "welcome";
	}
	
	@RequestMapping("/goodsMst")
	public String goodsMst(LoginForm form, Model m) {
		m.addAttribute("userName", form.getUserName());
		m.addAttribute("password", form.getPassword());
		
		return "goodsmst";
	}
	
	@RequestMapping("/addGoods")
	public String addGoods(GoodsForm goodsForm, LoginForm loginForm, Model m) {
		m.addAttribute("userName", loginForm.getUserName());
		m.addAttribute("password", loginForm.getPassword());
		
		Goods goods = new Goods();
		goods.setGoodsName(goodsForm.getGoodsName());
		goods.setPrice(goodsForm.getPrice());
		goodsRepos.saveAndFlush(goods);
		
		return "forward:/ecsite/admin/welcome";
	}
	
	@ResponseBody
	@PostMapping("/api/deleteGoods")
	public String deleteApi(@RequestBody GoodsForm f, Model m) {
		try {
			goodsRepos.deleteById(f.getId());
		} catch (IllegalArgumentException e) {
			return "-1";
		}
		
		return "1";
	}

}
