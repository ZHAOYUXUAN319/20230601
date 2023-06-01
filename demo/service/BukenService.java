package com.example.demo.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Buken;
import com.example.demo.entity.User;
import com.example.demo.obj.BukenDto;
import com.example.demo.obj.UserDto;
import com.example.demo.repository.BukenRepository;
import com.example.demo.repository.UserRepository;

@Service
public class BukenService {
	
	@Autowired
	private BukenRepository bukenRepository;
	@Autowired
	private UserRepository userRepository;
	// 物件公開一覧
	public List<BukenDto> searchAll() {
		List<Buken> bukenList = bukenRepository.findAll(Sort.by(Order.asc("propertyId")));
		List<BukenDto> bukenDtoList = new ArrayList<>();

		for (Buken buken : bukenList) {
			BukenDto bukenDto = convertToDto(buken);
			bukenDtoList.add(bukenDto);
		}

		return bukenDtoList;
	}

	private BukenDto convertToDto(Buken buken) {
		Long id = buken.getPropertyId();
		String name = buken.getPropertyName();
		String address = buken.getAddress();
		String propertyType = buken.getPropertyType();
		Date period = buken.getPeriod();
		String propertyArea = buken.getPropertyArea();
		String price = buken.getPrice();
		String syozokuCompanyId = buken.getSyozokuCompanyId();
		String status = buken.getStatus();
		String imagePath = buken.getImagePath();
		return new BukenDto(id, name, address, propertyType, period, propertyArea, price, syozokuCompanyId, status,
				imagePath);
	}

	// 物件非公開一覧

	public List<BukenDto> searchBukenHikoukei() {
		List<Buken> bukenList = bukenRepository.findByStatusContainingOrderByPropertyId("なし");

		List<BukenDto> bukenDtoList = new ArrayList<>();

		for (Buken buken : bukenList) {
			BukenDto bukenDto = convertToDtohikoukei(buken);
			bukenDtoList.add(bukenDto);
		}

		return bukenDtoList;
	}

	private BukenDto convertToDtohikoukei(Buken buken) {
		Long id = buken.getPropertyId();
		String name = buken.getPropertyName();
		String address = buken.getAddress();
		String propertyType = buken.getPropertyType();
		Date period = buken.getPeriod();
		String propertyArea = buken.getPropertyArea();
		String price = buken.getPrice();
		String syozokuCompanyId = buken.getSyozokuCompanyId();
		String status = buken.getStatus();
		String imagePath = buken.getImagePath();
		return new BukenDto(id, name, address, propertyType, period, propertyArea, price, syozokuCompanyId, status,
				imagePath);
	}
	// ユーザ一覧
	public List<UserDto> searchAllUser() {
		List<User> userList = userRepository.findAll(Sort.by(Order.asc("id")));
		List<UserDto> userDtoList = new ArrayList<>();

		for (User user : userList) {
			UserDto userDto = convertToDtoUser(user);
			userDtoList.add(userDto);
		}

		return userDtoList;
	}

	private UserDto convertToDtoUser(User user) {
		long id = user.getId();
		String userName = user.getUserName();
		String password = user.getPassword();
		String status = user.getStatus();
		
		return new UserDto(id, userName, password, status);
	}

	// 物件新規の保存

	public Buken saveBuken(Buken buken) {
		// BukenRepository を使って、DBに保存する
		return bukenRepository.save(buken);
	}
	// User新規の保存

	public User saveUser(User user) {
		// UserRepository を使って、DBに保存する
		return userRepository.save(user);
	}

	// 検索

	public List<BukenDto> searchById(Long propertyId) {
		List<Buken> bukenList;

		if (propertyId != null) {
			bukenList = bukenRepository.findByPropertyId(propertyId);
		} else {
			bukenList = bukenRepository.findAll(Sort.by(Order.asc("propertyId")));
		}

		List<BukenDto> bukenDtoList = new ArrayList<>();
		for (Buken buken : bukenList) {
			BukenDto bukenDto = convertToDto(buken);
			bukenDtoList.add(bukenDto);
		}

		return bukenDtoList;
	}

	// 期間検索
	public List<Buken> searchByPeriod(Date fromdate, Date todate) {
		return bukenRepository.findByPeriodBetweenOrderByPeriod(fromdate, todate);
	}

	// 検索
	// 构造函数注入BukenRepository
	public BukenService(BukenRepository bukenRepository) {
		this.bukenRepository = bukenRepository;
	}

	public List<Buken> searchByUidValue(Long propertyId) {
		return bukenRepository.findByPropertyId(propertyId);
	}

	// 削除
	public void deleteBuken(Long id) {
		bukenRepository.deleteById(id);
	}

	// 物件編集
	public void updateBuken(BukenDto bukenDto) {
		Long propertyId = bukenDto.getPropertyId();
		Optional<Buken> optionalBuken = bukenRepository.findById(propertyId);
		if (optionalBuken.isPresent()) {
			Buken buken = optionalBuken.get();
			buken.setPropertyName(bukenDto.getPropertyName());
			buken.setAddress(bukenDto.getAddress());
			buken.setPropertyType(bukenDto.getPropertyType());
			buken.setPeriod(bukenDto.getPeriod());
			buken.setPropertyArea(bukenDto.getPropertyArea());
			buken.setPrice(bukenDto.getPrice());
			buken.setSyozokuCompanyId(bukenDto.getSyozokuCompanyId());
			buken.setStatus(bukenDto.getStatus());
			//buken.setImagePath(bukenDto.getImagePath());
			bukenRepository.save(buken);
		} else {
			throw new NotFoundException("Buken not found with ID: " + propertyId);
		}
	}
	
	// 物件注文
	public void updateBukenStatus(BukenDto bukenDto) {
	    Long propertyId = bukenDto.getPropertyId();
	    Optional<Buken> optionalBuken = bukenRepository.findById(propertyId);
	    if (optionalBuken.isPresent()) {
	        Buken buken = optionalBuken.get();
	        buken.setStatus("あり"); 
	        bukenRepository.save(buken);
	    } else {
	        throw new NotFoundException("Buken not found with ID: " + propertyId);
	    }
	}

	
	// ユーザ編集
		public void updateUser(UserDto userDto) {
			Long id = userDto.getId();
			Optional<User> optionalUser = userRepository.findById(id);
			if (optionalUser.isPresent()) {
				User user = optionalUser.get();
				// 更新Buken对象的属性
				user.setUserName(userDto.getUserName());
				user.setPassword(userDto.getPassword());
				user.setStatus(userDto.getStatus());
				userRepository.save(user);
			} else {
				throw new NotFoundException("User not found with ID: " + id);
			}
		}
	//物件更新（選ばれた物件のIDを取得）
	public BukenDto getBukenById(Long propertyId) {
		Optional<Buken> optionalBuken = bukenRepository.findById(propertyId);
		if (optionalBuken.isPresent()) {
			Buken buken = optionalBuken.get();
			return convertToDto(buken);
		} else {
			throw new NotFoundException("Buken not found with ID: " + propertyId);
		}
	}
	
	//ユーザ更新（選ばれたユーザのIDを取得）
	public UserDto getUserById(Long id) {
		Optional<User> optionalUser = userRepository.findById(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			return convertToDtoUser(user);
		} else {
			throw new NotFoundException("User not found with ID: " + id);
		}
	}

}
