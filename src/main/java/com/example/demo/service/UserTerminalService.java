package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.exception.AlreadyUsedTerminalNameException;
import com.example.demo.response.TerminalResponse;

@Service
public class UserTerminalService {

	public void insert(Integer userId, String terminalName) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public List<TerminalResponse> getTerminalList(Integer userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	public void updateTerminalName(Integer userId, String oldTerminalName, String newTerminalName) { //AlreadyUsedTerminalNameException
		// TODO 自動生成されたメソッド・スタブ
		
	}

	public void delete(Integer userId, String terminalName) {//NotFoundValueException
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
