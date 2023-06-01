package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Buken;
import com.example.demo.entity.User;
import com.example.demo.obj.BukenDto;
import com.example.demo.obj.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BukenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

/**
 * ユーザー情報 Controller
 */
@Controller
@SessionAttributes("isVip")
public class UserController {


	// 物件公開一覧
	@Autowired
	private BukenService bukenService;

	@GetMapping(value = "/user/Buken")
	public ModelAndView displayListBuken(Model model) {
		List<BukenDto> bukenList = bukenService.searchAll();
		System.out.println("物件情報取得しました。" + bukenList);
		model.addAttribute("bukenList", bukenList);

		ModelAndView modelAndView = new ModelAndView("/user/Buken");
		return modelAndView;
	}

	// 物件非公開一覧

	@GetMapping(value = "/user/BukenHikoukei")
	public ModelAndView displayListBukenhikoukei(Model model) {
		List<BukenDto> bukenList = bukenService.searchBukenHikoukei();
		System.out.println("物件情報取得しました。" + bukenList);
		model.addAttribute("bukenList", bukenList);

		ModelAndView modelAndView = new ModelAndView("/user/Buken");
		return modelAndView;
	}

	// ユーザ一覧
	@GetMapping(value = "/user/user")
	public ModelAndView displayListUser(Model model) {
		List<UserDto> userList = bukenService.searchAllUser();
		System.out.println("ユーザ情報取得しました。" + userList);
		model.addAttribute("userList", userList);

		ModelAndView modelAndView = new ModelAndView("/user/user");
		return modelAndView;
	}

	// 物件新規画面
	@GetMapping(value = "/user/BukenAdd")
	public String displayBukenAdd(Model model) {
		model.addAttribute("bukenDto", new BukenDto());
		return "/user/BukenAdd";
	}

	@PostMapping("/user/BukenAdd")
	public String addBuken(@ModelAttribute("bukenDto") BukenDto bukenDto,
			@RequestParam("image") MultipartFile imageFile) {
		Buken buken = new Buken();
		buken.setPropertyName(bukenDto.getPropertyName());
		buken.setAddress(bukenDto.getAddress());
		buken.setPropertyType(bukenDto.getPropertyType());
		buken.setPeriod(bukenDto.getPeriod());
		buken.setPropertyArea(bukenDto.getPropertyArea());
		buken.setPrice(bukenDto.getPrice());
		buken.setSyozokuCompanyId(bukenDto.getSyozokuCompanyId());
		buken.setStatus(bukenDto.getStatus());

		if (!imageFile.isEmpty()) {
			try {
				String fileName = imageFile.getOriginalFilename();
				String filePath = "C:/Users/hangt/eclipse-workspace/Fudosan2/src/main/resources/static/img/" + fileName;
				imageFile.transferTo(new File(filePath));
				buken.setImagePath(filePath);
			} catch (IOException e) {
				e.printStackTrace();

			}
		}

		bukenService.saveBuken(buken);

		return "redirect:/user/home";
	}

	// ユーザ新規画面
	@GetMapping(value = "/user/userAdd")
	public String displayUserAdd(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "/user/userAdd";
	}

	@PostMapping("/user/userAdd")
	public String addUser(@ModelAttribute("userDto") UserDto userDto) {
		User user = new User();
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());

		bukenService.saveUser(user);

		return "redirect:/login";
	}

	//
	@GetMapping("/user/home")
	public String showMyPage() {
		return "user/home";
	}

	// 删除
	@PostMapping("/user/Buken/delete/{id}")
	public String deleteBuken(@PathVariable("id") Long propertyId) {
		// bukenService.deleteBukenById(propertyId);
		bukenService.deleteBuken(propertyId);
		return "redirect:/user/home";
	}

	// 更新物件
	@GetMapping("/user/BukenEdit/{propertyId}")
	public String displayBukenEdit(@PathVariable Long propertyId, Model model) {
		BukenDto bukenDto = bukenService.getBukenById(propertyId);
		model.addAttribute("bukenDto", bukenDto);
		return "user/BukenEdit";
	}

	// 物件更新・注文
	@PostMapping("/user/BukenEdit")
	public String updateBuken(@ModelAttribute("bukenDto") BukenDto bukenDto,@RequestParam("updateType") String updateType, HttpSession session) {
		if (updateType.equals("status")) {
			bukenService.updateBukenStatus(bukenDto);
			session.setAttribute("message", "messageがあります。");
		} else {
			bukenService.updateBuken(bukenDto);
		}
		return "redirect:/user/home";
	}
	
//	@PostMapping("/user/BukenEdit")
//	public String updateBuken(@ModelAttribute("bukenDto") BukenDto bukenDto) {
//
//		bukenService.updateBuken(bukenDto);
//		return "redirect:/user/home";
//	}

	// ユーザ更新
	@GetMapping("/user/userEdit/{id}")
	public String displayUserEdit(@PathVariable Long id, Model model) {
		UserDto userDto = bukenService.getUserById(id);
		model.addAttribute("userDto", userDto);
		return "user/userEdit";
	}

	// ユーザ更新
	@PostMapping("/user/userEdit")
	public String updateUser(@ModelAttribute("userDto") UserDto userDto) {
		bukenService.updateUser(userDto);
		return "redirect:/user/user";
	}

	// 検索
	// 构造函数注入BukenService
	public UserController(BukenService bukenService) {
		this.bukenService = bukenService;
	}

	@PostMapping("/submit")
	public ModelAndView submitForm(@RequestParam("uid") Long propertyId) {
		List<Buken> bukenList = bukenService.searchByUidValue(propertyId);

		ModelAndView modelAndView = new ModelAndView("user/Buken");
		modelAndView.addObject("bukenList", bukenList);
		return modelAndView;
	}

	// 期間検索
	@PostMapping("/submitkikan")
	public ModelAndView submitForm1(@RequestParam("fromdate") Date fromdate, @RequestParam("todate") Date todate) {
		List<Buken> bukenList = bukenService.searchByPeriod(fromdate, todate);

		ModelAndView modelAndView = new ModelAndView("user/Buken");
		modelAndView.addObject("bukenList", bukenList);
		return modelAndView;
	}

	// ログイン
	@Autowired
	private HttpSession session;

	@GetMapping("/login")
	public String showLoginForm() {
		return "user/login";
	}

	@Autowired
	private UserRepository userRepository;

//	@PostMapping("/login")
//	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session) {
//	    User user = userRepository.findByUserName(userName); // ユーザ名で情報を検査
//	    System.out.println(userName);
//
//	    if (user != null && user.getPassword().equals(password) && "VIP".equals(user.getStatus())) {
//	        boolean isVip = true;
//
//	        session.setAttribute("isVip", isVip);
//
//	        System.out.println("Login successful");
//	        return "redirect:/user/Buken";
//	    } else if (user != null && user.getPassword().equals(password) && !"VIP".equals(user.getStatus())) {
//	        boolean isVip = false;
//
//	        session.setAttribute("000", isVip);
//
//	        System.out.println("Login successful");
//	        return "redirect:/user/Buken";
//	    }else{
//	        System.out.println("Login failed");
//	        return "redirect:/login?error";
//	    }
//
//	}
	@PostMapping("/login")
	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
			HttpSession session) {
		User user = userRepository.findByUserName(userName);
		System.out.println(userName);

		if (user != null && user.getPassword().equals(password)) {
			boolean isVip = "VIP".equals(user.getStatus());

			session.setAttribute("isVip", isVip);

			System.out.println("Login successful");
			return "redirect:/user/home";
		} else {
			System.out.println("Login failed");
			return "redirect:/login?error";
		}
	}
//	@PostMapping("/login")
//	public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, HttpSession session, HttpServletRequest request) {
//	    User user = userRepository.findByUserName(userName); 
//	    System.out.println(userName);
//
//	    if (user != null && user.getPassword().equals(password)) {
//	        boolean isVip = "VIP".equals(user.getStatus());
//
//	        // 无效化
//	        session.invalidate();
//
//	     
//	        HttpSession newSession = request.getSession(true);
//	        newSession.setAttribute("isVip", isVip);
//
//	        System.out.println("Login successful");
//	        return "redirect:/user/Buken";
//	    } else {
//	        System.out.println("Login failed");
//	        return "redirect:/login?error";
//	    }
//	}

}