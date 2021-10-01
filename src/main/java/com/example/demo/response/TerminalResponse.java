package com.example.demo.response;

import com.example.demo.entity.TerminalEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TerminalResponse {
	private String terminalName;
	
	public TerminalResponse(TerminalEntity entity) {
		terminalName = entity.getTerminalName();
	}
}
